package api.supportingfiles;

public class ApiPayload {

	
	public static String registerSuccessfull(String email,String password){

		String registerUserPayload ="{\r\n" + 
				"    \"email\": \""+email+"\",\r\n" + 
				"    \"password\": \""+password+"\"\r\n" + 
				"}";
		
		return registerUserPayload;

	}
	
	public static String loginSuccessfull(String email,String password){

		String loginUserPayload ="{\r\n" + 
				"    \"email\": \""+email+"\",\r\n" + 
				"    \"password\": \""+password+"\"\r\n" + 
				"}";
		return loginUserPayload;

	}
	
	public static String createUser(String username, String job){

		String createUserPayload ="{\r\n" + 
				"    \"name\": \""+username+"\",\r\n" + 
				"    \"job\": \""+job+"\"\r\n" + 
				"}";
		return createUserPayload;

	}	
}
