package SwagLabs;

import org.SwagLabs.HomePage;
import org.SwagLabs.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Logout {
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
    public void logoutRedirectToLoginPage() {
        homePage.logout();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/");
    }

    @Test
    public void logoutClearSession() {
        homePage.logout();
        homePage.showHomePage();
        String url = driver.getCurrentUrl();
        Assert.assertNotEquals(url, "ttps://www.saucedemo.com/inventory.html");
    }

}
