package tests;

import application.BuiltInUsers;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductsTests extends BaseTest {

    @Test
    public void allSixProductsAreDisplayed() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertEquals(productsPage.getNumberOfProducts(), 6);
    }

    @Test
    public void allProductCardsContainRequiredElements() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        Assert.assertTrue(productsPage.allProductCardsContainRequiredElements());
    }

    @Test
    public void allSortingOptionsWorkCorrectly() {
        startingPage.logIn(BuiltInUsers.STANDARD_USER, BuiltInUsers.PASSWORD);

        List<String> actualTitles;
        List<String> expectedTitles;
        List<Double> actualPrices;
        List<Double> expectedPrices;


        productsPage.selectSortingOption("Name (A to Z)");

        actualTitles = productsPage.itemTitles();
        expectedTitles = new ArrayList<>(actualTitles);
        // utility klasa sa alatima, ne brkati sa interfejsom Collection...
        Collections.sort(expectedTitles); // java Stringove sortira alfabetski, prirodni redosled Stringova...

        Assert.assertEquals(actualTitles, expectedTitles);


        productsPage.selectSortingOption("Name (Z to A)");

        actualTitles = productsPage.itemTitles();
        expectedTitles = new ArrayList<>(actualTitles);

        Collections.sort(expectedTitles);
        Collections.reverse(expectedTitles);

        Assert.assertEquals(actualTitles, expectedTitles);


        productsPage.selectSortingOption("Price (low to high)");

        actualPrices = productsPage.itemPrices();
        expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);


        productsPage.selectSortingOption("Price (high to low)");

        actualPrices = productsPage.itemPrices();
        expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);
        Collections.reverse(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);

    }


}
