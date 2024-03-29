package servlets;

import java.io.IOException;
import java.util.List;

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
import model.Album;
import model.User;


@WebServlet({"/home", "/public", "/private", "/users"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID                         =   1L;
	private static final String HOME_PAGE                              =   "/home.jsp";
	private static final String PUBLIC_PAGE                            =   "/public.jsp";
	private static final String PRIVATE_PAGE                           =   "/private.jsp";
	private static final String LIST_USER_PAGE					       =   "/list_user.jsp";
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	@EJB 
	private UserDaoLocal userDaoLocal;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path         = request.getServletPath();
		
		User u              = (User)request.getSession().getAttribute("user");
		
		switch(path) {
			case "/home":
				List<Album> albums         =    albumDaoLocal.getAlbumsByUserId(u.getId()), 
				albumsPublic               =    albumDaoLocal.getAlbumsPublic();
	
				request.setAttribute("albums",       albums);
				request.setAttribute("albumsPublic", albumsPublic);
				this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
				break;
				
			case "/public":
				List<Album> albumsPublics     =   albumDaoLocal.getAlbumsPublic();
				
				if(u != null) {
					albumsPublics = albumDaoLocal.getAlbumsPublic(u.getId());
					List<Album> abl = null;
					if(u.getUserType().equals("ADMIN")) {
						abl = albumDaoLocal.getAlbumsPrivate(u.getId());					
					}
					else {
						abl = albumDaoLocal.getAlbumAutorisedToUser(u.getId());												
					}
					
					request.setAttribute("authorisedAlbums", abl);
				}
				
				request.setAttribute("albumsPublics", albumsPublics);
				this.getServletContext().getRequestDispatcher(PUBLIC_PAGE).forward(request, response);
				break;
				
			case "/private":
				List<Album> alb = null;
				
				if(u.getUserType().equals("ADMIN")) {
					alb = albumDaoLocal.getAlbumsPrivate(u.getId());					
				}
				else {
					alb = albumDaoLocal.getAlbumAutorisedToUser(u.getId());					
				}
				
				request.setAttribute("albumsPublics", alb);
				this.getServletContext().getRequestDispatcher(PRIVATE_PAGE).forward(request, response);
				break;
				
			case "/users":	
				request.setAttribute("listUtilisateur", userDaoLocal.getAllUser());
				this.getServletContext().getRequestDispatcher(LIST_USER_PAGE).forward(request, response);
				break;
		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
