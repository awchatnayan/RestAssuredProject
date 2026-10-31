package testClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {

    @BeforeClass
    public void setup() {

        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties")) {

            properties.load(file);

            RestAssured.baseURI =
                    properties.getProperty("baseURI");

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e);
        }
   


	}
}
