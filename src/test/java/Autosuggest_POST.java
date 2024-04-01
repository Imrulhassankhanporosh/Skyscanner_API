import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Autosuggest_POST {

    private static final String AUTH_TOKEN = "sh428739766321522266746152871799";

    // Request body of valid parameters and value
     public String valid_requestBody = "{\"query\":{\"market\":\"UK\",\"locale\":\"en-GB\",\"searchTerm\":\"united\"},\"limit\":2}";

    //Request body wth invalid value of SearchTerm parameter
    public String invalid_requestBody_invalid_searchTerm = "{\"query\":{\"market\":\"UK\",\"locale\":\"en-GB\",\"searchTerm\":\"united12\"},\"limit\":2}";
    public String invalid_requestBody_with_max_limit = "{\"query\":{\"market\":\"UK\",\"locale\":\"en-GB\",\"searchTerm\":\"united12\"},\"limit\":200}";
    public String invalid_requestBody_invalid_locale = "{\"query\":{\"market\":\"UK\",\"locale\":\"en-GB123\",\"searchTerm\":\"united12\"},\"limit\":2}";

    public String invalid_requestBody_missing_market = "{\"query\":{\"locale\":\"en-GB\",\"searchTerm\":\"united12\"},\"limit\":2}";



    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://partners.api.skyscanner.net/apiservices/v3";

    }


    // Perform the POST request and validate the response
    @Test(priority = 1)
    public void testHTTPMethod() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("HTTP method testing of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("HTTP method testing of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }
        Assert.assertEquals(statusCode, 200);
    }


    // Perform the POST request with invalid value of searchTerm parameter
    @Test(priority = 2)
    public void testInvalidSearchTerm() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_invalid_searchTerm)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("With invalid value of SearchTerm parameter of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body is empty object of places parameter as the searchTerm is invalid:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("With invalid value of SearchTerm parameter of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
    @Test(priority = 3)
    public void testInvalidMethod() {

        // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                //.get("/autosuggest/flights");
                //.put("/autosuggest/flights");
                //.patch("/autosuggest/flights");
                .delete("/autosuggest/flights");


        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 405) {
            System.out.println("Different invalid method testing of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Different invalid method testing of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 405);
    }

    //Perform the unauthorized Access Test Case for POST Request
    @Test(priority = 4)
    public void testUnauthorizedAccess() {
        // Perform the POST request
        Response response = given()
                //.header("x-api-key", AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 401) {
            System.out.println("Unauthorized Access test passed for autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Unauthorized Access test passed for autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 401);

    }

    //Perform the invalid URL Test Case for POST Request
    @Test(priority = 5)
    public void testInvalidURL() {
        // Perform the POST request
        Response response = given()
                .header("x-api-key", AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/autosuggest/flights1");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            System.out.println("Invalid URL test for autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Invalid URL test for autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 404);
    }

    // Perform the POST request with max limit
    @Test(priority = 6)
    public void testExeceedingLimit() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_with_max_limit)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("max limit testing of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("max limit testing of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }
        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request with invalid value of locale parameter
    @Test(priority = 7)
    public void testInvalidLocale() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_invalid_locale)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case with invalid value of locale parameter of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with invalid value of locale parameter of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request without market parameter
    @Test(priority = 8)
    public void testMissingMarket() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_missing_market)
                .when()
                .post("/autosuggest/flights");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case without market parameter of autosuggest/flights(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body is empty object of places parameter as the searchTerm is invalid:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without market parameter of autosuggest/flights(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }


}
