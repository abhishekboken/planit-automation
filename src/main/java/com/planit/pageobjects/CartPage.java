package com.planit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class CartPage extends CommonPageUtil<CartPage> {

    public static String CART_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/cart";

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(1)")
    private WebElement firstCartItemName;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(2)")
    private WebElement firstCartItemPrice;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(3) input")
    private WebElement firstCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(4)")
    private WebElement firstCartItemSubTotal;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(1)")
    private WebElement secondCartItemName;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(2)")
    private WebElement secondCartItemPrice;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(3) input")
    private WebElement secondCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(4)")
    private WebElement secondCartItemSubTotal;

    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(1)")
    private WebElement thirdCartItemName;

    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(2)")
    private WebElement thirdCartItemPrice;

    @FindBy(css = "tbody tr:nth-child(3) td:nth-child(4)")
    private WebElement thirdCartItemSubTotal;

    @FindBy(className = "total")
    private WebElement cartItemTotal;

    public String getFirstCartItemName() {
        return firstCartItemName.getText();
    }

    public String getSecondCartItemName() {
        return secondCartItemName.getText();
    }

    /**
     * Returns image URL for first item in cart.
     */
    public String getFirstItemAttribute() {
        WebElement firstCartItemImage = firstCartItemName.findElement(By.cssSelector("img"));
        return firstCartItemImage.getAttribute("src");
    }

    /**
     * Returns image URL for second item in cart.
     */
    public String getSecondItemAttribute() {
        WebElement secondCartItemImage = secondCartItemName.findElement(By.cssSelector("img"));
        return secondCartItemImage.getAttribute("src");
    }

    /**
     * Returns product quantity for first item in cart.
     */
    public int getFirstCartItemQuantity() {
        return Integer.parseInt(firstCartItemQuantity.getAttribute("value"));
    }

    /**
     * Returns product quantity for second item in cart.
     */
    public int getSecondCartItemQuantity() {
        return Integer.parseInt(secondCartItemQuantity.getAttribute("value"));
    }

    /**
     * Returns price of the first item in cart after removing its dollar symbol.
     */
    public float getFirstItemPrice() {
        return Float.parseFloat(firstCartItemPrice.getText().replace("$", ""));
    }

    /**
     * Returns price of the second item in cart after removing its dollar symbol.
     */
    public float getSecondItemPrice() {
        return Float.parseFloat(secondCartItemPrice.getText().replace("$", ""));
    }

    /**
     * Returns price of the third item in cart after removing its dollar symbol.
     */
    public float getThirdItemPrice() {
        return Float.parseFloat(thirdCartItemPrice.getText().replace("$", ""));
    }

    /**
     * Returns subtotal of first item in cart after removing its dollar symbol.
     */
    public float getFirstItemSubTotal() {
        return Float.parseFloat(firstCartItemSubTotal.getText().replace("$", ""));
    }

    /**
     * Returns subtotal of second item in cart after removing its dollar symbol.
     */
    public float getSecondItemSubTotal() {
        return Float.parseFloat(secondCartItemSubTotal.getText().replace("$", ""));
    }

    /**
     * Returns subtotal of third item in cart after removing its dollar symbol.
     */
    public float getThirdItemSubTotal() {
        return Float.parseFloat(thirdCartItemSubTotal.getText().replace("$", ""));
    }

    /**
     * Returns total of all items in cart after removing its dollar symbol.
     */
    public float getCartItemTotal() {
        return Float.parseFloat(cartItemTotal.getText().split(" ")[1].replace("$", ""));
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the home page: " + url, CART_PAGE_URL, url);
    }
}
