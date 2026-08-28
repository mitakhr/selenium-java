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
        //validate show page after login is list products
        boolean homepageDisplay = loginPage.isHompageDisplayed();
        Assert.assertTrue(homepageDisplay);

    }

    @Test
    public void invalidUsername() {
        loginPage.login("invalid", "secret_sauce");
        //validate error msg
        String actualError = loginPage.getErrrorMessage();
        Assert.assertTrue(actualError.contains("Username and password do not match"));
    }

    @Test
    public void invalidPassword() {
        loginPage.login("standard_user", "wrong");
        String actualError = loginPage.getErrrorMessage();
        Assert.assertTrue(actualError.contains("Username and password do not match")); //expected resul = true if actual error contains the message
    }

    @Test
    public void emptyUsername() {
        loginPage.login("", "secret_sauce");
        String actualError = loginPage.getErrrorMessage();
        Assert.assertEquals(actualError, "Epic sadface: Username is required"); //expected result = actual erro == the string, note: case sensitive
    }
    @Test
    public void emptyPassword() {
        loginPage.login("standard_user", "");
        String actualError = loginPage.getErrrorMessage();
        Assert.assertEquals(actualError, "Epic sadface: Password is required");
    }

    //more simple, choose seperate test or one single test invalidLoginData
    //using data provider to test invalid logi with some conditions
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"invalid", "secret_sauce", "Username and password do not match"},
                {"standard_user", "invalid", "Username and password do not match"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"},
                {"", "", "Username is required"},
        };
    }
    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginData(String username, String password, String error) {
        loginPage.login(username, password);
        String actualError = loginPage.getErrrorMessage();
        Assert.assertTrue(actualError.contains(error));
    }



}
