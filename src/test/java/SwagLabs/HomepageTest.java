package SwagLabs;

import org.SwagLabs.HomePage;
import org.SwagLabs.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class HomepageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login("standard_user", "secret_sauce");

        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void openHomePage() {
        homePage.showHomePage();
        String currentUrl = driver.getCurrentUrl(); //validate the url
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void validateHomePage() {
        String titleList = homePage.getHeaderPage();
        Assert.assertEquals(titleList, "Products");
    }

    @Test
    public void haveSortButton() {
        boolean sortButton = homePage.getSortButton();
        Assert.assertTrue(sortButton);
    }

    @Test
    public void validateFilterList() {
        List<String> expectedList = List.of(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");

        List<String> actualValues = homePage.getFilterList();
        Assert.assertEquals(actualValues, expectedList);
        Collections.sort(actualValues);
    }

    @Test
    public void haveCartButton() {
        boolean cartButton = homePage.getCartButton();
        Assert.assertTrue(cartButton);
        //it can be Assert.assertTrue(homePage.getCartButton());
    }

    /// FILTER PRICE & Name
    @Test
    public void sortPriceWorksDesc() {
        homePage.selectSortOption("Price (high to low)");
        List<Double> actualPrice = homePage.getDisplayedPrice();

        List<Double> expected = homePage.getDisplayedPrice();
        Collections.sort(actualPrice, Collections.reverseOrder());

        Assert.assertEquals(actualPrice, expected);
    }
    @Test
    public void sortPriceWorksAsc() {
        homePage.selectSortOption("Price (low to high)");
        List<Double> actualPrice = homePage.getDisplayedPrice();

        List<Double> expected = homePage.getDisplayedPrice();
        Collections.sort(actualPrice);

        Assert.assertEquals(actualPrice, expected);
    }

    @Test
    public void sortNameWorksAsc() {
        homePage.selectSortOption("Name (A to Z)");
        List<String> actualName = homePage.getDisplayedName();
        List<String> expected = homePage.getDisplayedName();
        Collections.sort(actualName);
        Assert.assertEquals(actualName, expected);
    }

    @Test
    public void sortNameWorksDesc() {
        homePage.selectSortOption("Name (Z to A)");
        List<String> actualName = homePage.getDisplayedName();
        List<String> expected = homePage.getDisplayedName();
        Collections.sort(actualName, Collections.reverseOrder());
        Assert.assertEquals(actualName, expected);
    }


    /// BURGER AND SIDEBAR
    @Test
    public void showBurgerMenus() {
        homePage.openBurgerMenu();
        boolean expected = homePage.isDisplayedBurgerMenu();
        Assert.assertTrue(expected);
    }

    @Test
    public void getSideBarMenu() {
        homePage.openBurgerMenu();
        List<String> expected = List.of(
                "All Items",
                "About",
                "Logout",
                "Reset App State");
        List<String> actualValues = homePage.getSideBarMenu();
        Assert.assertEquals(actualValues, expected);
    }

    /// PRODUCT CARD VALIDATION
    @Test
    public void productCardHasComponents() { //to validate all product has all card component
        Assert.assertTrue(homePage.showEachProudctCard());
    }

    @Test
    public void productCardHasMissingInfo() { // sama kaya di atas, but + info where's the missing info in product
        //Thread.sleep(100000); and put throws InterruptedException, only for testing missing product info, put
        List<String> missing = homePage.getProductWithRequiredInfo();
        Assert.assertTrue(missing.isEmpty(), "found issue in product : "+ missing);
    }

    /// product detail

    @Test
    public void productDetailPageExists() throws InterruptedException {
        homePage.showProductDetailPage();
        //Thread.sleep(50000); only for testing delete element
        Assert.assertTrue(homePage.detailPageHasDetailProduct());
    }

    @Test
    public void productDetailPageMissingInfo()  {
        homePage.showProductDetailPage();
        List<String> missing = homePage.getProductDetailPage();
        Assert.assertTrue(missing.isEmpty(), "found issue in product : "+ missing);
    }


}
