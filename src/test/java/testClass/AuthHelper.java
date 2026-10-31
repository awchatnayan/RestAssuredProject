package testClass;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthHelper {


    public static String getToken() {

        JSONObject authBody = new JSONObject();
        authBody.put("username", "admin");
        authBody.put("password", "password123");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authBody.toString())
                .post("/auth");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException(
                    "Authentication failed. Status: "
                    + response.getStatusCode());
        }

        return response.jsonPath().getString("token");
    }



}
