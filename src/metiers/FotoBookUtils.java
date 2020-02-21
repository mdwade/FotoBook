package metiers;

import java.util.ArrayList;

import model.User;

public class FotoBookUtils {
	
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
	
	
	public static boolean CheckIfTelephoneExist(String telephone, ArrayList<User> listClient) 
	{
		Boolean exist = false;
		
		for(User u : listClient) {
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
	
	
	public static boolean CheckIfEmailExist(String email, ArrayList<User> listClient) 
	{
		Boolean exist = false;
		
		for(User u : listClient) {
			if(!email.equals(u.getEmail())) 
			{
				exist = true;
			}
			break;
		}
		return exist;
	}

}
