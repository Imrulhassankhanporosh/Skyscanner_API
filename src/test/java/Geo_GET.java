import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Geo_GET {

    private static final String AUTH_TOKEN = "sh428739766321522266746152871799";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://partners.api.skyscanner.net/apiservices/v3/geo/hierarchy/flights";

    }

    // Perform the POST request and validate the response
    @Test(priority = 1)
    public void testHTTPMethod() {

        // Perform the GET request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .pathParams("locale","en-GB")
                .when()
                .get("/{locale}");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("HTTP method testing of hierarchy/flights(GET) endpoint successful! Status code: " + statusCode);
            //System.out.println("Response body:");
            //System.out.println(response.getBody().asString());
        } else {
            System.out.println("HTTP method testing of hierarchy/flights(GET) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }
        Assert.assertEquals(statusCode, 200);
    }

    // Perform the POST request and validate the response
    @Test(priority = 2)
    public void testMissingLocale() {

        // Perform the GET request without locale parameter
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                //.pathParams("locale","en-GB")
                .when()
                .get("/");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case without locale path parameter of hierarchy/flights(GET) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without locale path parameter of hierarchy/flights(GET) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    //Perform the unauthorized Access Test Case for GET Request
    @Test(priority = 3)
    public void testUnauthorizedAccess() {
        Response response = given()
                //.header("x-api-key",AUTH_TOKEN)
                .pathParams("locale","en-GB")
                .when()
                .get("/{locale}");

        int statusCode = response.getStatusCode();
        if(statusCode == 401) {
            System.out.println("Unauthorized Access test passed for hierarchy/flights(GET) endpoint. Actual status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Unauthorized Access test failed for hierarchy/flights(GET) endpoint. Actual status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 401);
    }

    //Perform the invalid URL Test Case for GET Request
    @Test(priority = 4)
    public void testInvalidURL() {
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .pathParams("locale","en-GB")
                .when()
                .get("https://partners.api.skyscanner.net/apiservices/v3/geo/hierarchy/flight1s/{locale}");

        int statusCode = response.getStatusCode();
        if(statusCode == 404) {
            System.out.println("Invalid URL test passed for hierarchy/flights(GET)(GET) endpoint. Actual status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Invalid URL test failed for hierarchy/flights(GET)(GET) endpoint. Actual status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 404);
    }

    // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
    @Test(priority = 5)
    public void testInvalidMethod() {

        // Perform the GET request with invalid method POST/PUT/PATCH/DELETE
        try {
            given()
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .pathParams("locale","en-GB")
                    .when()
                    .put("/{locale}")
                    .then()
                    .assertThat()
                    .statusCode(405);

            // Print success message
            System.out.println("Test case of different invalid method testing of hierarchy/flights(GET) endpoint passed successfully!");
        } catch (AssertionError e) {
            // Print failure message
            System.out.println("Test case of different invalid method testing of hierarchy/flights(GET) endpoint failed : " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }

}
