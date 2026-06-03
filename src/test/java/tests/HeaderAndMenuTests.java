package tests;

import application.BuiltInUsers;
import application.URLs;
import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeaderAndMenuTests extends BaseTest {

    //-------------------------
    @Test
    public void headerElementsAreDisplayed() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertTrue(header.isBurgerMenuButtonDisplayed());
        Assert.assertTrue(header.isAppLogoDisplayed());
        Assert.assertTrue(header.isCartIconDisplayed());
    }

    @Test
    public void appLogoContentIsCorrect() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertEquals(header.getLogoText(), "Swag Labs");
    }

    @Test
    public void cartIconRedirectsToCartPage() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        header.clickOnCartIcon();

        wait.until(ExpectedConditions.urlToBe(URLs.CART_PAGE_URL));

        Assert.assertEquals(driver.getCurrentUrl(), URLs.CART_PAGE_URL);
        Assert.assertEquals(cartPage.getCartPageTitle(), "Your Cart");
        Assert.assertTrue(cartPage.isCheckoutButtonDisplayed());
    }


    //-------------------------
    @Test
    public void burgerMenuHasAllExpectedOptions() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        header.clickOnBurgerMenuButton();

        wait.until(ExpectedConditions.visibilityOf(header.closeMenuButton));

        Assert.assertTrue(header.isCloseMenuButtonDisplayed());
        Assert.assertTrue(header.areExpectedMenuOptionsDisplayed());
    }

    @Test
    public void userCanLogout() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        header. clickOnBurgerMenuButton();

        wait.until(ExpectedConditions.visibilityOf(header.logoutLink));

        header.clickOnLogoutLink();

        Assert.assertEquals(driver.getCurrentUrl(), URLs.STARTING_PAGE_URL);
        Assert.assertTrue(startingPage.loginButton.isDisplayed());
    }

    @Test
    public void userNavigatesToProductsPageUsingAllItemsOption() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        header.clickOnCartIcon();

        wait.until(ExpectedConditions.urlToBe(URLs.CART_PAGE_URL));

        header.clickOnBurgerMenuButton();

        wait.until(ExpectedConditions.visibilityOf(header.allItemsLink));

        header.clickOnAllItemsLink();

        Assert.assertEquals(driver.getCurrentUrl(), URLs.PRODUCTS_PAGE_URL);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Test
    public void userNavigatesToSauceLabsWebsiteUsingAboutOption() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        header.clickOnBurgerMenuButton();

        wait.until(ExpectedConditions.visibilityOf(header.aboutLink));

        header.clickOnAboutLink();

        Assert.assertEquals(driver.getCurrentUrl(), URLs.SAUCE_LABS_URL);
        Assert.assertTrue(driver.getTitle().contains("Sauce Labs"));
    }

    @Test
    public void resetAppStateClearsCart() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        productsPage.clickOnAddBackpackToCartButton();
        //TODO videti da li random product za ovaj test ima smisla

        Assert.assertEquals(header.getCartBadgeText(), "1");

        header.clickOnBurgerMenuButton();

        wait.until(ExpectedConditions.visibilityOf(header.resetAppStateLink));

        header.clickOnResetAppStateLink();

        Assert.assertTrue(header.isCartBadgeRemoved());
    }

//TODO odluciti sta sa priorities u svim test klasama smisleno...

}
