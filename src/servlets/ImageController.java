package servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.AlbumDaoLocal;
import dao.ImageDaoLocal;
import metiers.FileUtils;
import model.Album;
import model.Image;
import model.User;


@WebServlet(
		urlPatterns = { "/add_image", "/delete_image", "/edit_image", "/get_image", "/get_album_images" }, 
		initParams = @WebInitParam(name = "UPLOAD_FILE_DIRECTORY", value = "/home/mouhamed/Bureau/uploadDir/")
)
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class ImageController extends HttpServlet {
	private static final long serialVersionUID              =   1L;
	public  static final int TAILLE_TAMPON                  =   10240; // 10 ko
	private static final String FILE_NOT_VALID_ERROR_MSG    =   "TYPE DE FICHIER NON PRIS EN CHARGE";
	
	@Inject Global global;
	
	@EJB
	private AlbumDaoLocal albumDaoLocal;
	
	@EJB
	private ImageDaoLocal imageDaoLocal;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path  = request.getServletPath();
		
		switch(path) {
			case "/get_image":
				int idImage = Integer.parseInt(request.getParameter("id"));
				Image i = imageDaoLocal.getImage(idImage);				
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String json = gson.toJson(i);
				response.setContentType("/application/json");
				response.getWriter().write(json);				
				response.getWriter().flush();	
				break;
				
				
			case "/get_album_images":				
				int idAlbum  = Integer.parseInt(request.getParameter("id"));
				List<Image> albumImage = imageDaoLocal.getImageByAlbumId(idAlbum);				
				Gson gson1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String json1 = gson1.toJson(albumImage);
				response.setContentType("/application/json");
				response.getWriter().write(json1);				
				response.getWriter().flush();							
				break;
				
			case "/delete_image":
				int idImage1     = Integer.parseInt(request.getParameter("id"));
				Image i1         = imageDaoLocal.getImage(idImage1);
				int albumName = i1.getAlbum().getId();
				String imageDir  = global.UPLOAD_FILE_DIRECTORY+albumName;
				File img     = new File(imageDir+"/"+i1.getImageFile());
				Files.deleteIfExists(img.toPath());				
				imageDaoLocal.deleteImage(idImage1);
				response.sendRedirect("/FotoBook/home");	
				break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path                   =   request.getServletPath();
		Map<String, String> errors    =   new HashMap<String, String>();
		User u                        =   (User) request.getSession().getAttribute("user");

		switch (path) {
		case "/add_image":
			
			int idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
			Album album = albumDaoLocal.getAlbum(idAlbum);
			
			Part part = request.getPart("imageFile");

			
			//String uploadPath = getServletContext().getRealPath("") + File.separator + album.getName();
			String uploadPath = global.UPLOAD_FILE_DIRECTORY + album.getId();
			uploadPath = uploadPath.replaceAll(" ", "-");
			
			FileUtils.mkUploadDir(uploadPath);	
			

			if(!FileUtils.validateImageFile(FileUtils.getFileExtension(FileUtils.getFileName(part)))) {
				errors.put("fileNotValidError", FILE_NOT_VALID_ERROR_MSG);	
			}
			
			
			if(errors.isEmpty()) {
				String fileName = FileUtils.genFileNameByTimeStamp(FileUtils.getFileName(part));
				
				if (FileUtils.getFileName(part) != null && !FileUtils.getFileName(part).isEmpty()) {
					//fileName = fileName.substring(fileName.lastIndexOf("/") + 1).substring(fileName.lastIndexOf("\\") + 1);

					
					FileUtils.writeImageOnDisk(part, fileName, uploadPath, TAILLE_TAMPON);			
					
					BufferedImage img     = ImageIO.read(new File(uploadPath+"/"+fileName));
					
					String title          = request.getParameter("imageTitle");
					String description    = request.getParameter("description");
					String keyWords       = request.getParameter("keyWords");
					int height            = img.getHeight();
					int width             = img.getWidth();
					
					Image i = new Image(0, description, height, fileName, keyWords, title, width, album);
					imageDaoLocal.addImage(i);									
					
					//response.getWriter().write(uploadPath+"/"+fileName);
				}
			}
			response.sendRedirect("/FotoBook/home");
			
			break;
			
		case "/edit_image":
			int    idImage        = Integer.parseInt(request.getParameter("imgId"));
			String imgTitle       = request.getParameter("imgTitle");
			String imgDescription = request.getParameter("imgDescription");
			String imgKeyWords    = request.getParameter("imgKeyWords");
			Image i               = new Image();
			Image imgToEdit       = imageDaoLocal.getImage(idImage);
			i.setId(idImage);
			i.setTitle(imgTitle);
			i.setDescription(imgDescription);
			i.setKeyWords(imgKeyWords);			
			i.setHeight(imgToEdit.getHeight());
			i.setWidth(imgToEdit.getWidth());
			i.setCreationDate(imgToEdit.getCreationDate());
			i.setUpdatedDate(new Date(System.currentTimeMillis()));
			i.setImageFile(imgToEdit.getImageFile());
			i.setAlbum(imgToEdit.getAlbum());
			imageDaoLocal.editImage(i);
			response.sendRedirect("/FotoBook/home");
			
			//Part   part1          = request.getPart("imgFile");
			//System.out.println("value: "+FileUtils.getFileName(part1));
			break;
		}
	}
	

	

}
