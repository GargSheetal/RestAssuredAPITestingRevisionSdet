package in.reqres.apiTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_reqres_GET_ValidateResponseBody {
	
	@Test
	void getSingleUser()
	{
		// Specify BaseURI
		RestAssured.baseURI = "https://reqres.in";
		
		// Request object creation
		RequestSpecification httprequest = RestAssured.given();
		
		// Creating Response object
		Response response = httprequest.request(Method.GET, "/api/users/2");
		
		JsonPath jsonpath = response.jsonPath();
		int id = jsonpath.get("data.id");
		String email = jsonpath.get("data.email");
		String firstName = jsonpath.get("data.first_name");
		String lastName = jsonpath.get("data.last_name");
		String avatar = jsonpath.get("data.avatar");
		
		System.out.println("Id is: " + id);
		System.out.println("Email is: " + email);
		System.out.println("First name is: " + firstName);
		System.out.println("Last name is: " + lastName);
		System.out.println("Avatar is: " + avatar);
		
		AssertJUnit.assertEquals(id, 2);
		
	}

}
