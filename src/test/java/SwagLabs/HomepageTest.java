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
        homePage.resetAppState();

    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void openHomePage() {
        homePage.navigateToHomePage();
        String currentUrl = driver.getCurrentUrl(); //validate the url
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void shouldHaveHomePageHeader() {
        String titleList = homePage.getHomePageHeaderText();
        Assert.assertEquals(titleList, "Products");
    }

    @Test
    public void sortButtonShouldDisplayed() {
        boolean sortButton = homePage.isSortButtonDisplayed();
        Assert.assertTrue(sortButton);
    }

    @Test
    public void filterListShouldDisplayed() {
        List<String> expectedList = List.of(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");

        List<String> actualValues = homePage.getSortOptionList();
        Assert.assertEquals(actualValues, expectedList);
        Collections.sort(actualValues);
    }

    @Test
    public void cartButtonShouldDisplayed() {
        boolean cartButton = homePage.isCartButtonDisplayed();
        Assert.assertTrue(cartButton);
        //it can  Assert.assertTrue(homePage.getCartButton());
    }

    /// FILTER PRICE & Name
    @Test
    public void shouldSortPriceDescending() {
        homePage.selectSortOption("Price (high to low)");
        List<Double> actualPrice = homePage.getDisplayedPrices();

        List<Double> expected = homePage.getDisplayedPrices();
        Collections.sort(actualPrice, Collections.reverseOrder());

        Assert.assertEquals(actualPrice, expected);
    }
    @Test
    public void shouldSortPriceAscending() {
        homePage.selectSortOption("Price (low to high)");
        List<Double> actualPrice = homePage.getDisplayedPrices();

        List<Double> expected = homePage.getDisplayedPrices();
        Collections.sort(actualPrice);

        Assert.assertEquals(actualPrice, expected);
    }

    @Test
    public void shouldSortNameAscending() {
        homePage.selectSortOption("Name (A to Z)");
        List<String> actualName = homePage.getDisplayedNames();
        List<String> expected = homePage.getDisplayedNames();
        Collections.sort(actualName);
        Assert.assertEquals(actualName, expected);
    }

    @Test
    public void shouldSortNameDescending() {
        homePage.selectSortOption("Name (Z to A)");
        List<String> actualName = homePage.getDisplayedNames();
        List<String> expected = homePage.getDisplayedNames();
        Collections.sort(actualName, Collections.reverseOrder());
        Assert.assertEquals(actualName, expected);
    }


    /// BURGER AND SIDEBAR
    @Test
    public void burgerMenuShouldDisplayed() {
        homePage.openBurgerMenu();
        boolean expected = homePage.isBurgerMenuDisplayed();
        Assert.assertTrue(expected);
    }

    @Test
    public void sideBarMenuShouldDisplayed() {
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
    public void productCardShouldHasComponents() { //to validate all product has all card component
        Assert.assertTrue(homePage.isProductCardDisplayedRequiredInfo());
    }

    @Test
    public void productCardHasMissingInfo() { // same with di atas, but + info where's the missing info in product
        //Thread.sleep(100000); and put throws InterruptedException, only for testing missing product info, put
        List<String> missing = homePage.getMissingProductInfo();
        Assert.assertTrue(missing.isEmpty(), "found issue in product : "+ missing);
    }

    /// product detail

    @Test
    public void productDetailPageShouldExists()   {
        homePage.openProductDetailPage();
        //Thread.sleep(50000); only for testing delete element + throws InterruptedException
        Assert.assertTrue(homePage.isProductDetailPageDisplayed());
    }

    @Test
    public void productDetailPageHasMissingInfo()  {
        homePage.openProductDetailPage();
        List<String> missing = homePage.getMissingProductDetailInfo();
        Assert.assertTrue(missing.isEmpty(), "found issue in product : "+ missing);
    }

    @Test
    public void addToCartShouldChangesButtonToRemove()  {
        //find product name then click add to cart
        homePage.addProductToCart("Sauce Labs Bike Light");
        //get 'remove' after ind product name then click add to cart
        String buttontext = homePage.getTextButtonFromProductCard("Sauce Labs Bike Light");
        Assert.assertEquals(buttontext, "Remove");
    }

    @Test
    public void addToCartShouldUpdateCartBadge()  {
        //find product name then click add to cart
        homePage.addProductToCart("Sauce Labs Bike Light");
        //Assert.assertEquals(homePage.getTextButtonFromProductCard("Sauce Labs Bike Light"), "Remove");
        Assert.assertEquals(homePage.getBadgeCartCount(), 1);
    }

    @Test
    public void addToCartShouldHaveIncrementCartBadge()  {
        int before = homePage.getBadgeCartCount();
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Onesie");
//        homePage.addProductToCart("Sauce Labs Backpack");
        int finalAdd = homePage.getBadgeCartCount();
        Assert.assertEquals(finalAdd - before, 2);
    }


}
