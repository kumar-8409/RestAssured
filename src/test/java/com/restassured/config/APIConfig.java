package com.restassured.config;

public class APIConfig {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String POSTS_ENDPOINT = "/posts";
    public static final String USERS_ENDPOINT = "/users";
    public static final String COMMENTS_ENDPOINT = "/comments";
    
    public static final int TIMEOUT_MS = 5000;
    public static final int MAX_RETRIES = 3;
}
