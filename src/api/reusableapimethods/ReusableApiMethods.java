package api.reusableapimethods;

import static io.restassured.RestAssured.given;

public class ReusableApiMethods {
	
	
	public static String reusbaleApiPostMethodResponse(String URI, String payload, String resource,String contenttype, int statusCode, String headername,String headervalue)
	{
		String registeremailresponse= given().log().all().
			    baseUri(URI).
				header("Content-Type",contenttype).
				body(payload).
				when().
				post(resource).
		        then().
				assertThat().log().all().
				and().contentType(contenttype).and().statusCode(statusCode).and().header(headername, headervalue).
				extract().response().asString();
		return registeremailresponse;
	}
	
	public static String reusbaleApiGetMethodResponse(String URI, String queryname, int paramvalue, String resource,String contenttype, int statusCode, String headername, String headervalue)
	{
		String listuserresponse= given().log().all().
			    baseUri(URI).
				header("Content-Type",contenttype).queryParam(queryname, paramvalue).
				when().
				get(resource).
		        then().
				assertThat().log().all().
				and().contentType(contenttype).and().statusCode(statusCode).and().header(headername, headervalue).
				extract().response().asString();
		return listuserresponse;
	}
	
	public static String reusbaleApiDeleteMethodResponse(String URI, String resource, int statusCode, String headername, String headervalue)
	{
		String listuserresponse= given().log().all().
			    baseUri(URI).
				when().
				delete(resource).
		        then().
				assertThat().log().all().
				and().statusCode(statusCode).and().header(headername, headervalue).
				extract().response().asString();
		return listuserresponse;
	}
	
	


}
