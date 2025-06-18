package com.emmerseweb.page.login;

import com.emmerseweb.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ForgetPasswordPage extends BasePage {
    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
    }

    private final By signInTab = By.xpath("//h1[text()='Sign In' and contains(@class, 'sign-in-title')]");
    private final By usernameTextBox = By.xpath("//input[@name='UserName']");
    private final By resetpasswordButton = By.xpath("//button[@type='submit' and contains(@class, 'loginbtn')]");
    private final By validationLabel = By.xpath("//span[@id='UserName-error']");
    private final By validationforDefault = By.xpath("//div[@class='toast-message' and text()='Your password is reset successefully']");
    private final By validationforRandom = By.xpath("//div[@class='toast-message' and text()='Relevant new password has sent to your email. Check your email for password to log to the system.']");
    private final By validationforInvalidCredential = By.xpath("//div[@class='toast-message' and text()='Username invalid']");
    private final By languageoption1Dropdown = By.xpath("//li[@class='selected']//span[@class='text' and text()='English']");
    private final By languageoption2Dropdown = By.xpath("//li[@data-original-index='1']//span[@class='text' and text()='සිංහල']");
    private final By languageoption3Dropdown = By.xpath("//li[@data-original-index='2']//span[@class='text' and text()='தமிழ்']");

    public void clickOnSignInTab() {
        clickOnElement(signInTab);
    }

    public void enterUsername(String username) {
        enterText(usernameTextBox, username);
    }

    public void clickOnResetPasswordButton() {
        clickOnElement(resetpasswordButton);
    }

    public String getValidationLabelText() {
        return getElementText(validationLabel);
    }

    public String getValidationforDefaultText() {
        return getElementText(validationforDefault);
    }

    public String getValidationforRandomText() {
        return getElementText(validationforRandom);
    }

    public String getValidationforInvalidCredentialText() {
        return getElementText(validationforInvalidCredential);
    }

    public void selectEnglishOptionByValue(String selectEnglishOption) {
        WebElement dropdown = getWebElementByLocator(languageoption1Dropdown);

        try {
            if (dropdown.getTagName().equalsIgnoreCase("select")) {
                new Select(dropdown).selectByVisibleText(selectEnglishOption);
            } else {
                dropdown.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//li[normalize-space()='" + selectEnglishOption + "']"))
                );

                if (!options.isEmpty()) {
                    options.get(0).click();
                } else {
                    throw new NoSuchElementException("Option '" + selectEnglishOption + "' not found in the dropdown.");
                }
            }
        } catch (Exception e) {
            //throw new RuntimeException("Error selecting option: " + selectActiveOption, e);
        }
    }

    public void selectSinhalaOptionByValue(String selectSinhalaOption) {
        WebElement dropdown = getWebElementByLocator(languageoption2Dropdown);

        try {
            if (dropdown.getTagName().equalsIgnoreCase("select")) {
                new Select(dropdown).selectByVisibleText(selectSinhalaOption);
            } else {
                dropdown.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//li[normalize-space()='" + selectSinhalaOption + "']"))
                );

                if (!options.isEmpty()) {
                    options.get(0).click();
                } else {
                    throw new NoSuchElementException("Option '" + selectSinhalaOption + "' not found in the dropdown.");
                }
            }
        } catch (Exception e) {
            //throw new RuntimeException("Error selecting option: " + selectActiveOption, e);
        }
    }

    public void selectTamilOptionByValue(String selectTamilOption) {
        WebElement dropdown = getWebElementByLocator(languageoption3Dropdown);

        try {
            if (dropdown.getTagName().equalsIgnoreCase("select")) {
                new Select(dropdown).selectByVisibleText(selectTamilOption);
            } else {
                dropdown.click();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//li[normalize-space()='" + selectTamilOption + "']"))
                );

                if (!options.isEmpty()) {
                    options.get(0).click();
                } else {
                    throw new NoSuchElementException("Option '" + selectTamilOption + "' not found in the dropdown.");
                }
            }
        } catch (Exception e) {
            //throw new RuntimeException("Error selecting option: " + selectActiveOption, e);
        }
    }

}
