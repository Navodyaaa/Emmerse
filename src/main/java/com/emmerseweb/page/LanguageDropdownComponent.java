package com.emmerseweb.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanguageDropdownComponent {


        private WebDriver driver;

        public LanguageDropdownComponent(WebDriver driver) {
            this.driver = driver;
        }

        // XPaths for dropdown interaction
        private final By languageDropdownToggle = By.xpath("//button[@data-toggle='dropdown']"); // adjust if needed
        private final By englishOption = By.xpath("//span[text()='English']");
        private final By sinhalaOption = By.xpath("//span[text()='Sinhala']");
        private final By tamilOption = By.xpath("//span[text()='Tamil']");

        // Actions
        public void openLanguageDropdown() {
            driver.findElement(languageDropdownToggle).click();
        }

        public void selectEnglish() {
            openLanguageDropdown();
            driver.findElement(englishOption).click();
        }

        public void selectSinhala() {
            openLanguageDropdown();
            driver.findElement(sinhalaOption).click();
        }

        public void selectTamil() {
            openLanguageDropdown();
            driver.findElement(tamilOption).click();
        }

        // Optional: Get current language (if highlighted or visible elsewhere)
//        public String getSelectedLanguage() {
//            return driver.findElement(By.cssSelector("span.filter-option.pull-left")).getText();
//        }
    }

