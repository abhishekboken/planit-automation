package com.planit.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class ShopPage extends CommonPageUtil<ShopPage> {

    public static String SHOP_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/shop";

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/bunny.jpg']//ancestor::div/p")
    private WebElement fluffyBunny;

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/cow.jpg']//ancestor::div/p")
    private WebElement funnyCow;

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/frog.jpg']//ancestor::div/p")
    private WebElement stuffedFrog;

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/valentine.jpg']//ancestor::div/p")
    private WebElement valentineBear;

    @FindBy(css = "#nav-cart > a")
    private WebElement cart;

    /**
     * Clicks on buy button for funny cow product.
     */
    public void buyFunnyCow() {
        WebElement funnyCowBuyButton = funnyCow.findElement(By.xpath("a"));
        waitForElementToBeClickable(funnyCowBuyButton);
        funnyCowBuyButton.click();
    }

    /**
     * Clicks on buy button for fluffy bunny product.
     */
    public void buyFluffyBunny() {
        WebElement fluffyBunnyBuyButton = fluffyBunny.findElement(By.xpath("a"));
        fluffyBunnyBuyButton.click();
    }

    /**
     * Clicks on buy button for stuffed frog product.
     */
    public void buyStuffedFrog() {
        WebElement stuffedFrogBuyButton = stuffedFrog.findElement(By.xpath("a"));
        stuffedFrogBuyButton.click();
    }

    /**
     * Clicks on buy button for valentine bear product.
     */
    public void buyValentineBear() {
        WebElement valentineBearBuyButton = valentineBear.findElement(By.xpath("a"));
        valentineBearBuyButton.click();
    }

    /**
     * Returns price of stuffed frog product after removing its dollar symbol.
     */
    public float getStuffedFrogPrice() {
        WebElement stuffedFrogPrice = stuffedFrog.findElement(By.xpath("span"));
        return Float.parseFloat(stuffedFrogPrice.getText().replace("$", ""));
    }

    /**
     * Returns price of fluffy bunny product after removing its dollar symbol.
     */
    public float getFluffyBunnyPrice() {
        WebElement fluffyBunnyPrice = fluffyBunny.findElement(By.xpath("span"));
        return Float.parseFloat(fluffyBunnyPrice.getText().replace("$", ""));
    }

    /**
     * Returns price of valentine bear product after removing its dollar symbol.
     */
    public float getValentineBearPricePrice() {
        WebElement valentineBearPrice = valentineBear.findElement(By.xpath("span"));
        return Float.parseFloat(valentineBearPrice.getText().replace("$", ""));
    }

    /**
     * Clicks on cart button.
     */
    public void clickCart() {
        cart.click();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the home page: " + url, SHOP_PAGE_URL, url);
    }
}
