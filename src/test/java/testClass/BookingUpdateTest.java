package testClass;

import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingUpdateTest extends BaseTest {
	
	@Test
	 public void BookingUpdateTest() {
		
//		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		RequestSpecification httpRequest = RestAssured.given();
		
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
	        
	     // Set content type and attach body BEFORE sending request
	        httpRequest.contentType(ContentType.JSON);
	        httpRequest.body(booking.toString());
	        
	    Response response = httpRequest.request(Method.POST,"/booking");   
	    int statusCode = response.getStatusCode();   
	    
	    Assert.assertEquals(statusCode, 200, "Status code is not created");
	    int bookingId = response.jsonPath().getInt("bookingid");
	    System.out.println("Created booking id is: "+bookingId);
	    System.out.println(response.getBody().asString());
	    
	    //Get the details
	    Response response1 = httpRequest.request(Method.GET,"/booking/"+bookingId);   
	    System.out.println(response1.getBody().asString());
	   
	    //Put Request and Patch Request 
	 // Get authentication token from /auth
//	    JSONObject authobj = new JSONObject();
//	    authobj.put("username", "admin");
//	    authobj.put("password", "password123");
//	   
//	    Response authResponse = RestAssured.given()
//	            .contentType(ContentType.JSON).body(authobj.toString()).post("/auth");
//	    Assert.assertEquals(authResponse.getStatusCode(), 200, "Token API not created");
//	    
//	   String token = authResponse.jsonPath().getString("token");
	    String token = AuthHelper.getToken();
	    
	   //Update the details using put
	   booking.put("firstname", "KameshNayan");
       booking.put("lastname", "Vaidyaa");
       booking.put("totalprice", 1800);
       booking.put("additionalneeds", "Lunch");
       
//       Response putResponse = RestAssured.given().contentType(ContentType.JSON).cookie("token",token).body(booking.toString()).put("/booking/"+bookingId);
       Response patchResponse = RestAssured.given().contentType(ContentType.JSON).cookie("token",token).body(booking.toString()).patch("/booking/"+bookingId);
   	
//       System.out.println("Status code is: "+putResponse.getStatusCode());
//       System.out.println("Body is: "+putResponse.getBody().asString());
   	
       System.out.println("Status code is: "+patchResponse.getStatusCode());
     System.out.println("Body is: "+patchResponse.getBody().asString());
 	

	
	
	}

}
