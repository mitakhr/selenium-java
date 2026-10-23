package org.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DragDropPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    private By columnA = By.id("column-a");
    private By columnB = By.id("column-b");

    public DragDropPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
    }
    public void dragAndDrop() {
        WebElement source = wait.until(ExpectedConditions.visibilityOfElementLocated(columnA));
        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(columnB));
        actions.dragAndDrop(source, target).perform(); //action drag source to target and perform(do) it
    }
    public String getTextColumnA() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(columnA)).getText();
    }
    public String getTextColumnB() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(columnB)).getText();
    }
}
