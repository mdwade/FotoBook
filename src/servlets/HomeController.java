package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlbumDaoLocal;
import model.Album;
import model.User;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID                         = 1L;
	private static final String HOME_PAGE                              =   "/home.jsp";
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String path                = request.getServletPath();
		
		User u                     = (User)request.getSession().getAttribute("user");
		List<Album> albums         =  albumDaoLocal.getAlbumsByUserId(u.getId()), 
					albumsPublic   =  albumDaoLocal.getAlbumsPublic();
		
		
		request.setAttribute("albums",       albums);
		request.setAttribute("albumsPublic", albumsPublic);
		
		this.getServletContext().getRequestDispatcher(HOME_PAGE).forward(request, response);
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
