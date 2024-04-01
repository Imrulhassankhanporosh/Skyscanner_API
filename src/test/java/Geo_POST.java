import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Geo_POST {

    private static final String AUTH_TOKEN = "sh428739766321522266746152871799";
    // Request body
    public String valid_requestBody ="{\"locale\":\"en-GB\",\"locator\":{\"ipAddress\":\"212.185.186.162\"}}";
    public String invalid_requestBody_with_invalid_locale ="{\"locale\":\"en-GB123\",\"locator\":{\"ipAddress\":\"212.185.186.162\"}}";
    public String valid_requestBody_with_additional_body = "{\"locale\":\"en-GB\",\"locator\":{\"ipAddress\":\"212.185.186.162\"},\"additionalField\":\"value\"}";
    public String invalid_requestBody = "{\"locale\":\"en-GB\",\"locator\":{\"ipAddress\":\"212.185.186.162.455554\"}}";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://partners.api.skyscanner.net/apiservices/v3";

    }

    // Perform the POST request with invalid value of ipAddress parameter
    @Test(priority = 1)
    public void testInvalidIpAddress() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody)
                .when()
                .post("/geo/hierarchy/flights/nearest");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case with invalid value of ipAddress parameter of geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with invalid value of ipAddress parameter of geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request with additional parameter
    @Test(priority = 2)
    public void testAdditionalField() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody_with_additional_body)
                .when()
                .post("/geo/hierarchy/flights/nearest");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Test case with additional parameter of geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with additional parameter of geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    // Perform the POST request without API key
    @Test(priority = 3)
    public void testUnauthorizedAccess() {

        // Perform the POST request
        Response response = given()
                //.header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody_with_additional_body)
                .when()
                .post("/geo/hierarchy/flights/nearest");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 401) {
            System.out.println("Test case without API key of geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without API key of geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 401);
    }

    // Perform the POST request and validate the response
    @Test(priority = 4)
    public void testHTTPMethod() {
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/geo/hierarchy/flights/nearest");


        int statusCode = response.getStatusCode();

        if(statusCode == 200) {
            System.out.println("HTTP method testing of geo/hierarchy/flights/nearest(POST) endpoint is successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("HTTP method testing of geo/hierarchy/flights/nearest(POST) endpoint is failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    //Perform the invalid URL Test Case for POST Request
    @Test(priority = 5)
    public void testInvalidURL() {
        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/geo/hierarchy/flights1/nearest");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            System.out.println("Invalid URL test for geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Invalid URL test for geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 404);
    }

    // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
    @Test(priority = 6)
    public void testInvalidMethod() {

        // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                //.get("/geo/hierarchy/flights/nearest");
                //.put("/geo/hierarchy/flights/nearest");
                //.patch("/geo/hierarchy/flights/nearest");
                .delete("/geo/hierarchy/flights/nearest");


        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 405) {
            System.out.println("Different invalid method testing of geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Different invalid method testing of geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 405);
    }

    // Perform the POST request with invalid value of locale parameter
    @Test(priority = 7)
    public void testInvalidLocale() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_with_invalid_locale)
                .when()
                .post("/geo/hierarchy/flights/nearest");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case with invalid value of locale parameter of geo/hierarchy/flights/nearest(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with invalid value of locale parameter of geo/hierarchy/flights/nearest(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }
}
