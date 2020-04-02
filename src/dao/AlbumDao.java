package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		//em.persist(a);
		em.createNativeQuery("INSERT INTO Album (name, theme, access, creation_date, idUser) VALUES (?,?,?,?,?)")
	      .setParameter(1, a.getName())
	      .setParameter(2, a.getTheme())
	      .setParameter(3, a.getAccess())	      
	      .setParameter(4, new Date(System.currentTimeMillis()))
	      .setParameter(5, a.getUser().getId())
	      .executeUpdate();
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
	public List<Album> getAlbumsPublic(int idUser) {
		
		Query q = em.createNativeQuery("SELECT * from Album where access = 'public' and idUser != ?", Album.class);
	      q.setParameter(1, idUser);
	    
	    List<Album> album = null;	
			
		try {
			album = q.getResultList();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return album;
	}
	
	@Override
	public List<Album> getAlbumAutorisedToUser(int userId) {
		
		Query q = em.createNativeQuery("SELECT * from Album where id IN (SELECT idAlbum from shared_album where idUser = ?)", Album.class);
	      q.setParameter(1, userId);   
	    
	    List<Album> album = null;	
			
		try {
			album = q.getResultList();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return album;
		
	}
	
	@Override
	public List<Album> getAlbumsPrivate(int idUser) {
		Query q = em.createNativeQuery("SELECT * from Album where access = 'prive' and idUser != ?", Album.class);
	      q.setParameter(1, idUser);
	    
	    List<Album> album = null;	
			
		try {
			album = q.getResultList();
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


	@Override
	public int getLastAlbumIndex() {
		TypedQuery<Integer> query = em.createNamedQuery("Album.lastIndex", Integer.class);
		return query.getSingleResult();
		
	}


	@Override
	public void insertSharedAlbum(String [] idUser, int idAlbum) {
		for(String id: idUser) {
			em.createNativeQuery("INSERT INTO shared_album (idUser, idAlbum) VALUES (?,?)")
		      .setParameter(1, id)	
		      .setParameter(2, idAlbum)	
		      .executeUpdate();
		}
	}


	@Override
	public void updateSharedAlbum(int idAlbum) {	
		em.createNativeQuery("DELETE FROM shared_album where idAlbum = ? ")
	      .setParameter(1, idAlbum)		      
	      .executeUpdate();
		
	}

}
