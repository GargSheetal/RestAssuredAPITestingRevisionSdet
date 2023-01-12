package in.reqres.apiTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_reqres_POSTNewUser {
	
	
	@Test
	void postNewUser()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		RequestSpecification httprequest = RestAssured.given();
		
		// Request Payload
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "John");
		requestParams.put("job", "Engineer");
		
		httprequest.header("Content-Type", "application/json");
		
		// Attach above data to the request
		httprequest.body(requestParams.toJSONString());
		
		// Response Object
		Response response = httprequest.request(Method.POST, "/api/users");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " + responseBody);
		
		// Status code validation
		int statusCode = response.getStatusCode();
		AssertJUnit.assertEquals(statusCode, 201);
		
		String statusLine = response.getStatusLine();
		System.out.println("Status line is: " + statusLine);
		
		// Validating response body has id
		String id = response.jsonPath().get("id");
		System.out.println("Id created is: " + id);
		AssertJUnit.assertTrue(responseBody.contains("id"));
		
		
		
	}
	

}
