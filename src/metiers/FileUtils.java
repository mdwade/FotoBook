package metiers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import javax.servlet.http.Part;


public class FileUtils {
	
	public static String getFileName(Part part) {	    
		for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {        
	        if ( contentDisposition.trim().startsWith( "filename" ) ) {            
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	        }
	    }
	    
	    return null;
	}
	
	
	
	public static String getFileExtension(String fileName) {				
	    return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	
	public static String genFileNameByTimeStamp(String fileName) {		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "." + getFileExtension(fileName);
	}
	
	public static String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8" ) );
	    StringBuilder valeur = new StringBuilder();
	    char[] buffer = new char[1024];
	    int longueur = 0;
	    while ( ( longueur = reader.read( buffer ) ) > 0 ) {
	        valeur.append( buffer, 0, longueur );
	    }
	    return valeur.toString();
	}

	
	public static void mkUploadDir(String uploadPath) {
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();				
		}
	}
	
	
	public static void writeImageOnDisk(Part part, String fileName, String path, int TAILLE_TAMPON) throws IOException {		
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    try {	    
	        entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
	        sortie = new BufferedOutputStream(new FileOutputStream(new File(path + "/" + fileName)),  TAILLE_TAMPON);

	        
	        byte[] tampon = new byte[TAILLE_TAMPON];
	        int longueur;
	        while (( longueur = entree.read( tampon )) > 0) {
	            sortie.write(tampon, 0, longueur);
	        }
	    } finally {
	        try {
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
	        }
	    }
	}
	
	public static Boolean validateImageFile(String fileExtension) {
		Boolean valid = false;
		
		String extensionPattern = "(?i)(jpg|png|gif|bmp)";
		if(fileExtension.matches(extensionPattern)) {
			valid = true;
		}
		
		return valid;
	}
	
}
