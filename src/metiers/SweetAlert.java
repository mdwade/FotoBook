package metiers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SweetAlert {

	public SweetAlert() {
		
	}

	public static void showSweetAlertBox(HttpServletRequest request, HttpServletResponse response, int error, String msg, String redirectURL) throws IOException {
		
		if(error == 0) 
		{
			
			//response.sendRedirect("/FotoBook/home");				
		}
		else 
		{
			try 
			{
				request.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			} 
			catch (ServletException e) {}
		}
		
	}
}
