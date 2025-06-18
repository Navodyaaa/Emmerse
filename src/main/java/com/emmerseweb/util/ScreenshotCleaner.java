package com.emmerseweb.util;

import java.io.File;

public class ScreenshotCleaner {

  /** Deletes all files from the screenshots folder before the test run. */
  public static void clearScreenshotsFolder() {
    File folder = new File("src/main/resources/screenshots"); // Folder where screenshots are stored
    if (folder.exists() && folder.isDirectory()) {
      File[] files = folder.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            file.delete(); // Delete each screenshot file
          }
        }
      }
    } else {
      System.out.println("Screenshot folder does not exist or is empty.");
    }
  }
}