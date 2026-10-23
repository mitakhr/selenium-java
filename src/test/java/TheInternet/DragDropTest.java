package TheInternet;

import org.TheInternet.DragDropPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragDropTest {
    WebDriver driver;
    DragDropPage dragDropPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        dragDropPage = new DragDropPage(driver);
        dragDropPage.openPage();
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
    @Test
    public void dragAToBShouldWorkAndSwap() {
        dragDropPage.dragAndDrop();
        Assert.assertEquals(dragDropPage.getTextColumnA(), "B");
        Assert.assertEquals(dragDropPage.getTextColumnB(), "A");
    }
}
