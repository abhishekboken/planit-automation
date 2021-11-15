package com.planit.tests;

import com.planit.pageobjects.CartPage;
import com.planit.pageobjects.HomePage;
import com.planit.pageobjects.ShopPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(ScreenshotListener.class)
public class CartPageTests extends BaseTest {

    /**
     * Test data provider for first test case.
     */
    @DataProvider(name = "testData1")
    public Object[][] dataSet1() {
        return new Object[][]{
                {"Funny Cow", "https://jupiter.cloud.planittesting.com/images/src-embed/cow.jpg", 2,
                        "Fluffy Bunny", "https://jupiter.cloud.planittesting.com/images/src-embed/bunny.jpg", 1},
        };
    }

    /**
     * This test method adds products to the cart and verifies the items added to the cart.
     *
     * @param firstProductName      name of first item expected in cart
     * @param firstProductImageUrl  image URL first item expected in cart
     * @param firstProductQuantity  quantity of first item expected in cart
     * @param secondProductName     name of second item expected in cart
     * @param secondProductImageUrl image URL second item expected in cart
     * @param secondProductQuantity name of second item expected in cart
     */
    @Test(dataProvider = "testData1")
    public void addItemsToCart(String firstProductName, String firstProductImageUrl, int firstProductQuantity,
                               String secondProductName, String secondProductImageUrl, int secondProductQuantity) {
        HomePage homePage = new HomePage();
        homePage.get();
        homePage.clickStartShopping();

        ShopPage shopPage = new ShopPage();

        // Clicks on buy button twice for funny cow product
        IntStream.rangeClosed(0, firstProductQuantity - 1).forEach(i -> shopPage.buyFunnyCow());

        // Clicks on buy button for fluffy bunny product
        shopPage.buyFluffyBunny();

        shopPage.clickCart();

        // Verifies the product names of items in cart
        CartPage cartPage = new CartPage();
        assertThat("First item " + firstProductName + " is not present", cartPage.getFirstCartItemName(),
                equalTo(firstProductName));
        assertThat("Second item " + secondProductName + " is not present", cartPage.getSecondCartItemName(),
                equalTo(secondProductName));

        // Verifies the image URLs of the items in cart
        assertThat("First item image is not present", cartPage.getFirstItemAttribute(),
                equalTo(firstProductImageUrl));
        assertThat("Second item image is not present", cartPage.getSecondItemAttribute(),
                equalTo(secondProductImageUrl));

        // Verifies the product quantities of the items in cart
        assertThat("First item quantity is not " + firstProductQuantity, cartPage.getFirstCartItemQuantity(),
                equalTo(firstProductQuantity));
        assertThat("Second item quantity is not " + secondProductQuantity, cartPage.getSecondCartItemQuantity(),
                equalTo(secondProductQuantity));
    }

    /**
     * Test data provider for second test case.
     */
    @DataProvider(name = "testData2")
    public Object[][] dataSet2() {
        return new Object[][]{
                {2, 5, 3}
        };
    }

    /**
     * This test method verifies product price, subtotal and total on cart page.
     *
     * @param firstProductQuantity  quantity of first item expected in cart
     * @param secondProductQuantity quantity of second item expected in cart
     * @param thirdProductQuantity  quantity of third item expected in cart
     */
    @Test(dataProvider = "testData2")
    public void verifyCartPrice(int firstProductQuantity, int secondProductQuantity, int thirdProductQuantity) {
        HomePage homePage = new HomePage();
        homePage.get();
        homePage.clickStartShopping();

        ShopPage shopPage = new ShopPage();

        // Clicks on buy button twice for stuffed frog cow product and captures its price
        IntStream.rangeClosed(0, firstProductQuantity - 1).forEach(i -> shopPage.buyStuffedFrog());
        float firstProductPrice = shopPage.getStuffedFrogPrice();

        // Clicks on buy button five times for fluffy bunny product and captures its price
        IntStream.rangeClosed(0, secondProductQuantity - 1).forEach(i -> shopPage.buyFluffyBunny());
        float secondProductPrice = shopPage.getFluffyBunnyPrice();

        // Clicks on buy button three times for valentine bear product and captures its price
        IntStream.rangeClosed(0, thirdProductQuantity - 1).forEach(i -> shopPage.buyValentineBear());
        float thirdProductPrice = shopPage.getValentineBearPricePrice();

        shopPage.clickCart();

        // Verifies product price for all three products on cart page
        CartPage cartPage = new CartPage();
        assertThat("First item price is not as expected", cartPage.getFirstItemPrice(),
                equalTo(firstProductPrice));
        assertThat("Second item price is not as expected", cartPage.getSecondItemPrice(),
                equalTo(secondProductPrice));
        assertThat("Third item price is not as expected", cartPage.getThirdItemPrice(),
                equalTo(thirdProductPrice));

        // Calculates the subtotal of all three products for further assertions
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        float firstItemSubtotal = Float.parseFloat(df.format(cartPage.getFirstItemPrice() * firstProductQuantity));
        float secondItemSubtotal = Float.parseFloat(df.format(cartPage.getSecondItemPrice() * secondProductQuantity));
        float thirdItemSubtotal = Float.parseFloat(df.format(cartPage.getThirdItemPrice() * thirdProductQuantity));

        // Verifies the subtotal of all three products on cart page
        assertThat("First item subtotal is not as expected", cartPage.getFirstItemSubTotal(),
                equalTo(firstItemSubtotal));
        assertThat("Second item subtotal is not as expected", cartPage.getSecondItemSubTotal(),
                equalTo(secondItemSubtotal));
        assertThat("Third item subtotal is not as expected", cartPage.getThirdItemSubTotal(),
                equalTo(thirdItemSubtotal));

        // Verifies the total of all three products on cart page
        assertThat("Total is not as expected", firstItemSubtotal + secondItemSubtotal + thirdItemSubtotal,
                equalTo(cartPage.getCartItemTotal()));
    }
}
