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
 * 1. Validate the status code
 * 2. Validate the content type
 * 3. Validate the Headername: Server
 * 4. Validate the the complete fields are received as Json Response:
 * {
    "name": "morpheus",
    "job": "leader",
    "id": "715",
    "createdAt": "2021-04-18T15:06:10.762Z"
   }
     for "id" which is generated uniquely, validating if ID is generated and added not null condition.
 *   for "createdAt" which is generated as per current timestamp validating if createdAt is generated and added not null condition.

 * */


public class CreateUserApiTest {
	
	final int expectedstatuscode=201;
	final String expectedname="morpheus";
	final String expectedjob="leader";
	final String expectedcontentType="application/json";
	final String expectedheadername="Server";
	final String expectedheaderval="cloudflare";
	
	final String name="morpheus";
	final String job="leader";
	
	public void createUser(String URI){
		String createuserPayload=ApiPayload.createUser(name,job);
		System.out.println("createuserPayload: "  + createuserPayload);

		String createuserresource =ApiResources.createUserResource();
		System.out.println("createuserresource: "  + createuserresource);
		
        //Validation of contenttype, header and status code is done as under this method

		String createuserresponse=ReusableApiMethods.reusbaleApiPostMethodResponse(URI, createuserPayload, createuserresource, expectedcontentType, expectedstatuscode,expectedheadername, expectedheaderval);
		
		

		JsonPath jscreateuser = JsonPath.from(createuserresponse);

		String actualname = jscreateuser.get("name");
		String actualjob = jscreateuser.get("job");
        String actualid=jscreateuser.get("id");
        String actualcreatedAt=jscreateuser.get("createdAt");

        
		//Validation of Json Response contents
		Assert.assertEquals(expectedname, actualname);
		Assert.assertEquals(expectedjob, actualjob);
		Assert.assertNotNull(actualid);
		Assert.assertNotNull(actualcreatedAt);

	}

}
