package metiers;

import java.util.List;

import model.User;

public class UserUtils {
	
	public static boolean checkIfEmpty(String data) 
	{
		Boolean isNotEmpty = false;
		
		if (data.length() == 0 || data.length() == 1) 
		{
			isNotEmpty = true;
		}
			
		return isNotEmpty;
	}
	
	
	public static boolean validatePhoneNumber(String telephone) 
	{
		Boolean valide = false;
		
		// Phone number like 774741740 or +221774741740
		String numberPattern = "^[7]+[0-6-7-8]+[0-9]{7}|^[\"+\"2217]+[0-6-7-8]+[0-9]{7}";
		
		if(telephone.matches(numberPattern)) 
		{
			
			valide = true;
		}		
		
		return valide;
	}
	
	
	public static boolean CheckIfTelephoneExist(String telephone, List<User> listUser) 
	{
		Boolean exist = false;
		
		for(User u : listUser) {
			if(!telephone.equals(u.getPhoneNumber())) 
			{
				exist = true;
			}
			break;
		}
		return exist;
	}
	
	
	public static boolean validateEmailAddress(String email) 
	{
		Boolean valide = false;
		
		String emailPattern = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
		
		if(email.matches(emailPattern)) 
		{
			
			valide = true;
		}		
		
		return valide;
	}
	
	
	public static boolean CheckIfEmailExist(String email, List<User> listUser) 
	{
		Boolean exist = false;
		
		for(User u : listUser) {
			if(!email.equals(u.getEmail())) 
			{
				exist = true;
			}
			break;
		}
		return exist;
	}
	
	
	public static boolean validateAge(int age) {
		Boolean valide = false;
		
		if(age > 10 && age < 75) {
			valide = true;
		}
		
		return valide;
	}
	
	public static boolean validatePassword(String password) {
		Boolean valide = false;
		
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		if(password.matches(passwordPattern)) {
			valide = true;
		}
		
		return valide;
	}

}
