package TheInternet;

import org.TheInternet.AlertsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTest {
    WebDriver driver;
    AlertsPage alertsPage;

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();

        alertsPage = new AlertsPage(driver);
        alertsPage.openPage();
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void jsAlertShouldHaveOkButton() {
        alertsPage.clickAlertButton("Click for JS Alert");
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void alertButtonConfirmDismiss() {
        alertsPage.clickAlertButton("Click for JS Confirm");
        alertsPage.cancelAlert();
        Assert.assertEquals(alertsPage.getResultText(), "You clicked: Cancel");
    }
    @Test
    public void alertButtonConfirmAccept() {
        alertsPage.clickAlertButton("Click for JS Confirm");
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getResultText(), "You clicked: Ok");

    }

    @Test
    public void jsAlertWithPromptShouldAcceptEnteredText() {
        alertsPage.clickAlertButton("Click for JS Prompt");
        alertsPage.inputResponseIntoAlertPopUp("Hello World");
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getResultText(), "You entered: Hello World");
    }
}
