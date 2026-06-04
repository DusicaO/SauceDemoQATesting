package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[data-test='title']")
    public WebElement cartPageTitle;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;


    public String getCartPageTitle() {
        return cartPageTitle.getText();
    }

    public boolean isCheckoutButtonDisplayed() {
        return checkoutButton.isDisplayed();
    }

    /*public void clickOnCheckoutButton() {
        checkoutButton.click();
    }*/
}
