package dataDrivenTesting;

import java.util.Arrays;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_StudentsAPI_DataDrivenTesting {
	
	@Test
	public void postDataDrivenTest()
	{
		// Using Fake api for data driven testing
		RestAssured.baseURI = "http://localhost:3000";
		
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject params = new JSONObject();
		params.put("name", "Mohan");
		params.put("location", "Scotland");
		params.put("phone", "5467891234");
		params.put("courses", Arrays.asList("RestAssured", "Cucumber"));
		
		// Adding header stating request body is Json
		httprequest.header("Content-Type", "application/json");
		
		// Adding Json object to the request body
		httprequest.body(params.toJSONString());
		
		// POST request
		Response response = httprequest.request(Method.POST, "/students");
		
		// Capturing Response body to perform validations
		String responseBody = response.getBody().asString();
		
		Assert.assertEquals(responseBody.contains("Mohan"), true);
		Assert.assertEquals(responseBody.contains("Scotland"), true);
		Assert.assertEquals(responseBody.contains("5467891234"), true);
		
		// Validating status code
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 201);
		
	}
	
}
