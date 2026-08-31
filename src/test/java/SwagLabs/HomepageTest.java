package SwagLabs;

import org.SwagLabs.HomePage;
import org.SwagLabs.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomepageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        driver.manage().window().maximize();
        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void openHomePage() {
        homePage = new HomePage(driver);
        homePage.showHomePage();
        String currentUrl = driver.getCurrentUrl(); //validate the url
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void validateHomePage() {
        homePage = new HomePage(driver);
        String titleList = homePage.getTitleList();
        Assert.assertEquals(titleList, "Products");
    }

    @Test
    public void haveSortButton() {
        homePage = new HomePage(driver);
        boolean sortButton = homePage.getSortButton();
        Assert.assertTrue(sortButton);
    }


}
