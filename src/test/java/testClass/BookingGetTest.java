package testClass;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookingGetTest extends BaseTest {

	 @Test
	 public void BookingGetTest()  {
		 
//			RestAssured.baseURI  = "https://restful-booker.herokuapp.com";
			
			RequestSpecification httpRequest = RestAssured.given();
			
			Response response = httpRequest.request(Method.GET, "/booking");
			
			int statusCode = response.getStatusCode();
			
			// Validate status code
			Assert.assertEquals(statusCode, 200, "Status Code is incorrect");
			
			// Validate response content type
			Assert.assertTrue(response.getContentType().contains("json"), "Content Type is not JSON");
			
			 // Read the JSON array
			List<Map<String,Object>> bookings = response.jsonPath().getList("$");
		
			for(Map<String,Object> booking: bookings)
			{
				Assert.assertTrue(booking.containsKey("bookingid"), "Booking does not contain booking id");
			}
				
			System.out.println("Size of array: "+bookings.size());
	 }

			
			
}
