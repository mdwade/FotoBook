package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AlbumDaoLocal;
import dao.UserDaoLocal;
import metiers.AlbumUtils;
import model.Album;
import model.User;


@WebServlet({"/get_album", "/add_album", "/delete_album", "/update_album"})
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID         =   1L;
	private static final String HOME_PAGE              =   "/home.jsp";
	private static final String EMPTY_FIELD_ERROR_MSG  =   "Vérifier que vous avez rempli tous les champs.";
	
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
	@EJB
	private UserDaoLocal userDaoLocal;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path        =   request.getServletPath();
		
		switch(path) {
		case "/get_album":
			int idAlbum  = Integer.parseInt(request.getParameter("id"));
			Album alb = albumDaoLocal.getAlbum(idAlbum);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String json = gson.toJson(alb);
			response.setContentType("/application/json");
			response.getWriter().write(json);
			
			response.getWriter().flush();			
			break;
			
		case "/delete_album":
			int idAlbum1 = Integer.parseInt(request.getParameter("id"));
			albumDaoLocal.deleteAlbum(idAlbum1);
			response.sendRedirect("/FotoBook/home");	
			break;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path                   =   request.getServletPath();
		Map<String, String> errors    =   new HashMap<String, String>();
		User u                        =   (User) request.getSession().getAttribute("user");
		
		
		switch (path) {
			case "/add_album":	
				
				String  name            =  request.getParameter("albumName");
				String  theme           =  request.getParameter("theme");
				String  access          =  request.getParameter("access");				
				String  [] sharedUser   =  request.getParameterValues("users");
								
				
				if(AlbumUtils.checkIfEmpty(name)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);				
				}
				
				if(errors.isEmpty()) {				
										
					Album a = new Album(0, access, name, theme, u);					
					albumDaoLocal.addAlbum(a);					
					
					try {
						if(sharedUser.length != 0 && access.equals("prive")) {
							albumDaoLocal.insertSharedAlbum(sharedUser, albumDaoLocal.getLastAlbumIndex());
						}
					}
					catch(NullPointerException e) {
						
					}
																			
					response.sendRedirect("/FotoBook/home");					
				}
				else {
					request.setAttribute("errors", errors);
					
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				
				break;			
			
			case "/update_album":
				break;
				
			default:
				break;
		}
	}

}
