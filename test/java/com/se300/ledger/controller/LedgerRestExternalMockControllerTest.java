package com.se300.ledger.controller;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class LedgerRestExternalMockControllerTest {
    @Test
    void testGetAccountById() throws JSONException {

        String expectedJson = "{\"address\" : \"1\", \"balance\" : 38}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get("https://6546c4c1902874dff3abd4bd.mockapi.io/api/accounts/1")
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(),true);
    }

    @Test
    public void testGetTransactionById() throws JSONException {

        // Mock API endpoint for testing
        String transactionApiEndpoint = "https://6546c4c1902874dff3abd4bd.mockapi.io/api/transactions/11";

        // Assuming your expected JSON for a transaction
        String expectedJson = "{\"amount\":23,\"fee\":29,\"note\":\"note 11\",\"payer\":{\"address\":\"master\",\"balance\":2147483647},\"receiver\":{\"address\":\"sergey\",\"balance\":10},\"transactionId\":\"11\"}";

        ExtractableResponse<Response> response = RestAssured
                .given()
                .filter(new RequestLoggingFilter())
                .auth().basic("sergey", "chapman")
                .contentType(ContentType.JSON)
                .when()
                .get(transactionApiEndpoint)
                .then()
                .statusCode(200)
                .extract();

        JSONAssert.assertEquals(expectedJson, response.body().asPrettyString(), true);
    }
}
