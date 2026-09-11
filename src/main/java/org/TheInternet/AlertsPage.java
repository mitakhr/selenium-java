package org.TheInternet;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage {
    WebDriver driver;
    WebDriverWait wait;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    public void clickAlertButton(String buttonName) {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='"+buttonName+ "']")));
        button.click();
    }
    public String getResultText(){
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        return result.getText();
    }
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    public void cancelAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
    public void inputResponseIntoAlertPopUp(String message) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(message);
    }


}