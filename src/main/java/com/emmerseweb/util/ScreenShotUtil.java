package com.emmerseweb.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {
    public static void captureScreenshot(WebDriver driver, String testName) {
        // Generate a timestamp to avoid overwriting files
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define screenshot file path
        String filePath =
                PropertyResolverUtil.getApplicationProperties()
                        .getProperty(AppConstant.PropertyName.SCREEN_SHOT_BASE_FOLDER)
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        try {
            // Create the directory if it doesn't exist
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
