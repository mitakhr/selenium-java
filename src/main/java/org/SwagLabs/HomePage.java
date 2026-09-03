package org.SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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
        //loop to get all the list
        for (WebElement option : dropdownFilter.getOptions()) {
            optionValues.add(option.getText());
        }
        return optionValues;
    }

    public boolean getCartButton(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container"))).isDisplayed();
    }

    public void selectSortOption(String option){
        //choose 1 filter
        WebElement filterList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container")));
        new Select(filterList).selectByVisibleText(option);
    }

    public List<Double> getDisplayedPrice(){
        //get all the value after being selected
        List<WebElement> priceList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price")));
        List<Double> prices = new ArrayList<>();
        for (WebElement prc : priceList){
            String cleaned = prc.getText().replace("$", "").trim(); //to seperate currency and price
            prices.add(Double.parseDouble(cleaned));
        }
        return prices;
    }

    public void openBurgerMenu(){
        //click the burger menu
        WebElement bgrMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        bgrMenu.click();
    }
    public boolean isDisplayedBurgerMenu(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap"))).isDisplayed();
    }

    public List<String> getSideBarMenu(){
        //get value list menu sidebar
        List<WebElement> sideBarMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("bm-item")));
        List<String> sides = new ArrayList<>();
        for (WebElement side : sideBarMenu )  {
            sides.add(side.getText());
        }
        return sides;
    }

    public boolean showEachProudctCard(){
        //get each product card
        List<WebElement> product = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));

        for (WebElement p : product){
            //read : hasImage jadi true = !product.isEmpty / tidak ada product.yg empty
            boolean hasImage = !p.findElements(By.className("inventory_item_img")).isEmpty();
            boolean hasName = !p.findElements(By.className("inventory_item_name")).isEmpty();
            boolean hasDesc = !p.findElements(By.className("inventory_item_desc")).isEmpty();
            boolean hasPrice = !p.findElements(By.className("inventory_item_price")).isEmpty();
            boolean hasCartButton = !p.findElements(By.cssSelector(".btn_inventory")).isEmpty(); //Reads as: "a <button> element that ALSO has the class btn_inventory."

            if (!hasImage || !hasName || !hasDesc || !hasPrice || !hasCartButton){
                return false;
            }
        }
        return true;
    }

    public List<String> getProductWithRequiredInfo(){
        //get each product card
        List<WebElement> product = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item")));
        //array to listing missing info
        List<String> missingInfo = new ArrayList<>();

        for (int i = 0; i < product.size(); i++){
            WebElement p = product.get(i);
            if (p.findElements(By.className("inventory_item_img")).isEmpty()){
                missingInfo.add("Product " + i + " is missing");
            }
            if (p.findElements(By.className("inventory_item_name")).isEmpty()){
                missingInfo.add("Product " + i + " is missing");
            }
            if (p.findElements(By.className("inventory_item_desc")).isEmpty()){
                missingInfo.add("Product " + i+ " is missing");
            }
            if (p.findElements(By.className("inventory_item_price")).isEmpty()){
                missingInfo.add("Product " + i + " is missing");
            }
            if (p.findElements(By.className("inventory_item_price")).isEmpty()){
                missingInfo.add("Product " + i + " is missing");
            }
        }
        return missingInfo;
    }

    public void logout(){
        openBurgerMenu();
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))); //use clickable bcs it's wait and click
        el.click();
    }


}
