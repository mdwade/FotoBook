package model;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;


@Entity
@Table(name = "Image")
@NamedQueries({
	@NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
	@NamedQuery(name = "Image.find",    query = "SELECT i FROM Image i where i.album.id = :albumId"),
})

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	@Expose
	private Date creationDate;

	@Lob
	@Expose
	private String description;

	@Expose
	private int height;

	@Lob
	@Expose
	private String imageFile;

	@Lob
	@Expose
	private String keyWords;

	@Expose
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(name="updated_date")
	@Expose
	private Date updatedDate;

	@Expose
	private int width;

	
	@ManyToOne
	@JoinColumn(name="idAlbum")
	private Album album;

	public Image() {
	}

	
	public Image(int id, String description, int height, String imageFile, String keyWords,
			String title, int width, Album album) {
		
		this.id = id;
		this.creationDate = new Date(System.currentTimeMillis());
		this.description = description;
		this.height = height;
		this.imageFile = imageFile;
		this.keyWords = keyWords;
		this.title = title;
		this.updatedDate = null;
		this.width = width;
		this.setAlbum(album);
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImageFile() {
		return this.imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}