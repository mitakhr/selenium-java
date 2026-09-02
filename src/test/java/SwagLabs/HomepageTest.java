package SwagLabs;

import org.SwagLabs.HomePage;
import org.SwagLabs.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomepageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void openHomePage() {
        homePage.showHomePage();
        String currentUrl = driver.getCurrentUrl(); //validate the url
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void validateHomePage() {
        String titleList = homePage.getHeaderPage();
        Assert.assertEquals(titleList, "Products");
    }

    @Test
    public void haveSortButton() {
        boolean sortButton = homePage.getSortButton();
        Assert.assertTrue(sortButton);
    }

    @Test
    public void validateFilterList() {
        List<String> expectedList = List.of(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");

        List<String> actualValues = homePage.getFilterList();
        Assert.assertEquals(actualValues, expectedList);
        Collections.sort(actualValues);
    }

    @Test
    public void haveCartButton() {
        boolean cartButton = homePage.getCartButton();
        Assert.assertTrue(cartButton);
    }

    @Test
    public void sortPriceWorks() {
        homePage.selectSortOption("Price (high to low)");
        List<Double> actualPrice = homePage.getDisplayedPrice();

        List<Double> expected = homePage.getDisplayedPrice();
        Collections.sort(actualPrice, Collections.reverseOrder());

        Assert.assertEquals(actualPrice, expected);
    }

    @Test
    public void showBurgerMenus() {
        homePage.openBurgerMenu();
       boolean expected = homePage.isDisplayedBurgerMenu();
       Assert.assertTrue(expected);
    }

    @Test
    public void getSideBarMenu() {
        homePage.openBurgerMenu();
        List<String> expected = List.of(
                "All Items",
                "About",
                "Logout",
                "Reset App State");
        List<String> actualValues = homePage.getSideBarMenu();
        Assert.assertEquals(actualValues, expected);
    }


;}
