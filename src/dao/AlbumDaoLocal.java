package dao;

import java.util.List;

import model.Album;

public interface AlbumDaoLocal {

	public void addAlbum(Album a);
	
	
	public void editAlbum(Album a);
	
	
	public void deleteAlbum(int albumId);
	
	
	public Album getAlbum(int albumId);
	
	
	public List<Album> getAlbumsByUserId(int userId);
	
	
	public List<Album> getAlbumsPublic();
	
	
	public List<Album> getAllAlbum();
}
