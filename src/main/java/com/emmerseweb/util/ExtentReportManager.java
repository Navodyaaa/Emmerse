package com.emmerseweb.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();
    private static final ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent-report.html");
    private static ExtentTest test;

    public static void initReport() {
        extentReports.attachReporter(sparkReporter);
    }

    public static void createTest(String testName) {
        test = extentReports.createTest(testName);
    }

    public static void flushReport() {
        extentReports.flush();
    }
} 