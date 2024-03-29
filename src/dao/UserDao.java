package dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.User;

/**
 * Session Bean implementation class UserDao
 */
@Stateless
public class UserDao implements UserDaoLocal {
	
	@PersistenceContext(unitName = "fotobook_pu")
	private EntityManager em;
	
	
	@Override
	public void addUser(User u) {
		em.persist(u);
	}
	

	@Override
	public void editUser(User u) {
		em.merge(u);
	}

	@Override
	public void deleteUser(int userId) {		
		User u = getUser(userId);
		
		em.remove(u);
		//System.out.println(u.getFirstName());
	}

	@Override
	public User getUser(int userId) {		
		return em.find(User.class, userId);
	}

	
	@Override
	public User getUserByLogin(String email) {
		
		User u = null;
		TypedQuery<User> query = em.createNamedQuery("User.find", User.class);
		query.setParameter("email", email);
		
		try {
			u = (User) query.getSingleResult();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return u;
	}


	@Override
	public List<User> getAllUser() {		
					
		List<User> listUser = null;
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		
		try {
			listUser = (List<User>) query.getResultList();			
		} 
		catch (NoResultException e) 
		{
			
		} catch (Exception e) {
			
		}
		return listUser;
	}


	@Override
	public List<User> getAuthorisedUser(int idAlbum) {
		List<User> listUser = null;
		
		String sql = "SELECT * FROM User u where u.id IN (SELECT idUser FROM shared_album s where s.idAlbum = ? )";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, idAlbum);
		
		
		try {
			listUser = query.getResultList();			
		} 
		catch (NoResultException e) 
		{
			
		} catch (Exception e) {
			
		}
		return listUser;
				
	}


}
