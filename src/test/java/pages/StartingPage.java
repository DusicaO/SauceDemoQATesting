package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartingPage extends BaseTest {


    public StartingPage() {
        PageFactory.initElements(driver, this);
    }


    //Login functionality/section:
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    public WebElement errorMessage;

    @FindBy(css = "[data-test='error-button']")
    public WebElement errorButton;



    public void inputUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void logIn(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        clickOnLoginButton();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return  errorMessage.isDisplayed();
    }

    public void clickOnErrorButton() {
        errorButton.click();
    }

    public boolean isErrorMessageRemoved() {
        return driver.findElements(By.cssSelector("[data-test='error']")).isEmpty();
    }



}
