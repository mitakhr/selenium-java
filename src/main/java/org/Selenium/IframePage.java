package org.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IframePage {
    WebDriver driver;
    WebDriverWait wait;

    public IframePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void openPage() {
        driver.get("https://webdriveruniversity.com/IFrame/index.html");
    }
    public void locateToIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("frame")));
    }
    public boolean iframeHasContent() {
        WebElement thumbnail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("thumbnail")));
        return thumbnail.isDisplayed();
    }
    public void locateToContactForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Contact Us"))).click();
    }

    public void enterFieldValue(String fieldName, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(fieldName)));
        field.clear();
        field.sendKeys(value);
    }

    public void submitForm(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='SUBMIT']"))).click();
    }
    public void resetForm(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='reset'][value='RESET']"))).click();
    }
    public String getSuccessMessage(){
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText();
        return text;
    }
    public void exitIframe(){
        driver.switchTo().defaultContent();
    }
    public boolean isH1MessageAccessibleOutsideIframe(){
        try {
            driver.findElement(By.tagName("h1"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
