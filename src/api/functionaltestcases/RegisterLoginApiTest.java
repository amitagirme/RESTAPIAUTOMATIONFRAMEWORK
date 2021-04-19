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
 * This class is to validate the Register Login API
 * Scenarios which are considered for validation as below:
 * 1. Validate the status code
 * 2. Validate the content type
 * 3. Validate the Headername: Server
 * 4. Validate the the complete fields are received as Json Response:
 * {
    "token": "QpwL5tke4Pnpja7X4"
}
 * */

public class RegisterLoginApiTest {
	
	int expectedstatuscode=200;
	String expectedtoken="QpwL5tke4Pnpja7X4";
	
	String expectedcontentType="application/json";
	String expectedheadername="Server";
	String expectedheaderval="cloudflare";
	final String loginEmailId="eve.holt@reqres.in";
	final String loginPassword="cityslicka";

	
	public String registerLogin(String URI){
		
		String registerloginPayload=ApiPayload.loginSuccessfull(loginEmailId,loginPassword);
		System.out.println("registerloginPayload: "  + registerloginPayload);

		String loginSuccessfullresource =ApiResources.loginSuccessfullResource();
		System.out.println("loginSuccessfullresource: "  + loginSuccessfullresource);
		
        //Validation of contenttype, header and status code is done as under this method
		String loginresponse=ReusableApiMethods.reusbaleApiPostMethodResponse(URI, registerloginPayload, loginSuccessfullresource, expectedcontentType, expectedstatuscode, expectedheadername,expectedheaderval);
		
		JsonPath jsregisteremail = JsonPath.from(loginresponse);

		String token = jsregisteremail.get("token");
		
		//Validation of Json Response contents
		Assert.assertEquals(expectedtoken, token);
		return token; //return the token in the case where the token is passed to other api's

	}

}
