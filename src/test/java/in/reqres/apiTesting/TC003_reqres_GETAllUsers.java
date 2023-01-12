package in.reqres.apiTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_reqres_GETAllUsers {
	
	@Test
	void getAllUsers()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/api/users?page=2");
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response body is: " + responseBody);
		
		// Validating headers from response
		String contentType = response.getHeader("Content-Type");
		System.out.println("Content type is: " + contentType);
		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
		
		String contentEncoding = response.getHeader("Content-Encoding");
		System.out.println("Content encoding is: " + contentEncoding);
		AssertJUnit.assertEquals(contentEncoding, "gzip");
		
	}
	

}
