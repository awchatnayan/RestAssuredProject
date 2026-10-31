package testClass;

import org.testng.Assert;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class BookingNegativeTest extends BaseTest {

	    @Test
	    public void getBookingWithInvalidIdTest() {

	        // Request a booking ID that should not exist
	        Response response = RestAssured.given()
	                .get("/booking/999999999");

	        System.out.println("Invalid ID Status: "
	                + response.getStatusCode());

	        Assert.assertEquals(response.getStatusCode(), 404);
	    }

	    @Test
	    public void createBookingWithMissingFieldsTest() {

	        // Send an incomplete booking
	        JSONObject booking = new JSONObject();
	        booking.put("firstname", "Kamesh");

	        Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(booking.toString())
	                .post("/booking");

	        System.out.println("Missing Fields Status: "
	                + response.getStatusCode());

	        // RESTful Booker may reject incomplete input
	        Assert.assertTrue(
	                response.getStatusCode() >= 400,
	                "Expected an error status for incomplete booking");
	    }

	    @Test
	    public void updateBookingWithoutTokenTest() {

	        Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body("{}")
	                .put("/booking/1");

	        System.out.println("No Token Status: "
	                + response.getStatusCode());

	        Assert.assertEquals(response.getStatusCode(), 403);
	    }
}
