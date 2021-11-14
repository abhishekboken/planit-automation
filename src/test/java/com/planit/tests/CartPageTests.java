package com.planit.tests;

import com.planit.pageobjects.CartPage;
import com.planit.pageobjects.HomePage;
import com.planit.pageobjects.ShopPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(ScreenshotListener.class)
public class CartPageTests extends BrowserHooks  {
    public static String FIRST_ITEM_NAME = "Funny Cow";
    public static String SECOND_ITEM_NAME = "Fluffy Bunny";
    public static String FIRST_ITEM_IMAGE = "https://jupiter.cloud.planittesting.com/images/src-embed/cow.jpg";
    public static String SECOND_ITEM_IMAGE = "https://jupiter.cloud.planittesting.com/images/src-embed/bunny.jpg";
    public static String FIRST_ITEM_QUANTITY = "2";
    public static String SECOND_ITEM_QUANTITY = "1";

    @Test
    public void addItemsToCart(){
        HomePage homePage = new HomePage();
        homePage.get();
        homePage.clickStartShopping();

        ShopPage shopPage = new ShopPage();
        shopPage.doubleClickBuyCow();
        shopPage.clickBuyBunny();
        shopPage.clickCart();

        CartPage cartPage = new CartPage();
        assertThat("First item " + FIRST_ITEM_NAME + "is not present", cartPage.getFirstCartItemName(),
                equalTo(FIRST_ITEM_NAME));
        assertThat("Second item " + SECOND_ITEM_NAME + "is not present", cartPage.getSecondCartItemName(),
                equalTo(SECOND_ITEM_NAME));
        assertThat("First item image is not present", cartPage.getFirstItemAttribute(),
                equalTo(FIRST_ITEM_IMAGE));
        assertThat("Second item image is not present", cartPage.getSecondItemAttribute(),
                equalTo(SECOND_ITEM_IMAGE));
        assertThat("First item quantity is not " + FIRST_ITEM_QUANTITY, cartPage.getFirstCartItemQuantity(),
                equalTo(FIRST_ITEM_QUANTITY));
        assertThat("Second item quantity is not " + SECOND_ITEM_QUANTITY, cartPage.getSecondCartItemQuantity(),
                equalTo(SECOND_ITEM_QUANTITY));
    }
    }
