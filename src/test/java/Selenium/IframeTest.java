package Selenium;

import org.Selenium.IframePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IframeTest {
    WebDriver driver;
    IframePage iframePage;
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        iframePage = new IframePage(driver);
        iframePage.openPage();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void iframeShouldBeDisplayed() {
        iframePage.locateToIframe();
        Assert.assertTrue(iframePage.iframeHasContent());
    }

    @Test
    public void inputValidContactFormInsideIframe() {
        iframePage.locateToIframe();
        iframePage.locateToContactForm();
        iframePage.enterFieldValue("first_name", "John");
        iframePage.enterFieldValue("last_name", "Doe");
        iframePage.enterFieldValue("email", "johndoe@mail.com");
        iframePage.enterFieldValue("message", "This is message from John Doe");
        iframePage.submitForm();
        Assert.assertEquals(iframePage.getSuccessMessage(), "Thank You for your Message!");
        iframePage.exitIframe();
        Assert.assertFalse(iframePage.isH1MessageAccessibleOutsideIframe());
    }
}
