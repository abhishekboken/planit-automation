package com.planit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class CartPage extends CommonPageUtil<CartPage> {

    public static String CART_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/cart";

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(1)")
    private WebElement firstCartItemName;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(1) img")
    private WebElement firstCartItemImage;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(1)")
    private WebElement secondCartItemName;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(1) img")
    private WebElement secondCartItemImage;

    @FindBy(css = "tbody tr:nth-child(1) td:nth-child(3) input")
    private WebElement firstCartItemQuantity;

    @FindBy(css = "tbody tr:nth-child(2) td:nth-child(3) input")
    private WebElement secondCartItemQuantity;

    public String getFirstCartItemName(){
        return firstCartItemName.getText();
    }

    public String getSecondCartItemName(){
        return secondCartItemName.getText();
    }

    public String getFirstItemAttribute(){
        return firstCartItemImage.getAttribute("src");
    }

    public String getSecondItemAttribute(){
        return secondCartItemImage.getAttribute("src");
    }

    public String getFirstCartItemQuantity(){
        return firstCartItemQuantity.getAttribute("value");
    }

    public String getSecondCartItemQuantity(){
        return secondCartItemQuantity.getAttribute("value");
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
