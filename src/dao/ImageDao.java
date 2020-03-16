package dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Album;
import model.Image;

@Stateless
public class ImageDao implements ImageDaoLocal{

	@PersistenceContext(unitName = "fotobook_pu")
	private EntityManager em;
	
	
	@Override
	public void addImage(Image i) {
		em.createNativeQuery("INSERT INTO Image (title, description, keyWords, height, width, creation_date, updated_date, imageFile, idAlbum) VALUES (?,?,?,?,?,?,?,?,?)")
	      .setParameter(1, i.getTitle())
	      .setParameter(2, i.getDescription())
	      .setParameter(3, i.getKeyWords())	   
	      .setParameter(4, i.getHeight())
	      .setParameter(5, i.getWidth())
	      .setParameter(6, new Date(System.currentTimeMillis()))
	      .setParameter(7, null)
	      .setParameter(8, i.getImageFile())
	      .setParameter(9, i.getAlbum().getId())
	      .executeUpdate();	
	}

	@Override
	public void editImage(Image i) {
		em.merge(i);
		
	}

	@Override
	public void deleteImage(int idImage) {		
		em.remove(getImage(idImage));
	}

	@Override
	public Image getImage(int idImage) {
		return em.find(Image.class, idImage);
	}

	@Override
	public int getLastImageIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Image> getImageByAlbumId(int idAlbum) {
		List<Image> images = null;
		TypedQuery<Image> query = em.createNamedQuery("Image.find", Image.class);
		query.setParameter("albumId", idAlbum);
		
		try {
			images = (List<Image>) query.getResultList();
		} 
		catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			
		}
		return images;
	}

	@Override
	public List<Image> getAllImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
