package api.functionaltestcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import api.reusableapimethods.ReusableApiMethods;
import api.supportingfiles.ApiPayload;
import api.supportingfiles.ApiResources;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/* 18th April 2021
 * Author: Amit Girme
 * This class is to validate the Register Email user API
 * Scenarios which are considered for validation as below:
 * 1. Validate the status code
 * 2. Validate the content type
 * 3. Validate the Headername: Server
 * 4. Validate the the complete fields are received as Json Response:
 * {"id":4,"token":"QpwL5tke4Pnpja7X4"}
 * */

public class RegisterEmailApiTest {
	
	final int expectedstatuscode=200;
	final int expectedId=4;
	final String expectedtoken="QpwL5tke4Pnpja7X4";
	final String expectedheadername="Server";
	final String expectedheaderval="cloudflare";
	final String expectedcontentType="application/json";
	final String registerEmailId="eve.holt@reqres.in";
	final String registerPassword="pistol";
	


	public String registerEmail(String URI){
		
		String registerUserPayload=ApiPayload.registerSuccessfull(registerEmailId,registerPassword);
		System.out.println("registerUserPayload: "  + registerUserPayload);

		String registerSuccessfullresource =ApiResources.registerSuccessfullresource();
		System.out.println("registerSuccessfullresource: "  + registerSuccessfullresource);
		

        //Validation of contenttype, header and status code is done as under this method
		String registeremailresponse=ReusableApiMethods.reusbaleApiPostMethodResponse(URI, registerUserPayload, registerSuccessfullresource, expectedcontentType, expectedstatuscode, expectedheadername,expectedheaderval);
		
		JsonPath jsregisteremail = JsonPath.from(registeremailresponse);

		int Id = jsregisteremail.getInt("id");
		String token = jsregisteremail.get("token");
		
		
		//Validation of Json Response contents
		Assert.assertEquals(expectedId, Id);
		Assert.assertEquals(expectedtoken, token);
		return token; //return the token in the case where the token is passed to other api's

	}

}
