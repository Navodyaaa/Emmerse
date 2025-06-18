package com.emmerseweb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private final By todoText = By.xpath("//h3[text()='To do']");

    public boolean clickLoginButton() {
        return isElementDisplay(todoText);
    }
}
