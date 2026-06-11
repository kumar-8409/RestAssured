package com.restassured.tests;

import com.restassured.base.BaseTest;
import com.restassured.config.APIConfig;
import com.restassured.utils.APIUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UsersAPITest extends BaseTest {

    @Test(description = "Test GET all users")
    public void testGetAllUsers() {
        logger.info("Test: Getting all users");
        Response response = APIUtils.getRequest(APIConfig.USERS_ENDPOINT);

        response.then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("email"))
                .body("[0]", hasKey("username"));

        logger.info("✓ All users retrieved successfully");
    }

    @Test(description = "Test GET user by ID")
    public void testGetUserById() {
        logger.info("Test: Getting user by ID");
        int userId = 1;

        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.USERS_ENDPOINT + "/{id}",
                "id",
                userId
        );

        response.then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("address", hasKey("street"))
                .body("address", hasKey("city"));

        logger.info("✓ User with ID {} retrieved successfully", userId);
    }

    @Test(description = "Test user has valid email format")
    public void testUserEmailValidation() {
        logger.info("Test: Validating user email format");
        
        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.USERS_ENDPOINT + "/{id}",
                "id",
                1
        );

        response.then()
                .statusCode(200)
                .body("email", matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"));

        logger.info("✓ User email format is valid");
    }

    @Test(description = "Test user address structure")
    public void testUserAddressStructure() {
        logger.info("Test: Validating user address structure");
        
        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.USERS_ENDPOINT + "/{id}",
                "id",
                2
        );

        response.then()
                .statusCode(200)
                .body("address.street", notNullValue())
                .body("address.suite", notNullValue())
                .body("address.city", notNullValue())
                .body("address.zipcode", notNullValue())
                .body("address.geo", hasKey("lat"))
                .body("address.geo", hasKey("lng"));

        logger.info("✓ User address structure is valid");
    }

    @Test(description = "Test user company information")
    public void testUserCompanyInfo() {
        logger.info("Test: Validating user company information");
        
        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.USERS_ENDPOINT + "/{id}",
                "id",
                3
        );

        response.then()
                .statusCode(200)
                .body("company.name", notNullValue())
                .body("company.catchPhrase", notNullValue())
                .body("company.bs", notNullValue());

        logger.info("✓ User company information is present");
    }

    @Test(description = "Test CREATE new user")
    public void testCreateUser() {
        logger.info("Test: Creating a new user");
        String userBody = "{\n" +
                "  \"name\": \"Test User\",\n" +
                "  \"email\": \"testuser@example.com\",\n" +
                "  \"username\": \"testuser123\",\n" +
                "  \"phone\": \"1234567890\"\n" +
                "}";

        Response response = APIUtils.postRequest(APIConfig.USERS_ENDPOINT, userBody);

        response.then()
                .statusCode(201)
                .body("name", equalTo("Test User"))
                .body("email", equalTo("testuser@example.com"))
                .body("id", notNullValue());

        logger.info("✓ User created successfully");
    }

    @Test(description = "Test response headers contain required fields")
    public void testResponseHeaders() {
        logger.info("Test: Validating response headers");
        
        Response response = APIUtils.getRequest(APIConfig.USERS_ENDPOINT);

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"));

        logger.info("✓ Response headers are valid");
    }
}
