package org.SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void showHomePage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public String  getTitleList(){
        return driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")).getText();
    }

    public boolean getSortButton(){
        return driver.findElement(By.className("product_sort_container")).isDisplayed();
    }

}
