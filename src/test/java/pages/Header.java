package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends BaseTest {

    public Header() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenuButton;

    @FindBy(id = "react-burger-cross-btn")
    public WebElement closeMenuButton;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItemsLink;

    @FindBy(id="about_sidebar_link")
    public WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutLink;

    @FindBy(id="reset_sidebar_link")
    public WebElement resetAppStateLink;


    @FindBy(className = "app_logo")
    public WebElement appLogo;


    @FindBy(className = "shopping_cart_link")
    public WebElement cartLink;

    @FindBy(className="shopping_cart_badge")
    public WebElement cartBadge;


    public boolean isBurgerMenuButtonDisplayed() {
        return burgerMenuButton.isDisplayed();
    }

    public boolean isAppLogoDisplayed() {
        return appLogo.isDisplayed();
    }

    public boolean isCartIconDisplayed() {
        return cartLink.isDisplayed();
    }

    public String getLogoText() {
        return appLogo.getText();
    }

    public void clickOnCartIcon() {
        cartLink.click();
    }

    public void clickOnBurgerMenuButton() {
        burgerMenuButton.click();
    }

    public boolean areExpectedMenuOptionsDisplayed() {
        return allItemsLink.isDisplayed()
                && aboutLink.isDisplayed()
                && logoutLink.isDisplayed()
                && resetAppStateLink.isDisplayed();
    }

    public boolean isCloseMenuButtonDisplayed() {
        return closeMenuButton.isDisplayed();
    }

    //------------------------
    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void clickOnAllItemsLink() {
        allItemsLink.click();
    }

    public void clickOnAboutLink() {
        aboutLink.click();
    }

    public String getCartBadgeText() {
        return cartBadge.getText();
    }

    public void clickOnResetAppStateLink() {
        resetAppStateLink.click();
    }

    public boolean isCartBadgeRemoved() {
        return driver.findElements(By.className("shopping_cart_badge")).isEmpty();
    }

}
