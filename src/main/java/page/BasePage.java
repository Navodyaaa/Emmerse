package page;

import static util.AppConstant.EXPLICIT_WAIT_DURATION;
import static util.AppConstant.ExceptionMessage.NO_SUCH_ELEMENT;

import exception.NoSuchElementException;

import java.io.File;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.StringUtil;

/**
 * BasePage class provides common web automation methods using Selenium WebDriver. It includes
 * functionalities such as opening URLs, interacting with elements, handling alerts, taking
 * screenshots, and managing frames.
 */
public class BasePage {

  /** WebDriver instance for browser automation */
  protected WebDriver driver;

  /** WebDriverWait instance for explicit waits */
  protected WebDriverWait wait;

  /**
   * Constructor to initialize BasePage with a WebDriver instance.
   *
   * @param driver the WebDriver instance to interact with the browser
   */
  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Opens the specified URL in the browser.
   *
   * @param url the URL to open
   */
  public void openUrl(final String url) {
    driver.get(url);
  }

  /**
   * Retrieves the title of the current page.
   *
   * @return the page title as a String
   */
  public String getTitle() {
    return driver.getTitle();
  }

  /**
   * Clicks on a web element identified by the given locator.
   *
   * @param locator the By locator (e.g., XPath, ID, CSS Selector)
   */
  public void clickOnElement(final By locator) {
    WebElement webElement = getWebElementByLocator(locator);
    explicitWaitOnElementVisibility(webElement);
    moveToElement(locator);
    webElement.click();
  }

  /**
   * Enters text into an input field identified by the given locator.
   *
   * @param locator the By locator (e.g., XPath, ID, CSS Selector)
   * @param inputText the text to enter into the field
   */
  public void enterText(final By locator, final String inputText) {
    WebElement webElement = getWebElementByLocator(locator);
    explicitWaitOnElementVisibility(webElement);
    moveToElement(locator);
    webElement.clear();
    webElement.sendKeys(inputText);
  }

  /**
   * Waits explicitly until the specified element is visible on the page.
   *
   * @param element the WebElement to wait for
   */
  public void explicitWaitOnElementVisibility(final WebElement element) {
    wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_DURATION));
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Finds and returns a web element using the specified locator.
   *
   * @param locator the By locator (e.g., XPath, ID, CSS Selector)
   * @return the WebElement found
   * @throws NoSuchElementException if the element is not found
   */
  public WebElement getWebElementByLocator(final By locator) {
    try {
      return driver.findElement(locator);
    } catch (RuntimeException e) {
      throw new NoSuchElementException(
          StringUtil.formatString(NO_SUCH_ELEMENT, locator.toString()), e);
    }
  }

  /**
   * Checks if a web element is displayed on the page.
   *
   * @param locator the By locator (e.g., XPath, ID, CSS Selector)
   * @return true if the element is displayed, false otherwise
   */
  public boolean isElementDisplay(final By locator) {
    WebElement webElement = getWebElementByLocator(locator);
    explicitWaitOnElementVisibility(webElement);
    return webElement.isDisplayed();
  }

  /**
   * Moves the mouse pointer to the specified element.
   *
   * @param locator the By locator of the element
   */
  public void moveToElement(final By locator) {
    new Actions(driver).moveToElement(getWebElementByLocator(locator)).perform();
  }

  /**
   * Retrieves the text content of an element.
   *
   * @param locator the By locator of the element
   * @return the text of the element
   */
  public String getElementText(final By locator) {
    return getWebElementByLocator(locator).getText();
  }

  /**
   * Retrieves the value of a specific attribute from an element.
   *
   * @param locator the By locator of the element
   * @param attribute the attribute name (e.g., "href", "class", "value")
   * @return the attribute value as a String
   */
  public String getElementAttribute(final By locator, String attribute) {
    return getWebElementByLocator(locator).getAttribute(attribute);
  }

  /**
   * Retrieves a list of web elements matching the given locator.
   *
   * @param locator the By locator of the elements
   * @return a list of WebElement objects
   */
  public List<WebElement> getElements(final By locator) {
    return driver.findElements(locator);
  }

  /**
   * Scrolls to the specified element on the page.
   *
   * @param locator the By locator of the element
   */
  public void scrollToElement(final By locator) {
    new Actions(driver).scrollToElement(getWebElementByLocator(locator)).perform();
  }

  /**
   * Captures a screenshot and saves it to the specified file path.
   *
   * @param filePath the destination file path for the screenshot
   */
  public void takeScreenshot(final String filePath) {
    TakesScreenshot screenshot = (TakesScreenshot) driver;
    File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
    File destFile = new File(filePath);
    srcFile.renameTo(destFile);
  }

  /**
   * Switches the WebDriver context to an iframe identified by the given locator.
   *
   * @param locator the By locator of the iframe
   */
  public void switchToFrame(final By locator) {
    driver.switchTo().frame(getWebElementByLocator(locator));
  }

  /** Switches the WebDriver context back to the default content. */
  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  /** Accepts an alert popup. */
  public void acceptAlert() {
    wait.until(ExpectedConditions.alertIsPresent()).accept();
  }

  /** Dismisses an alert popup. */
  public void dismissAlert() {
    wait.until(ExpectedConditions.alertIsPresent()).dismiss();
  }
}
