package com.restassured.tests;

import com.restassured.base.BaseTest;
import com.restassured.config.APIConfig;
import com.restassured.utils.APIUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CommentsAPITest extends BaseTest {

    @Test(description = "Test GET all comments")
    public void testGetAllComments() {
        logger.info("Test: Getting all comments");
        Response response = APIUtils.getRequest(APIConfig.COMMENTS_ENDPOINT);

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0]", hasKey("postId"))
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("email"))
                .body("[0]", hasKey("body"));

        logger.info("✓ All comments retrieved successfully");
    }

    @Test(description = "Test GET comment by ID")
    public void testGetCommentById() {
        logger.info("Test: Getting comment by ID");
        int commentId = 1;

        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.COMMENTS_ENDPOINT + "/{id}",
                "id",
                commentId
        );

        response.then()
                .statusCode(200)
                .body("id", equalTo(commentId))
                .body("postId", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("body", notNullValue());

        logger.info("✓ Comment with ID {} retrieved successfully", commentId);
    }

    @Test(description = "Test GET comments by post ID")
    public void testGetCommentsByPostId() {
        logger.info("Test: Getting comments by post ID");
        int postId = 1;

        Response response = APIUtils.getRequestWithQueryParam(
                APIConfig.COMMENTS_ENDPOINT,
                "postId",
                postId
        );

        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("findAll {it.postId == " + postId + "}.size()", greaterThan(0));

        logger.info("✓ Comments for post {} retrieved successfully", postId);
    }

    @Test(description = "Test comment email validation")
    public void testCommentEmailValidation() {
        logger.info("Test: Validating comment email format");

        Response response = APIUtils.getRequestWithPathParam(
                APIConfig.COMMENTS_ENDPOINT + "/{id}",
                "id",
                1
        );

        response.then()
                .statusCode(200)
                .body("email", matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"));

        logger.info("✓ Comment email format is valid");
    }

    @Test(description = "Test CREATE new comment")
    public void testCreateComment() {
        logger.info("Test: Creating a new comment");
        String commentBody = "{\n" +
                "  \"postId\": 1,\n" +
                "  \"name\": \"Test Comment\",\n" +
                "  \"email\": \"test@example.com\",\n" +
                "  \"body\": \"This is a test comment\"\n" +
                "}";

        Response response = APIUtils.postRequest(APIConfig.COMMENTS_ENDPOINT, commentBody);

        response.then()
                .statusCode(201)
                .body("name", equalTo("Test Comment"))
                .body("email", equalTo("test@example.com"))
                .body("id", notNullValue());

        logger.info("✓ Comment created successfully");
    }

    @Test(description = "Test UPDATE comment")
    public void testUpdateComment() {
        logger.info("Test: Updating a comment");
        int commentId = 1;
        String updateBody = "{\n" +
                "  \"id\": " + commentId + ",\n" +
                "  \"postId\": 1,\n" +
                "  \"name\": \"Updated Comment\",\n" +
                "  \"email\": \"updated@example.com\",\n" +
                "  \"body\": \"This is an updated comment\"\n" +
                "}";

        Response response = APIUtils.putRequest(
                APIConfig.COMMENTS_ENDPOINT + "/" + commentId,
                updateBody
        );

        response.then()
                .statusCode(200)
                .body("name", equalTo("Updated Comment"));

        logger.info("✓ Comment updated successfully");
    }

    @Test(description = "Test DELETE comment")
    public void testDeleteComment() {
        logger.info("Test: Deleting a comment");
        int commentId = 1;

        Response response = APIUtils.deleteRequest(APIConfig.COMMENTS_ENDPOINT + "/" + commentId);

        response.then()
                .statusCode(200);

        logger.info("✓ Comment deleted successfully");
    }
}
