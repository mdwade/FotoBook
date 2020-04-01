package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AlbumDaoLocal;
import dao.ImageDaoLocal;
import dao.UserDaoLocal;
import metiers.AlbumUtils;
import metiers.SweetAlert;
import model.Album;
import model.Image;
import model.User;


@WebServlet({"/get_album", "/get_album1", "/add_album", "/delete_album", "/update_album", "/get_authorised_users"})
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID         =   1L;
	private static final String HOME_PAGE              =   "/home.jsp";
	private static final String EMPTY_FIELD_ERROR_MSG  =   "Vérifier que vous avez rempli tous les champs.";
	
	@Inject Global global;
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
	@EJB
	private UserDaoLocal userDaoLocal;
	
	@EJB
	private ImageDaoLocal imageDaoLocal;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path        =   request.getServletPath();
		
		switch(path) {
		case "/get_album":
			int idAlbum    = Integer.parseInt(request.getParameter("id"));
			Album alb      = albumDaoLocal.getAlbum(idAlbum);
			Gson gson      = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String json    = gson.toJson(alb);
			response.setContentType("/application/json");
			response.getWriter().write(json);			
			response.getWriter().flush();			
			break;
			
		case "/get_album1":
			int idAlbum_      = Integer.parseInt(request.getParameter("id"));
			Album alb1        = albumDaoLocal.getAlbum(idAlbum_);
			alb1.setImages(imageDaoLocal.getImageByAlbumId(idAlbum_));
			String uploadPath = global.UPLOAD_FILE_DIRECTORY;
			String message    = AlbumUtils.albumBloc(alb1, uploadPath);
			Gson gson_        = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String json_      = gson_.toJson(message);
			response.setContentType("/application/json");
			response.getWriter().write(json_);			
			response.getWriter().flush();			
			break;
			
		case "/get_authorised_users":
			int idAlbum1                 = Integer.parseInt(request.getParameter("id"));
			List<User> listAuthorsedUser = userDaoLocal.getAuthorisedUser(idAlbum1);
			Gson gson1                   = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String json1                 = gson1.toJson(listAuthorsedUser);
			response.setContentType("/application/json");
			response.getWriter().write(json1);				
			response.getWriter().flush();			
			break;
			
		case "/delete_album":
			int idAlbum2 = Integer.parseInt(request.getParameter("id"));
			albumDaoLocal.deleteAlbum(idAlbum2);
			response.sendRedirect("/FotoBook/home");	
			break;
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path                     =   request.getServletPath();
		Map<String, String> errors      =   new HashMap<String, String>();
		User u                          =   (User) request.getSession().getAttribute("user");
		
		
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
										
					try {
										
						switch(access) {
							case "public":
								albumDaoLocal.addAlbum(a);
								break;
							
							case "prive":
								albumDaoLocal.addAlbum(a);								
								if(sharedUser.length !=0) {
									albumDaoLocal.insertSharedAlbum(sharedUser, albumDaoLocal.getLastAlbumIndex());
								}								
								break;
						}
					}
					catch(NullPointerException e) {}
					
					response.sendRedirect("/FotoBook/home");					
				}
				else {
					request.setAttribute("errors", errors);	
					request.getSession().setAttribute("user", u);
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				
				break;			
			
			case "/update_album":
				int     idAlbum          =  Integer.parseInt(request.getParameter("idAlbum"));
				String  name1            =  request.getParameter("albumName");
				String  theme1           =  request.getParameter("theme");
				String  access1          =  request.getParameter("access");				
				String  [] sharedUser1   =  request.getParameterValues("users");
								
				
				if(AlbumUtils.checkIfEmpty(name1)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);				
				}
				
				if(errors.isEmpty()) {				
										
					Album a = new Album(idAlbum, access1, name1, theme1, u);					
														
					try {
												
						switch(access1) {
							case "public":
								albumDaoLocal.editAlbum(a);	
								albumDaoLocal.updateSharedAlbum(idAlbum);
								break;
							
							case "prive":
								albumDaoLocal.editAlbum(a);									
								if(sharedUser1.length !=0) {
									albumDaoLocal.updateSharedAlbum(idAlbum);
									albumDaoLocal.insertSharedAlbum(sharedUser1, idAlbum);
								}
								else {
									albumDaoLocal.updateSharedAlbum(idAlbum);
								}
								break;
						}
						
					}
					catch(NullPointerException e) {}
																			
					response.sendRedirect("/FotoBook/home");					
				}
				else {
					request.setAttribute("errors", errors);					
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				
			
				break;
				
			default:
				break;
		}
	}

}
