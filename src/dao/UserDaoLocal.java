package dao;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserDaoLocal 
{
	
	public void addUser(User u);
	
	
	public void editUser(User u);
	
	
	public void deleteUser(int userId);
	
	
	public User getUser(int userId);
	
	
	public User getUserByLogin(String email);
	
	
	public List<User> getAllUser();
	
	
	public List<User> getAuthorisedUser(int idAlbum);
	
}
