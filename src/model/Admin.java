package model;

import java.util.Date;
import java.util.List;

public class Admin extends User {

	
	private static final long serialVersionUID = 1L;

	public Admin() {
		
	}

	public Admin(int id, String address, int age, String email, String firstName, String lastName, String password,
			String phoneNumber, Date registerDate, String userType, List<Album> albums) {
		super(id, address, age, email, firstName, lastName, password, phoneNumber, userType);
	}

		
}
