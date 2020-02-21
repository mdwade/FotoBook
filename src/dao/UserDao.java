package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
		em.remove(getUser(userId));
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
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}


}
