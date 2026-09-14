package org.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IframePage {
    WebDriver driver;
    WebDriverWait wait;

    public IframePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(){
        driver.get("https://the-internet.herokuapp.com/frames");
    }
    public void clickIframeLink(){
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/iframe']")));
        link.click();
    }
    public void locateToIframe(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("mce_0_ifr")));
    }
    public String getIframeText(){
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce"))).getText();
        return text;
    }
    public void exitIframe(){
        driver.switchTo().defaultContent();
    }
    public String getPageHeaderText(){
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3"))).getText();
        return text;
    }
}
