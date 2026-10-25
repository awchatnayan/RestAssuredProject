package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
public class HealthCheckTest {
	
	 @Test
	    public void verifyApiHealth() {
		 
		 RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		 
		 RequestSpecification httpRequest = RestAssured.given();

		 Response response = httpRequest.request(Method.GET, "/ping");
		 
		 String body = response.getBody().asString();
		 int statusCode = response.getStatusCode();
		 
		 System.out.println("Response body: "+body);
		 Assert.assertEquals(statusCode, 201, "Status code is incorrect");
		 
		 
		 //Question: How do you send a GET request using REST Assured?
		 //A good answer for your experience level:“I set the base URI using RestAssured.baseURI. 
		 //Then I create a RequestSpecification using RestAssured.given(). 
		 //I send the GET request using the request() method and store the result in a Response object. 
		 //Finally, I extract the status code and response body and validate the expected result using TestNG assertions.”
	  
	 
	 }

}
