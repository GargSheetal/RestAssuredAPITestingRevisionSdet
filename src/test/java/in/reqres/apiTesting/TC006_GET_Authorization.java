package in.reqres.apiTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_Authorization {
	
	@Test
	public void authorizationTest()
	{
		// Specify base URI
		RestAssured.baseURI = "https://postman-echo.com/basic-auth";
		
		// Basic Authentication
		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName("postman");
		basicAuth.setPassword("password");
		
		RestAssured.authentication = basicAuth;
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// Response object 
		Response response = httpRequest.request(Method.GET, "/");	// as there is no parameter

		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
		
		
		
	}

}
