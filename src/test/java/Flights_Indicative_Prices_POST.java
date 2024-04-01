import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Flights_Indicative_Prices_POST {

    private static final String AUTH_TOKEN = "sh428739766321522266746152871799";
    // Request body
    public String valid_requestBody ="{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB\",\"market\":\"UK\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";
    public String invalid_requestBody_with_missing_destinationPlace ="{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB\",\"market\":\"UK\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";
    public String invalid_requestBody_with_invalid_date_range = "{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB\",\"market\":\"UK\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":1}}}]}}";
    public String valid_requestBody_without_optional_parameter = "{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB\",\"market\":\"UK\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";
    public String invalid_requestBody_invalid_locale ="{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB123\",\"market\":\"UK\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";
    public String invalid_requestBody_missing_market ="{\"query\":{\"currency\":\"GBP\",\"locale\":\"en-GB\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";
    public String invalid_requestBody_missing_currency ="{\"query\":{\"locale\":\"en-GB\",\"market\":\"UK\",\"dateTimeGroupingType\":\"DATE_TIME_GROUPING_TYPE_BY_MONTH\",\"queryLegs\":[{\"originPlace\":{\"queryPlace\":{\"entityId\":\"27544008\"}},\"destinationPlace\":{\"queryPlace\":{\"entityId\":\"27539733\"}},\"date_range\":{\"startDate\":{\"year\":2024,\"month\":2},\"endDate\":{\"year\":2024,\"month\":5}}}]}}";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://partners.api.skyscanner.net/apiservices/v3";

    }

    // Perform the POST request without destinationPlace parameter
    @Test(priority = 1)
    public void testMissingDestinationPlace() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_with_missing_destinationPlace)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case without destinationPlace parameter of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without destinationPlace parameter of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request with invalid value of date range parameter
    @Test(priority = 2)
    public void testInvalidDateRange() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_with_invalid_date_range)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case with invalid value of date range parameter of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with invalid value of date range parameter of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request without optional parameter
    @Test(priority = 3)
    public void testWithoutOptionalParameter() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody_without_optional_parameter)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Test case without optional parameter(dateTimeGroupingType) of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
           // System.out.println("Expected response body :");
           // System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without optional parameter(dateTimeGroupingType) of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    // Perform the POST request and validate the response
    @Test(priority = 4)
    public void testHTTPMethod() {
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/flights/indicative/search");


        int statusCode = response.getStatusCode();

        if(statusCode == 200) {
            System.out.println("HTTP method testing of flights/indicative/search(POST) endpoint is successful! Status code: " + statusCode);
            //large response body
            //System.out.println("Response body:");
            //System.out.println(response.getBody().asString());
        } else {
            System.out.println("HTTP method testing of flights/indicative/search(POST) endpoint is failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 200);
    }

    //Perform the unauthorized Access Test Case for POST Request
    @Test(priority = 5)
    public void testUnauthorizedAccess() {
        // Perform the POST request
        Response response = given()
                //.header("x-api-key", AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 401) {
            System.out.println("Unauthorized Access test passed for flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Unauthorized Access test passed for flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 401);

    }

    //Perform the invalid URL Test Case for POST Request
    @Test(priority = 6)
    public void testInvalidURL() {
        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                .post("/flights/indicative1/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            System.out.println("Invalid URL test for flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Response body:");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Invalid URL test for flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 404);
    }

    // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
    @Test(priority = 7)
    public void testInvalidMethod() {

        // Perform the POST request with invalid method GET/PUT/PATCH/DELETE
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(valid_requestBody)
                .when()
                //.get("/flights/indicative/search");
                //.put("/flights/indicative/search");
                .patch("/flights/indicative/search");
                //.delete("/flights/indicative/search");


        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 405) {
            System.out.println("Different invalid method testing of flights/indicative1/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Different invalid method testing of flights/indicative1/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 405);
    }

    // Perform the POST request with invalid locale parameter
    @Test(priority = 8)
    public void testWithInvalidLocale() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_invalid_locale)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case with invalid locale parameter of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
             System.out.println("Expected response body :");
             System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case with invalid locale parameter of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request without market parameter
    @Test(priority = 9)
    public void testWithoutMarketParameter() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_missing_market)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case without market parameter of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
             System.out.println("Expected response body :");
             System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without market parameter of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }

    // Perform the POST request without currency parameter
    @Test(priority = 10)
    public void testWithoutCurrencyParameter() {

        // Perform the POST request
        Response response = given()
                .header("x-api-key",AUTH_TOKEN)
                .contentType(ContentType.JSON)
                .body(invalid_requestBody_missing_currency)
                .when()
                .post("/flights/indicative/search");

        // Validate the response
        int statusCode = response.getStatusCode();
        if (statusCode == 400) {
            System.out.println("Test case without currency parameter of flights/indicative/search(POST) endpoint successful! Status code: " + statusCode);
            System.out.println("Expected response body :");
            System.out.println(response.getBody().asString());
        } else {
            System.out.println("Test case without currency parameter of flights/indicative/search(POST) endpoint failed! Status code: " + statusCode);
            System.out.println("Error message:");
            System.out.println(response.getBody().asString());
        }

        Assert.assertEquals(statusCode, 400);
    }
}
