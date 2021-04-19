package api.functionaltestcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;

import java.util.Arrays;  
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;  
import api.reusableapimethods.ReusableApiMethods;
import api.supportingfiles.ApiPayload;
import api.supportingfiles.ApiResources;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/* 18th April 2021
 * Author: Amit Girme
 * This class is to validate the Get user list API
 * Scenarios which are considered for validation as below:
 * 1. Validate the status code
 * 2. Validate the content type
 * 3. Validate the Headername: Server
 * 4. Validate the the complete fields are received as Json Response like page, per_page, data array and support json etc.
 * 
 * */
public class GetUserListApiTest {
	
	final int expectedstatuscode=200;;
	final String paramqueryname= "page";
	final int paramqueryvalue=2;
	final String expectedheadername="Server";
	final String expectedheaderval="cloudflare";
	final String expectedcontentType="application/json";
	final int expectedpage=2;
	final int expectedper_page=6;
	final int expectedtotal=12;
	final int expectedtotal_pages=2;
	final int[] expectedID={7,8,9,10,11,12};
	final String[] expectedEmail={"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
	final String[] expectedfirst_name={"Michael","Lindsay","Tobias","Byron","George","Rachel"};
	final String[] expectedlast_name={"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
	final String[] expectedavatar={"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
	final String expectedsupporturl = "https://reqres.in/#support-heading";
	final String expectedsupporttext = "To keep ReqRes free, contributions towards server costs are appreciated!";

	
	public void listUser(String URI){	
		String listuserresource =ApiResources.getListOfUsersResource();
		System.out.println("listuserresource: "  + listuserresource);
		
        //Validation of contenttype, header and status code is done as under this method
		String listuserresponse=ReusableApiMethods.reusbaleApiGetMethodResponse(URI, paramqueryname, paramqueryvalue, listuserresource, expectedcontentType, expectedstatuscode, expectedheadername, expectedheaderval);
		
		JsonPath jslistuser = JsonPath.from(listuserresponse);
	
		int actualpage = jslistuser.getInt("page");
		int actualper_page = jslistuser.getInt("per_page");
		int actualtotal=jslistuser.getInt("total");
		int actualtotal_pages=jslistuser.getInt("total_pages");

		int size= jslistuser.getList("data").size();
		System.out.println(size);
		
		ArrayList<Integer> actualid = new ArrayList<Integer>();
		ArrayList<String> actualemail = new ArrayList<String>();
		ArrayList<String> actualfirst_name = new ArrayList<String>();
		ArrayList<String> actuallast_name = new ArrayList<String>();
		ArrayList<String> actualavatar = new ArrayList<String>();

		
		for(int i=0;i<size;i++){
			actualid.add(jslistuser.getInt("data["+i+"].id"));
			actualemail.add(jslistuser.get("data["+i+"].email"));
			actualfirst_name.add(jslistuser.get("data["+i+"].first_name"));
			actuallast_name.add(jslistuser.get("data["+i+"].last_name"));
			actualavatar.add(jslistuser.get("data["+i+"].avatar"));
		}
		
		List<Integer> listexpectedID =  Arrays.stream(expectedID).boxed().collect(Collectors.toList());
		List<String> listExpectedEmail = Arrays.asList(expectedEmail);
		List<String> listExpectedfirst_name = Arrays.asList(expectedfirst_name);
		List<String> listExpectedlast_name = Arrays.asList(expectedlast_name);
		List<String> listExpectedavatar = Arrays.asList(expectedavatar);

		
		String actualsupporturl = jslistuser.get("support.url");
		System.out.println(actualsupporturl);
		String actualsupporttext= jslistuser.get("support.text");
		System.out.println(actualsupporttext);

		//Validation of Json Response contents
		Assert.assertEquals(actualpage, expectedpage);
		Assert.assertEquals(actualper_page, expectedper_page);
		Assert.assertEquals(actualtotal, expectedtotal);
		Assert.assertEquals(actualtotal_pages, expectedtotal_pages);
		Assert.assertEquals(actualid, listexpectedID);
		Assert.assertEquals(actualemail, listExpectedEmail);
		Assert.assertEquals(actualfirst_name, listExpectedfirst_name);
		Assert.assertEquals(actuallast_name, listExpectedlast_name);
		Assert.assertEquals(actualavatar, listExpectedavatar);
		Assert.assertEquals(actualavatar, listExpectedavatar);
		Assert.assertEquals(actualsupporturl, expectedsupporturl);
		Assert.assertEquals(actualsupporttext, expectedsupporttext);


		
		
        
	}

}
