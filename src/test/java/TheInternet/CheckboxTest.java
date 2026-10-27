package TheInternet;

import org.TheInternet.CheckboxPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxTest {
    WebDriver driver;
    CheckboxPage checkboxPage;

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        checkboxPage = new CheckboxPage(driver);
        checkboxPage.openPage();
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
    @Test
    public void checkedCheckboxShouldBeFalseAfterClicked(){
        Assert.assertTrue(checkboxPage.isCheckboxChecked(1));
        checkboxPage.clickCheckbox(1);
        Assert.assertFalse(checkboxPage.isCheckboxChecked(1));
    }
    @Test
    public void uncheckedCheckboxShouldBeClicked(){
        Assert.assertFalse(checkboxPage.isCheckboxChecked(0));
        checkboxPage.clickCheckbox(0);
        Assert.assertTrue(checkboxPage.isCheckboxChecked(0));
    }
}
