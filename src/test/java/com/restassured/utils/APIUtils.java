package com.restassured.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

public class APIUtils {
    private static final Logger logger = LoggerFactory.getLogger(APIUtils.class);

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static Response getRequest(String endpoint) {
        logger.info("Sending GET request to: {}", endpoint);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint);
    }

    public static Response getRequestWithPathParam(String endpoint, String pathParam, Object value) {
        logger.info("Sending GET request to: {} with pathParam: {} = {}", endpoint, pathParam, value);
        return given()
                .header("Content-Type", "application/json")
                .pathParam(pathParam, value)
                .when()
                .get(endpoint);
    }

    public static Response getRequestWithQueryParam(String endpoint, String queryParam, Object value) {
        logger.info("Sending GET request to: {} with queryParam: {} = {}", endpoint, queryParam, value);
        return given()
                .header("Content-Type", "application/json")
                .queryParam(queryParam, value)
                .when()
                .get(endpoint);
    }

    public static Response postRequest(String endpoint, String body) {
        logger.info("Sending POST request to: {} with body: {}", endpoint, body);
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response putRequest(String endpoint, String body) {
        logger.info("Sending PUT request to: {} with body: {}", endpoint, body);
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        logger.info("Sending DELETE request to: {}", endpoint);
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete(endpoint);
    }

    public static void printResponse(Response response) {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asString());
    }
}
