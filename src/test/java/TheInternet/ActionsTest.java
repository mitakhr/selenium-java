package TheInternet;

import org.TheInternet.ActionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsTest {
    WebDriver driver;
    ActionsPage actionsPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        actionsPage = new ActionsPage(driver);
        actionsPage.openPage();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
    @Test
    public void profileCaptionShouldAppearWhenHover() {
        Assert.assertFalse(actionsPage.isProfileCaptionDisplayed(1));
        actionsPage.hoverProfile(1);
        Assert.assertTrue(actionsPage.isProfileCaptionDisplayed(1));
        Assert.assertEquals(actionsPage.getProfileName(1), "name: user2");
    }
    @Test
    public void clickProfileLinkFromHover(){
        actionsPage.hoverProfile(1);
        actionsPage.clickProfileLink(1);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://the-internet.herokuapp.com/users/2");
        Assert.assertEquals(actionsPage.getProfilePageContent(), "Not Found");
    }
}
