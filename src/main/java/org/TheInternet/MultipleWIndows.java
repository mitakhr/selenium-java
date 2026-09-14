package org.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class MultipleWIndows {
    WebDriver driver;
    WebDriverWait wait;

    public MultipleWIndows(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(){
        driver.navigate().to("https://the-internet.herokuapp.com/windows");
    }
    public String getCurrentWindow(){
        return driver.getWindowHandle();
    }

    public void clickNewWindow(){
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/windows/new']")));
        link.click();
    }
    public Set<String> getWindowHandles(){
        Set<String> handles = driver.getWindowHandles();
        return handles;
    }
    public  void switchToNewWindow(){
        String parentHandle = getCurrentWindow();

        Set<String> handles = getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                return;
            }
        }

    }
    public String getTextNewWindow(){
        return  driver.getTitle();
    }
    public int numberOfWindows(int expectedCount){
        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedCount));
        return driver.getWindowHandles().size();
    }

}
