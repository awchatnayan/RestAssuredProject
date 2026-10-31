package testClass;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingDeleteTest extends BaseTest{
	
	@Test
    public void deleteBookingTest() {
		
//		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		//Create bookin data
		JSONObject booking = new JSONObject();
		booking.put("firstname", "Kamesh");
        booking.put("lastname", "Vaidya");
        booking.put("totalprice", 1500);
        booking.put("depositpaid", true);
        booking.put("additionalneeds", "Breakfast");

        JSONObject dates = new JSONObject();
        dates.put("checkin", "2027-01-10");
        dates.put("checkout", "2027-01-15");
        booking.put("bookingdates", dates);
        
        Response response = RestAssured.given().contentType(ContentType.JSON).body(booking.toString()).post("/booking");
		
		int bookingId = response.jsonPath().getInt("bookingid");
		System.out.println("Booking id is: "+bookingId);
		
		//Token generation
//		JSONObject auth = new JSONObject();
//		auth.put("username", "admin");
//		auth.put("password", "password123");
//		
//		Response tokenResponse = RestAssured.given().contentType(ContentType.JSON).body(auth.toString()).post("/auth");
//		
//		Assert.assertEquals(tokenResponse.getStatusCode(), 200, "Token not created successfully");
//		String token = tokenResponse.jsonPath().getString("token");
		 String token = AuthHelper.getToken();
		
		//Delete request
		Response deleteResponse = RestAssured.given().cookie("token",token).delete("/booking/"+bookingId);
		
		System.out.println(deleteResponse.getStatusCode());
		System.out.println(deleteResponse.getStatusLine());
		Assert.assertEquals(deleteResponse.getStatusCode(), 201, "Getting wrong status code");
		
		//Verify booking has been Deleted
		Response getResponse = RestAssured.given().get("/booking/"+bookingId);
		System.out.println(getResponse.getStatusCode());
		
		
	}

}
