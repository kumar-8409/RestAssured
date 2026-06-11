package com.restassured.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.restassured.utils.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {
    
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started: {}", context.getName());
        extentReports = ExtentReportManager.getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getMethod().getMethodName());
        
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        String className = result.getTestClass().getRealClass().getSimpleName();
        
        ExtentTest test = extentReports.createTest(testName, description)
                .assignCategory(className)
                .assignAuthor("REST Assured Framework");
        
        extentTest.set(test);
        
        test.info("Test execution started for: " + className + "." + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: {}", result.getMethod().getMethodName());
        
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.pass("✓ Test Passed Successfully");
            test.pass("Execution Time: " + getExecutionTime(result) + " ms");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: {}", result.getMethod().getMethodName());
        
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.fail("✗ Test Failed");
            test.fail("Failure Message: " + result.getThrowable().getMessage());
            test.fail("Stack Trace: " + getStackTrace(result.getThrowable()));
            test.fail("Execution Time: " + getExecutionTime(result) + " ms");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: {}", result.getMethod().getMethodName());
        
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.skip("⊘ Test Skipped");
            test.skip("Reason: " + result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Test failed but within success percentage: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished: {}", context.getName());
        
        int passedTests = context.getPassedTests().size();
        int failedTests = context.getFailedTests().size();
        int skippedTests = context.getSkippedTests().size();
        
        logger.info("Test Summary - Passed: {}, Failed: {}, Skipped: {}", 
                    passedTests, failedTests, skippedTests);
        
        ExtentReportManager.closeExtentReports();
    }

    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }

    private String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] elements = throwable.getStackTrace();
        for (StackTraceElement element : elements) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}