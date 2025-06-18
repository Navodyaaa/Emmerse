package com.emmerseweb.test;

import static com.emmerseweb.util.AppConstant.EXPLICIT_WAIT_DURATION;

import com.emmerseweb.exception.GlobalExceptionHandler;
import com.emmerseweb.util.AssertionUtils;
import com.emmerseweb.util.ExtentReportManager;
import com.emmerseweb.util.ScreenshotCleaner;
import com.emmerseweb.util.SupportUtil;
import com.emmerseweb.util.fileio.Excel;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BaseTest {
  private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(BaseTest.class);
  protected WebDriver driver;
  protected WebDriverWait wait;
  protected AssertionUtils assertionUtils;

  protected String fileName = "src/main/resources/TestData/Admin/Auth/Auth.xlsx";
  public Logger logger;

  @BeforeMethod
  @Parameters("browser")
  public void initBrowser(@Optional("Chrome") String browser) {

    logger = LogManager.getLogger();
    driver = SupportUtil.getDriver(browser);
    driver.manage().window().maximize();
    assertionUtils = new AssertionUtils(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_DURATION));
  }

  @BeforeMethod
  public void initTest(Method method) {
    ExtentReportManager.createTest(method.getName());
  }

  @BeforeSuite
  public void setup() {

    Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
    ExtentReportManager.initReport();
    System.out.println("cleaner screenshot");
    ScreenshotCleaner.clearScreenshotsFolder(); // Clear previous screenshots
  }

  @AfterSuite
  public void flushReport() {
    ExtentReportManager.flushReport();
  }

  @AfterMethod
  public void quit() {

    if (Objects.nonNull(driver)) {
      driver.quit();
    }
  }

  @DataProvider(name = "dataProvider")
  public Iterator<Object> commonDataProvider(Method method) {

    Class<?>[] parameterTypes = method.getParameterTypes();
    Class<?> aClass = Arrays.stream(parameterTypes).findFirst().get();

    List<Object> excelData =
        (List<Object>) 
        Excel.readExcelData(fileName, method.getAnnotation(Test.class).description(), aClass);

    return excelData.iterator();
  }



  // Verify if URL is correct
  public void assertUrl(String expectedUrl, String onErrorMessage) {
    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, onErrorMessage);
  }

  // Verify if Page Title is correct
  public void assertTitle(String expectedTitle) {
    Assert.assertEquals(driver.getTitle(), expectedTitle, "Title Mismatch!");
  }

  // Verify if Element Text Matches
  public void assertElementText(String actualText, String expectedText) {
    Assert.assertEquals(actualText, expectedText, "Text Mismatch!");
  }

  // Verify if an element is visible
  public void assertElementVisible(org.openqa.selenium.By locator) {
    Assert.assertTrue(driver.findElement(locator).isDisplayed(), "Element is not visible!");
  }

  // Verify if an element is enabled
  public void assertElementEnabled(org.openqa.selenium.By locator) {
    Assert.assertTrue(driver.findElement(locator).isEnabled(), "Element is not enabled!");
  }

  // Verify if an element is selected (for checkboxes/radio buttons)
  public void assertElementSelected(org.openqa.selenium.By locator) {
    Assert.assertTrue(driver.findElement(locator).isSelected(), "Element is not selected!");
  }

  // Wait for an element to be visible
  public void waitForElement(org.openqa.selenium.By locator) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public void assertTrue(boolean condition, String onErrorMessage) {
    Assert.assertTrue(condition, onErrorMessage);
  }

  // set implicit wait
  public void setImplicitWait(int seconds) {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
  }

  public void captureAndAssertText(
      List<WebElement> elements, List<String> expectedTexts, String message) {
    List<String> actualTexts =
        elements.stream().map(element -> element.getText().trim()).collect(Collectors.toList());
    AssertionUtils.assertEqualsWithScreenshot(driver, actualTexts, expectedTexts, message);
  }

  public void captureAndAssertContainsText(
      List<WebElement> elements, String expectedText, String message) {
    List<String> actualTexts =
        elements.stream().map(element -> element.getText().trim()).collect(Collectors.toList());
    AssertionUtils.assertTextContainsWithScreenshot(driver, actualTexts, expectedText, message);
  }
}