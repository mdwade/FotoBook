package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "User")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.find",    query = "SELECT u FROM User u where u.email = :email")
})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String address;

	private int age;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="phone_number")
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="register_date")
	private Date registerDate;

	@Column(name="user_type")
	private String userType;

	
	@OneToMany(mappedBy="user")
	private List<Album> albums;

	public User() {
	}

	
	public User(int id, String address, int age, String email, String firstName, String lastName, String password,
			String phoneNumber, String userType) {
		
		this.id = id;
		this.address = address;
		this.age = age;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.registerDate = new Date(System.currentTimeMillis());
		this.userType = userType;		
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setUser(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setUser(null);

		return album;
	}

}