import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Carriers_GET {

    private static final String AUTH_TOKEN = "sh428739766321522266746152871799";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://partners.api.skyscanner.net/apiservices/v3";
    }

    //Perform the unauthorized Access Test Case for GET Request
    @Test(priority = 1)
    public void testUnauthorizedAccess() {
        int statusCode = given()
                //.header("Authorization", "Bearer " + AUTH_TOKEN)
                .when()
                .get("/flights/carriers")
                .then()
                .extract()
                .statusCode();

        if(statusCode == 401) {
            System.out.println("Unauthorized Access test passed for flights/carriers(GET) endpoint.\nExpected status code: 401, Actual status code: " + statusCode);
        } else {
            System.out.println("Unauthorized Access test failed for flights/carriers(GET) endpoint.\nExpected status code: 401, Actual status code: " + statusCode);
        }

        Assert.assertEquals(statusCode, 401);
    }

    //Perform the invalid URL Test Case for GET Request
    @Test(priority = 2)
    public void testInvalidURL() {
        int statusCode = given()
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .when()
                .get("/flights/carriers1")
                .then()
                .extract()
                .statusCode();

        if(statusCode == 404) {
            System.out.println("Invalid URL test passed for flights/carriers(GET) endpoint.\nExpected status code: 404, Actual status code: " + statusCode);
        } else {
            System.out.println("Invalid URL test failed for flights/carriers(GET) endpoint.\nExpected status code: 404, Actual status code: " + statusCode);
        }

        Assert.assertEquals(statusCode, 404);
    }

    //Perform the Content Validation Test Case for GET Request
    @Test(priority = 3)
    public void testContentValidation() {
        try {
            given()
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .when()
                    .get("/flights/carriers")
                    .then()
                    .assertThat()
                    .statusCode(200) // Success
                    .contentType(ContentType.JSON)
                    .body("status", equalTo("RESULT_STATUS_COMPLETE"))
                    .body("carriers.-30510.name", equalTo("KM Malta Airlines"))
                    .body("carriers.-30510.iata", equalTo("KM"))
                    .body("carriers.-30510.icao", equalTo("KMM"))
                    .body("carriers.-30510.displayCode", equalTo("KM"));

            // Print success message
            System.out.println("Content validation for flights/carriers(GET) endpoint passed successfully!");
        } catch (AssertionError e) {
            // Print failure message
            System.out.println("Content validation failed for flights/carriers(GET) endpoint : " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed
        }
    }

    // Perform the GET request and validate the response
    @Test(priority = 4)
    public void testHTTPMethod() {
        Response response = given()
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .when()
                .get("/flights/carriers");


        int statusCode = response.getStatusCode();

        if(statusCode == 200) {
            System.out.println("HTTP method testing of flights/carriers(GET) endpoint is successful! Status code: " + statusCode);
            //Too large response body
            //System.out.println("Response body:");
           // System.out.println(response.getBody().asString());
        } else {
            System.out.println("HTTP method testing of flights/carriers(GET) endpoint is failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    // Perform the GET request with invalid method GET/PUT/PATCH/DELETE
    @Test(priority = 5)
    public void testInvalidMethod() {

        // Perform the GET request with invalid method GET/PUT/PATCH/DELETE
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                //.post("/flights/carriers");
                .put("/flights/carriers");
                //.patch("/flights/carriers");
                //.delete("/flights/carriers");


        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 405) {
            System.out.println("Different invalid method testing of flights/carriers(GET) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Different invalid method testing of flights/carriers(GET) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 405);
    }
}
