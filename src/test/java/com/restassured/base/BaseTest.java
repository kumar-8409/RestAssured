package com.restassured.base;

import com.restassured.config.APIConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = APIConfig.BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
        logger.info("Base URL set to: {}", APIConfig.BASE_URL);
    }
}
