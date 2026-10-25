package testClass;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.http.ContentType;

public class BookingPostTest {
	
	@Test
	 public void BookingGetTest() {

	//Created data using post
	RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	
	RequestSpecification httpRequest = RestAssured.given();
	httpRequest.contentType(ContentType.JSON);
	
	JSONObject jsonobject = new JSONObject();
	jsonobject.put("firstname", "Kamesh");
	jsonobject.put("lastname", "Vaidya");
	jsonobject.put("totalprice",1500);
	jsonobject.put("depositpaid", true);
	jsonobject.put("additionalneeds", "Breakfast");
	
	JSONObject bookingdate = new JSONObject();
	bookingdate.put("checkin", "2027-01-10");
	bookingdate.put("checkout", "2027-01-15");
	
	jsonobject.put("bookingdates", bookingdate);

	
	httpRequest.body(jsonobject.toString());
	
	Response response = httpRequest.request(Method.POST, "/booking");
	
	int statusCode = response.getStatusCode();
	System.out.println(response.getHeader("Content-Type"));
	System.out.println(response.getBody().asString());

	Assert.assertEquals(statusCode, 200, "Status code is not correct");
	
	int bookingId = response.jsonPath().getInt("bookingid");
	Assert.assertNotNull(bookingId, "Booking id is null");
	
	//Retrieving data using Get
	Response getResponse = httpRequest.request(Method.GET, "/booking/" + bookingId);
	
	Assert.assertEquals(getResponse.getStatusCode(), 200, "Request Created successfully");
	
	System.out.println(getResponse.getBody().asString());
	
	System.out.println(getResponse.jsonPath().getString("firstname"));
	System.out.println(getResponse.jsonPath().getString("lastname"));
	System.out.println(getResponse.jsonPath().getInt("totalprice"));
	System.out.println(getResponse.jsonPath().getBoolean("depositpaid"));
	System.out.println(getResponse.jsonPath().getString("additionalneeds"));
	
	}
	

}
