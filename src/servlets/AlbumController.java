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

import dao.AlbumDaoLocal;
import metiers.AlbumUtils;
import model.Album;
import model.User;


@WebServlet({"/add_album"})
public class AlbumController extends HttpServlet {
	private static final long serialVersionUID         =   1L;
	private static final String HOME_PAGE              =   "/home.jsp";
	private static final String EMPTY_FIELD_ERROR_MSG  =   "Vérifier que vous avez rempli tous les champs.";
	
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path                   =   request.getServletPath();
		Map<String, String> errors    =   new HashMap<String, String>();
		User u                        =   (User) request.getSession().getAttribute("user");
		
		
		switch (path) {
			case "/add_album":						
				String  name    =  request.getParameter("albumName");
				String  theme   =  request.getParameter("theme");
				String  access  =  request.getParameter("access");				
				
				if(AlbumUtils.checkIfEmpty(name)) {
					errors.put("emptyFieldError", EMPTY_FIELD_ERROR_MSG);				
				}
				
				if(errors.isEmpty()) {				
										
					Album a = new Album(0, access, name, theme, u);
					albumDaoLocal.addAlbum(a);
									
					//this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
					response.sendRedirect("/FotoBook/home");					
				}
				else {
					request.setAttribute("errors", errors);
					
					this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				}
				
				break;
	
			case "/delete_album":
				int idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
				albumDaoLocal.deleteAlbum(idAlbum);
				response.sendRedirect("/FotoBook/home");		
				break;
				
			default:
				break;
		}
	}

}
