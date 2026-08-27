package SwagLabs;

import org.SwagLabs.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    //initiate
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeTest() {
        //add value
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);

        loginPage.openLoginPage();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void validLogin() {
        loginPage.login("standard_user", "secret_sauce");
        //validate if success login direct to inventory.html
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void invalidUsername() {
        loginPage.login("invalid", "secret_sauce");
        //validate error msg
        String actualError = loginPage.getErrrorMessage();
        Assert.assertTrue(actualError.contains("Username and password do not match"));
    }



}
