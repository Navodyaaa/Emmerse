package com.emmerseweb.page.login;

import com.emmerseweb.page.BasePage;
import com.emmerseweb.page.DashboardPage;
import com.emmerseweb.util.PropertyResolverUtil;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import static com.emmerseweb.util.AppConstant.PropertyName.LOGIN_URL;

@Getter
public class Login extends BasePage {
    public Login(WebDriver driver) {
        super(driver);
    }

    private final By userNameInput = By.xpath("//input[@id='form-control1']\n") ;
    private final By passwordInput = By.xpath("//input[@id='form-control2']\n") ;
    private final By forgotPasswordLink = By.xpath("//a[@class='text text-links forgot' ]") ;
    private final By loginButton = By.xpath("//button[@name='submit' ]");
    private final By continueWithMicrosoftButton = By.xpath("//a[@class='input-submit1 input-submit-btn-text row pa-0' ]");
    private final By errorLabel1= By.xpath("//span[@id='form-control1-error'] ");
    private final By errorLabel2= By.xpath("//span[@id='form-control2-error'] ");
    private final By toastMessage = By.xpath("//div[contains(@class, 'toast-message')]");


    public void enterUserName(String userName) {
        enterText(userNameInput, userName);
    }

    public void enterPassword(String password) {
        enterText(passwordInput, password);
    }

    public void loadLoginPage(){
        openUrl(PropertyResolverUtil.getApplicationProperties().getProperty(LOGIN_URL));
    }

    public ForgetPasswordPage clickForgotPasswordLink() {
        clickOnElement(forgotPasswordLink);
        return PageFactory.initElements(driver, ForgetPasswordPage.class);
    }

    public DashboardPage clickLoginButton() {
        clickOnElement(loginButton);
        return PageFactory.initElements(driver, DashboardPage.class);
    }

}
