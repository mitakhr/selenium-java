package TheInternet;

import org.TheInternet.MultipleWIndows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class MultipleWIndowsTest {
    WebDriver driver;
    MultipleWIndows multipleWIndows;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();

        multipleWIndows = new MultipleWIndows(driver);
        multipleWIndows.openPage();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
    @Test
    public void shouldOpenNewWindow() {
        multipleWIndows.getCurrentWindow();
        multipleWIndows.clickNewWindow();
        multipleWIndows.getWindowHandles();
        multipleWIndows.switchToNewWindow();
        Assert.assertEquals(multipleWIndows.getTextNewWindow(), "New Window");


    }
}
