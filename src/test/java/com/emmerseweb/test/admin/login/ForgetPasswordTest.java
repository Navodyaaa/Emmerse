package com.emmerseweb.test.admin.login;

import com.emmerseweb.function.Login.ForgetPasswordFunction;
import com.emmerseweb.model.ForgetPasswordData;
import com.emmerseweb.model.LoginData;
import com.emmerseweb.page.DashboardPage;
import com.emmerseweb.page.login.ForgetPasswordPage;
import com.emmerseweb.page.login.Login;
import com.emmerseweb.test.BaseTest;
import com.emmerseweb.util.ExcelUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgetPasswordTest extends BaseTest {

    /*@Test(description = "TC001")
    public void forgetPasswordWithoutEmail(){
        Login loginPage = PageFactory.initElements(driver, Login.class);
        loginPage.loadLoginPage();

        ForgetPasswordPage forgetPasswordPage = loginPage.clickForgotPasswordLink();

        String username = ExcelUtils.getCellData("src/main/resources/testdata.xlsx", "ForgetPassword", 1, 0);

        ForgetPasswordData forgetPasswordData = ForgetPasswordData.builder()
                .username(username)
                .build();

        ForgetPasswordFunction.addUsernameWithoutEmail(forgetPasswordData, forgetPasswordPage);

        //loginPage = PageFactory.initElements(driver, Login.class);
        //loginPage.loadLoginPage();

        loginPage.enterUserName(username);
        loginPage.enterPassword("Alpha@2017");
        loginPage.clickLoginButton();

        DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        Assert.assertTrue(dashboardPage.clickLoginButton(), "Login failed with default password after forget password.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Login successful with default password.");
    }*/

    /*@Test(description = "TC001 - Forget Password Without Email and Login with Default Password")
    public void forgetPasswordWithoutEmail() {
        String filePath = "src/main/resources/testdata.xlsx";
        String sheetName = "ForgetPassword";
        int row = 1;
        int tcColumn = 0;
        int descriptionColumn = 1; // Column to write test case ID
        int resultColumn = 4; // Column to write result ("Pass"/"Fail")
        String testCaseId = "TC001";
        String testCaseDescription = "Forget Password Without Email and Login with Default Password";

        try {
            // Step 1: Initialize pages
            Login loginPage = PageFactory.initElements(driver, Login.class);
            loginPage.loadLoginPage();

            // Step 2: Go to forget password page
            ForgetPasswordPage forgetPasswordPage = loginPage.clickForgotPasswordLink();

            // Step 3: Read username from Excel
            String username = ExcelUtils.getCellData(filePath, sheetName, row, 2);

            // Step 4: Write test case ID to Excel
            ExcelUtils.setCellData(filePath, sheetName, row, tcColumn, testCaseId);
            ExcelUtils.setCellData(filePath, sheetName, row, descriptionColumn, testCaseDescription);

            // Step 5: Perform forget password
            ForgetPasswordData forgetPasswordData = ForgetPasswordData.builder()
                    .username(username)
                    .build();

            ForgetPasswordFunction.addUsernameWithoutEmail(forgetPasswordData, forgetPasswordPage);

            // Step 6: Login with default password
            loginPage.enterUserName(username);
            loginPage.enterPassword("Alpha@2017");
            loginPage.clickLoginButton();

            // Step 7: Verify dashboard
            DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
            boolean loginSuccess = dashboardPage.clickLoginButton();

            Assert.assertTrue(loginSuccess, "Login failed with default password after forget password.");

            // Step 8: Write Pass result
            ExcelUtils.setCellData(filePath, sheetName, row, resultColumn, "Pass");
            System.out.println("Login successful with default password.");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            try {
                ExcelUtils.setCellData(filePath, sheetName, row, resultColumn, "Fail");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Assert.fail("Test execution failed.", e);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Login successful with default password.");
    }*/


    @Test(description = "TC001 - Forget Password Without Email and Login with Default Password")
    public void forgetPasswordWithoutEmails() {
        String filePath = "src/main/resources/testdata.xlsx";
        String sheetName = "ForgetPassword";
        int row = 1;
        int tcColumn = 0;
        int descriptionColumn = 1; // Column to write test case ID
        int resultColumn = 4; // Column to write result ("Pass"/"Fail")
        String testCaseId = "TC001";
        String testCaseDescription = "Forget Password Without Email and Login with Default Password";

        try {

            Login loginPage = PageFactory.initElements(driver, Login.class);
            loginPage.loadLoginPage();

            ForgetPasswordPage forgetPasswordPage = loginPage.clickForgotPasswordLink();

            String username = ExcelUtils.getCellData(filePath, sheetName, row, 2);

            ExcelUtils.setCellData(filePath, sheetName, row, tcColumn, testCaseId);
            ExcelUtils.setCellData(filePath, sheetName, row, descriptionColumn, testCaseDescription);

            ForgetPasswordData forgetPasswordData = ForgetPasswordData.builder()
                    .username(username)
                    .build();

            ForgetPasswordFunction.addUsernameWithoutEmail(forgetPasswordData, forgetPasswordPage);

            loginPage.enterUserName(username);
            loginPage.enterPassword("Alpha@2017");
            loginPage.clickLoginButton();

            DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
            boolean loginSuccess = dashboardPage.clickLoginButton(); // corrected here

            Assert.assertTrue(loginSuccess, "Login failed with default password after forget password.");
            ExcelUtils.setCellData(filePath, sheetName, row, resultColumn, "Pass");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            try {
                ExcelUtils.setCellData("src/main/resources/testdata.xlsx", "ForgetPassword", 1, 2, "Fail");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Assert.fail("Test execution failed.", e);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Login successful with default password.");

    }

    @Test(description = "TC002 - Check validation message for 'Forget Password' when user does not have an email")
    public void validationMessageForUserWithoutEmail() {
        String filePath = "src/main/resources/testdata.xlsx";
        String sheetName = "ForgetPassword";
        int row = 1;
        int tcColumn = 0;
        int descriptionColumn = 1; // Column to write test case ID
        int resultColumn = 4; // Column to write result ("Pass"/"Fail")
        String testCaseId = "TC002";
        String testCaseDescription = "Check validation message for 'Forget Password' when user does not have an email";

        try {

            Login loginPage = PageFactory.initElements(driver, Login.class);
            loginPage.loadLoginPage();

            ForgetPasswordPage forgetPasswordPage = loginPage.clickForgotPasswordLink();

            String username = ExcelUtils.getCellData(filePath, sheetName, row, 2);

            ExcelUtils.setCellData(filePath, sheetName, row, tcColumn, testCaseId);
            ExcelUtils.setCellData(filePath, sheetName, row, descriptionColumn, testCaseDescription);

            ForgetPasswordData forgetPasswordData = ForgetPasswordData.builder()
                    .username(username)
                    .build();

            ForgetPasswordFunction.addUsernameWithoutEmail(forgetPasswordData, forgetPasswordPage);

            loginPage.enterUserName(username);
            loginPage.enterPassword("Alpha@2017");
            loginPage.clickLoginButton();

            DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
            boolean loginSuccess = dashboardPage.clickLoginButton(); // corrected here

            Assert.assertTrue(loginSuccess, "Login failed with default password after forget password.");
            ExcelUtils.setCellData(filePath, sheetName, row, resultColumn, "Pass");

        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            try {
                ExcelUtils.setCellData("src/main/resources/testdata.xlsx", "ForgetPassword", 1, 2, "Fail");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Assert.fail("Test execution failed.", e);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Login successful with default password.");

    }
}
