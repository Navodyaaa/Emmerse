package com.emmerseweb.function.Login;

import com.emmerseweb.model.ForgetPasswordData;
import com.emmerseweb.page.login.ForgetPasswordPage;

public class ForgetPasswordFunction {

    public static void addUsernameWithoutEmail(ForgetPasswordData forgetPasswordData, ForgetPasswordPage forgetPasswordPage){
        forgetPasswordPage.enterUsername(forgetPasswordData.getUsername());
        forgetPasswordPage.clickOnResetPasswordButton();
    }

    public static void validationMessageForUserWithoutEmail(ForgetPasswordData forgetPasswordData, ForgetPasswordPage forgetPasswordPage){
        forgetPasswordPage.enterUsername(forgetPasswordData.getUsername());
        forgetPasswordPage.clickOnResetPasswordButton();
    }

    public static void addUsernameWithEmail(ForgetPasswordData forgetPasswordData, ForgetPasswordPage forgetPasswordPage){
        forgetPasswordPage.enterUsername(forgetPasswordData.getUsername());
        forgetPasswordPage.clickOnResetPasswordButton();
    }

    public static void nousername(ForgetPasswordData forgetPasswordData, ForgetPasswordPage forgetPasswordPage){
        forgetPasswordPage.clickOnResetPasswordButton();
    }

    public static void addwrongusername(ForgetPasswordData forgetPasswordData, ForgetPasswordPage forgetPasswordPage){
        forgetPasswordPage.enterUsername(forgetPasswordData.getUsername());
        forgetPasswordPage.clickOnResetPasswordButton();
    }
}
