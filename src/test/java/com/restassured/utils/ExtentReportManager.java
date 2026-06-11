package com.restassured.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    
    private static ExtentReports extentReports;
    private static final String REPORTS_PATH = "extent-reports";

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = createExtentReports();
        }
        return extentReports;
    }

    private static ExtentReports createExtentReports() {
        // Create reports directory if not exists
        File reportsDir = new File(REPORTS_PATH);
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        // Generate timestamp for report name
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = REPORTS_PATH + "/ExtentReport_" + timestamp + ".html";

        // Create ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configure reporter
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("REST Assured - API Automation Report");
        sparkReporter.config().setReportName("REST Assured Test Execution Report");
        sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

        // Create ExtentReports and attach reporter
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Set system information
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Environment", "REST Assured Testing");
        extentReports.setSystemInfo("Base URL", "https://jsonplaceholder.typicode.com");
        extentReports.setSystemInfo("Framework", "REST Assured 5.3.1");
        extentReports.setSystemInfo("Test Runner", "TestNG");

        return extentReports;
    }

    public static void closeExtentReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
