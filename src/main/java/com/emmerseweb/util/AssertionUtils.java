package com.emmerseweb.util;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.List;

public class AssertionUtils {
    private final WebDriver driver;

    public AssertionUtils(WebDriver driver) {
        this.driver = driver;
    }

    public static void assertEqualsWithScreenshot(WebDriver driver, List<String> actual, List<String> expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            // Take screenshot on failure
            throw e;
        }
    }

    public static void assertTextContainsWithScreenshot(WebDriver driver, List<String> actual, String expected, String message) {
        try {
            actual.forEach(text -> Assert.assertTrue(text.contains(expected), message));
        } catch (AssertionError e) {
            // Take screenshot on failure
            throw e;
        }
    }
} 