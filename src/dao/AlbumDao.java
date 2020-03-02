package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Album;
import model.User;

/**
 * Session Bean implementation class AlbumDaon
 */
@Stateless
public class AlbumDao implements AlbumDaoLocal {

	@PersistenceContext(unitName = "fotobook_pu")
	private EntityManager em;
	
	@Override
	public void addAlbum(Album a) {
		em.persist(a);
	}

	
	@Override
	public void editAlbum(Album a) {
		em.merge(a);
	}

	
	@Override
	public void deleteAlbum(int albumId) {
		em.remove(getAlbum(albumId));
	}

	
	@Override
	public Album getAlbum(int albumId) {
		return em.find(Album.class, albumId);
	}

	
	@Override
	public List<Album> getAlbumsByUserId(int userId) {
		
		List<Album> album = null;
		TypedQuery<Album> query = em.createNamedQuery("Album.find", Album.class);
		query.setParameter("userId", userId);
		
		try {
			album = (List<Album>) query.getResultList();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return album;
	}
	
	
	@Override
	public List<Album> getAlbumsPublic() {
		
		List<Album> album = null;
		TypedQuery<Album> query = em.createNamedQuery("Album.findPublic", Album.class);
		
		try {
			album = (List<Album>) query.getResultList();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return album;
	}
	

	@Override
	public List<Album> getAllAlbum() {
		TypedQuery<Album> query = em.createNamedQuery("Album.findAll", Album.class);
		return query.getResultList();
	}

}
