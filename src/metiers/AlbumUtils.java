package metiers;

import java.util.List;

import model.Album;

public class AlbumUtils {

	public static boolean checkIfEmpty(String data) 
	{
		Boolean isNotEmpty = false;
		
		if (data.length() == 0 || data.length() == 1) 
		{
			isNotEmpty = true;
		}
			
		return isNotEmpty;
	}
	
	public static Boolean checkIfAlbumExist(String albumName, List<Album> listUserAlbum) {
		Boolean exist = true;
		
		for(Album a : listUserAlbum) {
			if(!albumName.equals(a.getName())) {
				exist = false;
			}
		}
		
		return exist;
	}

}
