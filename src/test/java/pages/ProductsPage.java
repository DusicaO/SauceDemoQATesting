package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BaseTest {


    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[data-test='title']")
    public WebElement productsPageTitle;


    public String getPageTitle() {
        return productsPageTitle.getText();
    }

}
