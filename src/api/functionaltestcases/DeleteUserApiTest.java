package api.functionaltestcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.Instant;

import org.testng.Assert;

import api.reusableapimethods.ReusableApiMethods;
import api.supportingfiles.ApiPayload;
import api.supportingfiles.ApiResources;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


/* 18th April 2021
 * Author: Amit Girme
 * This class is to validate the create user API
 * Scenarios which are considered for validation as below:
 * 1. Validate the status code as 204
 * 2. Validate the Headername: Server
 * 3. Validate the the Json Response is blank:
 * []  
 * 
 * */


public class DeleteUserApiTest {
	
	final int expectedstatuscode=204;
	final String expectedheadername="Server";
	final String expectedheaderval="cloudflare";
	
	
	public void deleteUser(String URI){


		String deleteuserresource =ApiResources.deleteUserResource();
		System.out.println("deleteuserresource: "  + deleteuserresource);
		
        //Validation of header and status code is done as under this method
		String deleteuserresponse=ReusableApiMethods.reusbaleApiDeleteMethodResponse(URI, deleteuserresource, expectedstatuscode,expectedheadername, expectedheaderval);
	
        
		//Validation of Json Response contents
		Assert.assertTrue(deleteuserresponse.isEmpty());

	}

}
