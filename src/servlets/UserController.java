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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AlbumDaoLocal;
import dao.UserDaoLocal;
import metiers.UserUtils;
import model.Album;
import model.User;

@WebServlet({"/sign_in", "/sign_up", "/sign_out", "/get_user", "/get_users", "/add_user", "/delete_user", "/update_user"})
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID                         =   1L;
	private static final String HOME_PAGE_URL                          =   "/FotoBook/home";
	private static final String SIGN_IN_URL                            =   "/FotoBook/sign_in";
	private static final String SIGN_IN_PAGE                           =   "/index.jsp";
	private static final String SIGN_UP_PAGE						   =   "/WEB-INF/sign_up.jsp";
	private static final String LIST_USER_PAGE					       =   "/list_user.jsp";
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
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
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
				response.sendRedirect(SIGN_IN_URL);				
				break;
				
			case "/sign_up":
				this.getServletContext().getRequestDispatcher(SIGN_UP_PAGE).forward(request, response);
				break;
				
			case "/get_user":
				int idUser  = Integer.parseInt(request.getParameter("id"));
				User user = userDaoLocal.getUser(idUser);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String json = gson.toJson(user);
				response.setContentType("/application/json");
				response.getWriter().write(json);				
				response.getWriter().flush();			
				break;	
				
			case "/get_users":				
				List<User> listUser = userDaoLocal.getAllUser();
				Gson gson1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String json1 = gson1.toJson(listUser);
				response.setContentType("/application/json");
				response.getWriter().write(json1);				
				response.getWriter().flush();			
				break;	
			
			case "/delete_user":
				int idUser1 = Integer.parseInt(request.getParameter("id"));
				userDaoLocal.deleteUser(idUser1);
				response.sendRedirect("/FotoBook/users");
						
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		
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
					response.sendRedirect(HOME_PAGE_URL);
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
				
				if(UserUtils.checkIfEmpty(_lastName) || UserUtils.checkIfEmpty(_firstName) || UserUtils.checkIfEmpty(_address)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);
				}
				else {
					if(!_password.equals(_passwordConfirm)) {
						errors.put("passwordError", NO_CONFORM_PASSWORD_MSG);
					}
					
					if(!UserUtils.validatePassword(_password)) {
						errors.put("passwordFormatError", PASSWORD_FORMAT_ERROR_MSG);
					}
					
					if(!UserUtils.validateEmailAddress(_email)){
						errors.put("emailFormatError", EMAIL_ERROR_MSG);
					}
					
					if(!UserUtils.CheckIfEmailExist(_email, userDaoLocal.getAllUser())) {
						errors.put("emailExistError", EMAIL_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!UserUtils.validatePhoneNumber(_phoneNumber)) {
						errors.put("phoneNumberFormatError", PHONE_NUMBER_ERROR_MSG);
					}
					
					if(!UserUtils.CheckIfTelephoneExist(_phoneNumber, userDaoLocal.getAllUser())) {
						errors.put("phoneNumberExistError", PHONE_NUMBER_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!UserUtils.validateAge(_age)) {
						errors.put("ageError", AGE_ERROR_MSG);
					}						
				}
								
				
				if(errors.isEmpty()) {
					User sampleUser = new User(0, _address, _age, _email, _firstName, _lastName, _password, _phoneNumber, userType);
					userDaoLocal.addUser(sampleUser);
					HttpSession session = request.getSession();
					session.setAttribute("user", sampleUser);
					
					response.sendRedirect(HOME_PAGE_URL);		
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
				
			case "/update_user":
				int      id               =  Integer.parseInt(request.getParameter("uIdUser"));				
				String  _lastName1        =  request.getParameter("lastName");				
				String  _firstName1       =  request.getParameter("firstName");
				int     _age1             =  Integer.parseInt(request.getParameter("age"));
				String  _email1           =  request.getParameter("email");
				String  _phoneNumber1     =  request.getParameter("phoneNumber");
				String  _address1         =  request.getParameter("address");
				String   userType         =  request.getParameter("userType");
				
				
				
				if(UserUtils.checkIfEmpty(_lastName1) || UserUtils.checkIfEmpty(_firstName1) || UserUtils.checkIfEmpty(_address1)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);
				}
				else {
										
					if(!UserUtils.validateEmailAddress(_email1)){
						errors.put("emailFormatError", EMAIL_ERROR_MSG);
					}									
					
					if(!UserUtils.validatePhoneNumber(_phoneNumber1)) {
						errors.put("phoneNumberFormatError", PHONE_NUMBER_ERROR_MSG);
					}
					
					if(!UserUtils.validateAge(_age1)) {
						errors.put("ageError", AGE_ERROR_MSG);
					}						
				}
				
				if(errors.isEmpty()) {
					User user = new User();
					User userToUpdate = userDaoLocal.getUser(id);
					
					user.setId(id);						
					user.setFirstName(_firstName1);
					user.setLastName(_lastName1);
					user.setAge(_age1);
					user.setAddress(_address1);
					user.setPhoneNumber(_phoneNumber1);
					user.setEmail(_email1);
					user.setUserType(userType);
					user.setPassword(userToUpdate.getPassword());
					user.setRegisterDate(userToUpdate.getRegisterDate());
					
					userDaoLocal.editUser(user);					
					
					response.sendRedirect("/FotoBook/users");		
				}
				else {
					request.setAttribute("errors",       errors);
					request.setAttribute("lastName",    _lastName1);
					request.setAttribute("firstName",   _firstName1);
					request.setAttribute("age",         _age1);
					request.setAttribute("email",       _email1);
					request.setAttribute("phoneNumber", _phoneNumber1);
					request.setAttribute("address",     _address1);
					
					request.setAttribute("listUtilisateur", userDaoLocal.getAllUser());
					this.getServletContext().getRequestDispatcher(LIST_USER_PAGE).forward(request, response);					
				}
				break;
				
			case "/add_user":
				String  _lastName2        =  request.getParameter("lastName");
				String  _firstName2       =  request.getParameter("firstName");
				int     _age2             =  Integer.parseInt(request.getParameter("age"));
				String  _email2           =  request.getParameter("email");
				String  _phoneNumber2     =  request.getParameter("phoneNumber");
				String  _address2         =  request.getParameter("address");
				String  _password2        =  request.getParameter("password");
				String   userType2        =  request.getParameter("userType");
				
				if(UserUtils.checkIfEmpty(_lastName2) || UserUtils.checkIfEmpty(_firstName2) || UserUtils.checkIfEmpty(_address2)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);
				}
				else {
					
					if(!UserUtils.validatePassword(_password2)) {
						errors.put("passwordFormatError", PASSWORD_FORMAT_ERROR_MSG);
					}
					
					if(!UserUtils.validateEmailAddress(_email2)){
						errors.put("emailFormatError", EMAIL_ERROR_MSG);
					}
					
					if(!UserUtils.CheckIfEmailExist(_email2, userDaoLocal.getAllUser())) {
						errors.put("emailExistError", EMAIL_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!UserUtils.validatePhoneNumber(_phoneNumber2)) {
						errors.put("phoneNumberFormatError", PHONE_NUMBER_ERROR_MSG);
					}
					
					if(!UserUtils.CheckIfTelephoneExist(_phoneNumber2, userDaoLocal.getAllUser())) {
						errors.put("phoneNumberExistError", PHONE_NUMBER_ALREADY_EXIST_ERROR_MSG);
					}
					
					if(!UserUtils.validateAge(_age2)) {
						errors.put("ageError", AGE_ERROR_MSG);
					}						
				}
								
				
				if(errors.isEmpty()) {
					User newUser = new User(0, _address2, _age2, _email2, _firstName2, _lastName2, _password2, _phoneNumber2, userType2);
					userDaoLocal.addUser(newUser);
					response.sendRedirect("/FotoBook/users");				
				}
				else {
					request.setAttribute("errors",       errors);
					request.setAttribute("lastName",    _lastName2);
					request.setAttribute("firstName",   _firstName2);
					request.setAttribute("age",         _age2);
					request.setAttribute("email",       _email2);
					request.setAttribute("phoneNumber", _phoneNumber2);
					request.setAttribute("address",     _address2);
					
					request.setAttribute("listUtilisateur", userDaoLocal.getAllUser());
					this.getServletContext().getRequestDispatcher(LIST_USER_PAGE).forward(request, response);					
				}
				break;
		}
	}

}
