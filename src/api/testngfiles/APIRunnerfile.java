package api.testngfiles;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.functionaltestcases.CreateUserApiTest;
import api.functionaltestcases.DeleteUserApiTest;
import api.functionaltestcases.GetUserListApiTest;
import api.functionaltestcases.RegisterEmailApiTest;
import api.functionaltestcases.RegisterLoginApiTest;
import api.supportingfiles.ApiPayload;
import io.restassured.RestAssured;

public class APIRunnerfile {

	final String baseuri="https://reqres.in";
    String registeremailtoken;
    String loginemailtoken;
	
    @Test(description ="1. Functional Automation testing for Register Successful API")
	public void RegisterEmailApiFunctionalTest()
	{
	    RegisterEmailApiTest EmailApi= new RegisterEmailApiTest();
	    this.registeremailtoken = EmailApi.registerEmail(baseuri);
 	   
	}
	
	@Test(dependsOnMethods={"RegisterEmailApiFunctionalTest"},description ="2. Functional Automation testing for Register Login Successful API")
	public void RegisterLoginApiFunctionalTest()
	{
		RegisterLoginApiTest LoginApi= new RegisterLoginApiTest();
		this.loginemailtoken=LoginApi.registerLogin(baseuri);
	}
	
	@Test(dependsOnMethods={"RegisterLoginApiFunctionalTest"},description ="3. Functional Automation testing for Create User Successfully API")
	public void CreateUserApiFunctionalTest()
	{
	    CreateUserApiTest createuserApi= new CreateUserApiTest();
 	    createuserApi.createUser(baseuri);
	}
	
	@Test(dependsOnMethods={"CreateUserApiFunctionalTest"},description ="4. Functional Automation testing for Get List of Users Successfully API")
	public void GetUserListApiFunctionalTest()
	{
		GetUserListApiTest  getuserlistApi = new GetUserListApiTest();
		getuserlistApi.listUser(baseuri);
	}
	
	@Test(dependsOnMethods={"CreateUserApiFunctionalTest"},description ="5. Functional Automation testing for Delete User API")
	public void deleteUserApiFunctionalTest()
	{
		DeleteUserApiTest  deleteUserApi = new DeleteUserApiTest();
		deleteUserApi.deleteUser(baseuri);
	}
	
	
	
}
