package SwagLabs;

import org.SwagLabs.HomePage;
import org.SwagLabs.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
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
    }

    @Test
    public void haveCartButton() {
        boolean cartButton = homePage.getCartButton();
        Assert.assertTrue(cartButton);
    }


}
