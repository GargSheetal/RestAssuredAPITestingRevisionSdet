package in.reqres.apiTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC001_reqres_GETSingleUser {
	
	@Test
	void getSingleUser()
	{
		// Specify BaseURI
		RestAssured.baseURI = "https://reqres.in";
		
		// Request object creation
		RequestSpecification httprequest = RestAssured.given();
		
		// Creating Response object
		Response response = httprequest.request(Method.GET, "/api/users/2");
		
		// Printing Response on Console
		String responseBody = response.getBody().asString();
		
		System.out.println("Response body is " + responseBody);
		
		// Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is " + statusCode);
		
		AssertJUnit.assertEquals(statusCode, 200);
		
		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("Status line is " + statusLine);
		
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		// Validating Response Body
		AssertJUnit.assertEquals(responseBody.contains("2"), true);
		
	}
	

}
