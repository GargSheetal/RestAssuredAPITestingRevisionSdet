package dataDrivenTesting;


import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

	public class TC008_StuentsAPI_DataProviderDDT {
	
	@Test(dataProvider = "studentDataProvider")
	public void postDataDrivenTest(String sName, String sLocation, String sPhone, List sCourses)
	{
		// Using Fake api for data driven testing
		RestAssured.baseURI = "http://localhost:3000";
		
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject params = new JSONObject();
		params.put("name", sName);
		params.put("location", sLocation);
		params.put("phone", sPhone);
		params.put("courses", Arrays.asList(sCourses));
		
		// Adding header stating request body is Json
		httprequest.header("Content-Type", "application/json");
		
		// Adding Json object to the request body
		httprequest.body(params.toJSONString());
		
		// POST request
		Response response = httprequest.request(Method.POST, "/students");
		
		// Capturing Response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("Response body is: " + responseBody);
		
		Assert.assertEquals(responseBody.contains(sName), true);
		Assert.assertEquals(responseBody.contains(sLocation), true);
		Assert.assertEquals(responseBody.contains(sPhone), true);
		
		// Validating status code
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 201);
		
	}
	
	
	@DataProvider(name = "studentDataProvider")
	Object[][] studentsData()
	{
		Object[][] stuData = {
				{"Eddie", "Paris", "4563452341", Arrays.asList("Python", "C#")},
				{"Bob", "Denmark", "6784563456", Arrays.asList("Cucumber", "Java")},
				{"Eddie", "Paris", "1234561234", Arrays.asList("Selenium", "Postman")}		
		};
		return stuData;
	}


}
