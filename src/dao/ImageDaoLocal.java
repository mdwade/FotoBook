package dao;

import java.util.List;

import model.Image;

public interface ImageDaoLocal {
	
	public void addImage(Image i);
	
	
	public void editImage(Image i);
	
	
	public void deleteImage(int idImage);
	
	
	public Image getImage(int idImage);
	
	
	public int getLastImageIndex();
	
	
	public List<Image> getImageByAlbumId(int idAlbum);
	
	
	public List<Image> getAllImage();
}
