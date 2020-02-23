package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoLocal;
import metiers.FotoBookUtils;
import model.User;


@WebServlet({"/sign_in", "/sign_up", "/sign_out"})
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID                         =   1L;
	private static final String HOME_PAGE                              =   "/home.jsp";
	private static final String SIGN_IN_PAGE                           =   "/index.jsp";
	private static final String SIGN_UP_PAGE						   =   "/WEB-INF/sign_up.jsp";
	private static final String SIGN_IN_ERROR_MSG                      =   "Email ou mot de passe incorrect.";
	private static final String NO_CONFORM_PASSWORD_MSG                =   "Les mots de passe ne sont pas conformes."; 
	private static final String EMAIL_ERROR_MSG                        =   "L'adresse email saisie est invalide.";
	private static final String EMAIL_ALREADY_EXIST_ERROR_MSG          =   "Cette adresse email existe déjà.";
	private static final String PHONE_NUMBER_ERROR_MSG                 =   "Le numéro de téléphone saisi est invalide.";
	private static final String PHONE_NUMBER_ALREADY_EXIST_ERROR_MSG   =   "Ce numéro de téléphone existe déjà.";
	private static final String AGE_ERROR_MSG			               =   "L'age doit être compris entre 10 et 75ans";
	private static final String EMPTY_FIELD_ERROR_MSG                  =   "Vérifier que vous avez rempli tous les champs.";
	private static final String PASSWORD_FORMAT_ERROR_MSG              =   "Le mot de passe doit contenir au moins 8 caractères dont 1 chiffre, "
																			+ "au minimum une lettre majuscule, une lettre minuscule et un caractère spécial. "
																			+ "Pas d'espace ni de tabulation.";
	private static final String userType							   =   "SAMPLE";
	
	
	@EJB
	private UserDaoLocal userDaoLocal;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String path                 =  request.getServletPath();
		
		switch(path) {
			case "/sign_in":
				this.getServletContext().getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
				break;
				
			case "/sign_out":
				HttpSession session = request.getSession();
				session.invalidate();
				this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				break;
				
			case "/sign_up":
				this.getServletContext().getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
				break;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String path                   =  request.getServletPath();
		Map<String, String> errors    =  new HashMap<String, String>();
		
		switch(path) 
		{
			
			case "/sign_in":
				String email     =  request.getParameter("email");
				String password  =  request.getParameter("password");
				
				User   u         =  userDaoLocal.getUserByLogin(email);
				
				if(u == null) {
					errors.put("signInError", SIGN_IN_ERROR_MSG);										
				}
				else if(!u.getPassword().equals(password)) {
					errors.put("signInError", SIGN_IN_ERROR_MSG);					
				}				
				
				if(errors.isEmpty()) {
					HttpSession session = request.getSession();
					session.setAttribute("user", u);
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				else {
					request.setAttribute("errors", errors);
					request.setAttribute("email", email);
					this.getServletContext().getRequestDispatcher(SIGN_IN_PAGE).forward(request, response);
				}
				
				break;
				
				
			case "/sign_up":
				String  _lastName        =  request.getParameter("lastName");
				String  _firstName       =  request.getParameter("firstName");
				int     _age             =  Integer.parseInt(request.getParameter("age"));
				String  _email           =  request.getParameter("email");
				String  _phoneNumber     =  request.getParameter("phoneNumber");
				String  _address         =  request.getParameter("address");
				String  _password        =  request.getParameter("password");
				String  _passwordConfirm =  request.getParameter("passwordConfirm");
				
				if(FotoBookUtils.checkIfEmpty(_lastName) || FotoBookUtils.checkIfEmpty(_firstName) || FotoBookUtils.checkIfEmpty(_address)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);
				}
				else {
					if(!_password.equals(_passwordConfirm)) {
						errors.put("passwordError", NO_CONFORM_PASSWORD_MSG);
					}
					
					if(!FotoBookUtils.validatePassword(_password)) {
						errors.put("passwordFormatError", PASSWORD_FORMAT_ERROR_MSG);
					}
					
					if(!FotoBookUtils.validateEmailAddress(_email)){
						errors.put("emailFormatError", EMAIL_ERROR_MSG);
					}
					
					if(!FotoBookUtils.CheckIfEmailExist(_email, userDaoLocal.getAllUser())) {
						errors.put("emailExistError", EMAIL_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!FotoBookUtils.validatePhoneNumber(_phoneNumber)) {
						errors.put("phoneNumberFormatError", PHONE_NUMBER_ERROR_MSG);
					}
					
					if(!FotoBookUtils.CheckIfTelephoneExist(_phoneNumber, userDaoLocal.getAllUser())) {
						errors.put("phoneNumberExistError", PHONE_NUMBER_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!FotoBookUtils.validateAge(_age)) {
						errors.put("ageError", AGE_ERROR_MSG);
					}						
				}
				
				
				
				if(errors.isEmpty()) {
					User sampleUser = new User(0, _address, _age, _email, _firstName, _lastName, _password, _phoneNumber, userType);
					userDaoLocal.addUser(sampleUser);
					HttpSession session = request.getSession();
					session.setAttribute("user", sampleUser);
					
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				else {
					request.setAttribute("errors",       errors);
					request.setAttribute("lastName",    _lastName);
					request.setAttribute("firstName",   _firstName);
					request.setAttribute("age",         _age);
					request.setAttribute("email",       _email);
					request.setAttribute("phoneNumber", _phoneNumber);
					request.setAttribute("address",     _address);
					
					this.getServletContext().getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
				}
				
				break;
		}
	}

}
