package org.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckboxPage {
    WebDriver driver;
    WebDriverWait wait;

    private By checkboxLocator = By.xpath("//input[@type='checkbox']");

    public CheckboxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void openPage(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }
    public WebElement getCheckboxLocator(int index) {
        List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(checkboxLocator));
        return checkboxes.get(index);
    }

    public void clickCheckbox(int index){
        getCheckboxLocator(index).click();
    }
    public boolean isCheckboxChecked(int index){
        return getCheckboxLocator(index).isSelected();
    }
}
