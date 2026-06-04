package tests;

import application.BuiltInUsers;
import application.URLs;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//TODO odluciti sta sa priorities u svim test klasama smisleno...

public class LoginTests extends BaseTest {

    //login flow with one of the builtin users
    //test case LG001
    @Test(priority = 10)
    public void standardUserCanLogIn() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertEquals(driver.getCurrentUrl(), URLs.PRODUCTS_PAGE_URL);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    //test cases LG002-LG007
    @Test(dataProvider = "invalidLoginData", priority = 20)
    public void userCannotLogInWithInvalidOrEmptyCredentials(
            String username,
            String password,
            String expectedErrorMessage) {
        startingPage.logIn(username, password);

        Assert.assertEquals(driver.getCurrentUrl(), URLs.STARTING_PAGE_URL);
        Assert.assertTrue(startingPage.isErrorMessageDisplayed());
        Assert.assertTrue(startingPage.getErrorMessageText().contains(expectedErrorMessage));
    }

    //enriched test case LG008 - after each failed login combination
    @Test(dataProvider = "invalidLoginData", priority = 30)
    public void userCanLogInAfterFailedLoginAttempt(String username,
                                                    String password,
                                                    String unusedParameter) {

        startingPage.logIn(username, password);

        Assert.assertTrue(startingPage.isErrorMessageDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), URLs.STARTING_PAGE_URL);

        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertEquals(driver.getCurrentUrl(), URLs.PRODUCTS_PAGE_URL);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    //enriched test case LG009 - after each failed login combination
    @Test(dataProvider = "invalidLoginData", priority = 40)
    public void errorMessageCanBeDismissed(String username,
                                           String password,
                                           String unusedParameter) {

        startingPage.logIn(username, password);

        Assert.assertTrue(startingPage.isErrorMessageDisplayed());
        //No meaningful additional verification identified.

        startingPage.clickOnErrorButton();

        Assert.assertTrue(startingPage.isErrorMessageRemoved());
        //No meaningful additional verification identified.

        //TODO are there more meaningful additional verifications for either step?
    }

    //-------------------------

    //login with all other builtin users
    @Test(priority = 50)
    public void lockedOutUserCannotLogIn() {
        startingPage.logIn(BuiltInUsers.LOCKED_OUT_USER, BuiltInUsers.PASSWORD);

        Assert.assertEquals(driver.getCurrentUrl(), URLs.STARTING_PAGE_URL);
        Assert.assertTrue(startingPage.isErrorMessageDisplayed());
        Assert.assertTrue(startingPage.getErrorMessageText().contains("this user has been locked out"));
    }

    @Test(dataProvider = "allOtherBuiltInUsers", priority = 60)
    public void specialUsersCanLogIn(String username) {
        startingPage.logIn(username, BuiltInUsers.PASSWORD);

        Assert.assertEquals(driver.getCurrentUrl(), URLs.PRODUCTS_PAGE_URL);
        Assert.assertEquals(productsPage.getPageTitle(), "Products");

        //performance_glitch_user login was stable in repeated runs without additional explicit wait.
        //TODO Investigate why performance_glitch_user login repeatedly passes without additional wait.
    }


    //----------------------------------
    //username, password, expectedErrorMessage
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {"wrong_user", BuiltInUsers.PASSWORD,
                        "Username and password do not match any user"},
                {BuiltInUsers.STANDARD_USER, "wrong_password",
                        "Username and password do not match any user"},
                {"wrong_user", "wrong_password",
                        "Username and password do not match any user"},
                {"", BuiltInUsers.PASSWORD,
                        "Username is required"},
                {BuiltInUsers.STANDARD_USER, "",
                        "Password is required"},
                {"", "",
                        "Username is required"}
        };
    }

    @DataProvider(name = "allOtherBuiltInUsers")
    public Object[][] builtinValidUsers() {
        return new Object[][]{
                {BuiltInUsers.PROBLEM_USER},
                {BuiltInUsers.PERFORMANCE_GLITCH_USER},
                {BuiltInUsers.ERROR_USER},
                {BuiltInUsers.VISUAL_USER}
        };
    }

}
