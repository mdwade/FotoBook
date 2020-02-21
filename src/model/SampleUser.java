package model;

import java.util.Date;
import java.util.List;

public class SampleUser extends User {

	private static final long serialVersionUID = 1L;
	
	public SampleUser() {

	}

	public SampleUser(int id, String address, int age, String email, String firstName, String lastName, String password,
			String phoneNumber, Date registerDate, String userType, List<Album> albums) {
		super(id, address, age, email, firstName, lastName, password, phoneNumber, registerDate, userType, albums);
	}

}
