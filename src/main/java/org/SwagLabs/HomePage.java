package org.SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void showHomePage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public String  getHeaderPage(){
        return driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")).getText();
    }

    public boolean getSortButton(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container"))).isDisplayed();
    }

    public List<String> getFilterList(){
        //Locate the dropdown and initialize the Select class
        WebElement filterList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
        Select dropdownFilter = new Select(filterList);

        //put the value in a list
        List<String> optionValues = new ArrayList<>();

        for (WebElement option : dropdownFilter.getOptions()) {
            optionValues.add(option.getText());
        }
        return optionValues;
    }


    public boolean getCartButton(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container"))).isDisplayed();
    }



}
