package TheInternet;

import org.TheInternet.ActionsPageHover;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActionsPageHoverTest {
    WebDriver driver;
    ActionsPageHover actionsPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        actionsPage = new ActionsPageHover(driver);
        actionsPage.openPage();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
    @Test
    public void profileCaptionShouldAppearWhenHover() {
        Assert.assertFalse(actionsPage.isProfileCaptionDisplayed(0));
        actionsPage.hoverProfile(0);
        Assert.assertTrue(actionsPage.isProfileCaptionDisplayed(0));
        Assert.assertEquals(actionsPage.getProfileName(0), "name: user1");
    }
    @Test
    public void clickProfileLinkFromHover(){
        actionsPage.hoverProfile(0);
        actionsPage.clickProfileLink(0);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://the-internet.herokuapp.com/users/1");
        Assert.assertEquals(actionsPage.getProfilePageContent(), "Not Found");
    }

}
