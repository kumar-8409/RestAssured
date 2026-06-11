package com.restassured.tests;

import com.restassured.base.BaseTest;
import com.restassured.config.APIConfig;
import com.restassured.utils.APIUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class PostsAPITest extends BaseTest {

    @Test(description = "Test GET all posts")
    public void testGetAllPosts() {
        logger.info("Test: Getting all posts");
        Response response = APIUtils.getRequest(APIConfig.POSTS_ENDPOINT);

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0]", hasKey("userId"))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("title"))
                .body("[0]", hasKey("body"));

        logger.info("✓ All posts retrieved successfully");
    }

    @Test(description = "Test GET post by ID")
    public void testGetPostById() {
        logger.info("Test: Getting post by ID");
        int postId = 1;
        
        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.POSTS_ENDPOINT + "/{id}",
                "id",
                postId
        );

        response.then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
                .body("body", notNullValue());

        logger.info("✓ Post with ID {} retrieved successfully", postId);
    }

    @Test(description = "Test GET posts by user ID")
    public void testGetPostsByUserId() {
        logger.info("Test: Getting posts by user ID");
        int userId = 1;
        
        Response response = APIUtils.getRequestWithQueryParam(
                APIConfig.POSTS_ENDPOINT,
                "userId",
                userId
        );

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("findAll {it.userId == " + userId + "}.size()", greaterThan(0));

        logger.info("✓ Posts for user {} retrieved successfully", userId);
    }

    @Test(description = "Test CREATE new post")
    public void testCreatePost() {
        logger.info("Test: Creating a new post");
        String postBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post created via REST Assured\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = APIUtils.postRequest(APIConfig.POSTS_ENDPOINT, postBody);

        response.then()
                .statusCode(201)
                .body("title", equalTo("Test Post"))
                .body("body", equalTo("This is a test post created via REST Assured"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());

        logger.info("✓ Post created successfully");
    }

    @Test(description = "Test UPDATE post")
    public void testUpdatePost() {
        logger.info("Test: Updating a post");
        int postId = 1;
        String updateBody = "{\n" +
                "  \"id\": " + postId + ",\n" +
                "  \"title\": \"Updated Test Post\",\n" +
                "  \"body\": \"This is an updated test post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = APIUtils.putRequest(
                APIConfig.POSTS_ENDPOINT + "/" + postId,
                updateBody
        );

        response.then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", equalTo("Updated Test Post"));

        logger.info("✓ Post updated successfully");
    }

    @Test(description = "Test DELETE post")
    public void testDeletePost() {
        logger.info("Test: Deleting a post");
        int postId = 1;

        Response response = APIUtils.deleteRequest(APIConfig.POSTS_ENDPOINT + "/" + postId);

        response.then()
                .statusCode(200);

        logger.info("✓ Post deleted successfully");
    }

    @Test(description = "Test GET post with invalid ID returns 404")
    public void testGetPostInvalidId() {
        logger.info("Test: Getting post with invalid ID");
        
        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.POSTS_ENDPOINT + "/{id}",
                "id",
                99999
        );

        response.then()
                .statusCode(404);

        logger.info("✓ Request handled correctly for invalid ID - returns 404");
    }
}
