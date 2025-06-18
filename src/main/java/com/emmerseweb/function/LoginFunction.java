package com.emmerseweb.function;

import com.emmerseweb.model.LoginData;
import com.emmerseweb.page.Login;
import com.emmerseweb.page.admin.DashboardPage;

/**
 * This class contains the function to perform login operations.
 */

public class LoginFunction {
    public static DashboardPage loging(LoginData loginData, Login loginPage) {
        loginPage.enterUserName(loginData.getUserName());
        loginPage.enterPassword(loginData.getPassword());
        return loginPage.clickOnLoginButton();
    }
    public static boolean isLoginFailErrorMessageDisplayed1(Login loginPage) {
        return loginPage.isLoginFailErrorMessageDisplayed1();
    }
    public static boolean isLoginFailErrorMessageDisplayed2(Login loginPage) {
        return loginPage.isLoginFailErrorMessageDisplayed2();
    }
    public static boolean isLoginFailToastDisplayed(Login loginPage) {
        return loginPage.isLoginFailToastDisplayed();
    }


}
