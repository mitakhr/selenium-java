package TheInternet;

import org.TheInternet.IframePage;
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
    public void BeforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        iframePage = new IframePage(driver);
        iframePage.openPage();
    }
    @AfterMethod
    public void AfterMethod(){
        driver.quit();
    }
    @Test
    public void iframeShouldAppeared() {
        iframePage.clickIframeLink();
        iframePage.locateToIframe();
        Assert.assertEquals(iframePage.getIframeText(), "Your content goes here.");
        iframePage.exitIframe();
        Assert.assertEquals(iframePage.getPageHeaderText(), "An iFrame containing the TinyMCE WYSIWYG Editor");
    }
}
