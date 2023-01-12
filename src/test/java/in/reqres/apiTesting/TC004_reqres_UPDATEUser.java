package in.reqres.apiTesting;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_reqres_UPDATEUser {

	@Test
	void updateUser()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		RequestSpecification httprequest = RestAssured.given();
		
		// Request Payload
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", "Mitch");
		requestparams.put("job", "Plumber");
		
		httprequest.header("Content-Type", "application/json");
		
		// Attach above data to the request
		httprequest.body(requestparams.toJSONString());
		
		Response response = httprequest.request(Method.PUT, "/api/users/8");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		// Capturing all headers from response
		Headers allheaders = response.headers();
		
		//Printing the header name and value
		for(Header header:allheaders)
		{
			System.out.println(header.getName() + "  :  " + header.getValue());
		}
		
	}
	
	
}
