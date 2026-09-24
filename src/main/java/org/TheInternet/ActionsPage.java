package org.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionsPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public ActionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }
    public void openPage(){
        driver.get("https://the-internet.herokuapp.com/hovers");
    }
    public void hoverProfile(int index){
        List<WebElement> figures = driver.findElements(By.className("figure"));
        WebElement target = figures.get(index);
        actions.moveToElement(target)
                .perform();
    }
    public boolean isProfileCaptionDisplayed(int index){
        List<WebElement> figures = driver.findElements(By.className("figure")); // get 1 container
        WebElement target = figures.get(index); // get by index
        WebElement caption = target.findElement(By.cssSelector(".figcaption h5")); //scoped search : cari di specific container tadi
        return caption.isDisplayed();
    }
    public String getProfileName(int index){
        List<WebElement> figures = driver.findElements(By.className("figure"));
        WebElement target = figures.get(index);
        WebElement caption = target.findElement(By.cssSelector(".figcaption h5"));
        return caption.getText();
    }
}
