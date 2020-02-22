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
import model.User;


@WebServlet({"/sign_in", "/sign_up", "/sign_out"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID     =   1L;
	private static final String HOME_PAGE          =   "/home.jsp";
	private static final String SIGN_IN_PAGE       =   "/index.jsp";
	private static final String SIGN_IN_ERROR_MSG  =   "Email ou mot de passe incorrect.";
	
	
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
				
				User   u         = userDaoLocal.getUserByLogin(email);
				
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
				break;
		}
	}

}
