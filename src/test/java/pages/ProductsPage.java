package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BaseTest {


    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[data-test='title']")
    public WebElement productsPageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addBackpackToCartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement removeBackpackButton;


    @FindBy(className = "inventory_item")
    public List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;


    public String getPageTitle() {
        return productsPageTitle.getText();
    }

    public void clickOnAddBackpackToCartButton() {
        addBackpackToCartButton.click();
    }

    public boolean isAddBackpackToCartButtonDisplayed() {
        return !driver.findElements(By.id("add-to-cart-sauce-labs-backpack")).isEmpty();
    }

    public int getNumberOfProducts() {
        return inventoryItems.size();
    }

    public boolean allProductCardsContainRequiredElements() {
        for (WebElement item : inventoryItems) {
            boolean imageIsDisplayed = item.findElement(By.tagName("img")).isDisplayed();
            boolean titleIsDisplayed = item.findElement(By.className("inventory_item_name")).isDisplayed();
            boolean descriptionIsDisplayed = item.findElement(By.className("inventory_item_desc")).isDisplayed();
            boolean priceIsDisplayed = item.findElement(By.className("inventory_item_price")).isDisplayed();
            boolean buttonIsDisplayed = item.findElement(By.tagName("button")).isDisplayed();

            boolean titleIsNotEmpty = !item.findElement(By.className("inventory_item_name")).getText().isEmpty();
            boolean descriptionIsNotEmpty = !item.findElement(By.className("inventory_item_desc")).getText().isEmpty();
            boolean priceIsNotEmpty = !item.findElement(By.className("inventory_item_price")).getText().isEmpty();

            if (!imageIsDisplayed
                    || !titleIsDisplayed
                    || !descriptionIsDisplayed
                    || !priceIsDisplayed
                    || !buttonIsDisplayed

                    || !titleIsNotEmpty
                    || !descriptionIsNotEmpty
                    || !priceIsNotEmpty) {

                /*System.out.println("Failed product: " + item.findElement(By.className("inventory_item_name")).getText());

                System.out.println("imageIsDisplayed: " + imageIsDisplayed);
                System.out.println("titleIsDisplayed: " + imageIsDisplayed);
                System.out.println("descriptionIsDisplayed: " + imageIsDisplayed);
                System.out.println("priceIsDisplayed: " + imageIsDisplayed);
                System.out.println("buttonIsDisplayed: " + imageIsDisplayed);

                System.out.println("titleIsNotEmpty: " + titleIsNotEmpty);
                System.out.println("descriptionIsNotEmpty: " + descriptionIsNotEmpty);
                System.out.println("priceIsNotEmpty: " + priceIsNotEmpty);*/

                return false;
            }
        }
        return true;
    }

    public List<String> itemTitles() {
        List<String> itemTitles = new ArrayList<>();
        for (WebElement item : inventoryItems) {
            String itemTitle = item.findElement(By.className("inventory_item_name")).getText();
            itemTitles.add(itemTitle);
        }
        return itemTitles;
    }

    public void selectSortingOption(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public List<Double> itemPrices() {

        List<Double> itemPrices = new ArrayList<>();

        for (WebElement item : inventoryItems) {

            String priceText = item.findElement(By.className("inventory_item_price")).getText();

            //replace - metoda klase String (kao sto je i length npr)
            priceText = priceText.replace("$", "");

            //metoda klase Double; parse - procitaj tekst i pretvori ga u nesto smisleno, u odgovarajuci tip podatka
            // (ovde u Double, ali moze i Integer, Boolean...)
            Double price = Double.parseDouble(priceText);

            itemPrices.add(price);
        }
        return itemPrices;
    }

}
