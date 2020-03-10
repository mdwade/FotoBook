package model;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Album")

@NamedQueries({
	@NamedQuery(name = "Album.findAll",    query = "SELECT a FROM Album a"),
	@NamedQuery(name = "Album.find",       query = "SELECT a FROM Album a where a.user.id = :userId"),
	@NamedQuery(name = "Album.findPublic", query = "SELECT a FROM Album a where a.access  = 'public'"),
	@NamedQuery(name = "Album.lastIndex",  query = "SELECT MAX(a.id) FROM Album a")
})
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private int id;

	@Expose
	private String access;

	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	@Expose
	private Date creationDate;

	@Expose
	private String name;

	@Expose
	private String theme;

	
	@ManyToOne
	@JoinColumn(name="idUser")
	@Expose
	private User user;

	
	@OneToMany(mappedBy="album", cascade = CascadeType.ALL)
	private List<Image> images;

	public Album() {
	}
	
	public Album(int id, String access, String name, String theme, User user) {		
		this.id = id;
		this.access = access;
		this.name = name;
		this.theme = theme;
		this.creationDate = new Date(System.currentTimeMillis());
		this.setUser(user);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess() {
		return this.access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setAlbum(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setAlbum(null);

		return image;
	}

}