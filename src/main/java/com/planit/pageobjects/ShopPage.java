package com.planit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class ShopPage extends CommonPageUtil<ShopPage>{

    public static String SHOP_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/shop";

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/bunny.jpg']//ancestor::div/p/a")
    private WebElement buyBunny;

    @FindBy(xpath = "//div/img[@ng-src='images/src-embed/cow.jpg']//ancestor::div/p/a")
    private WebElement buyCow;

    @FindBy(css = "#nav-cart > a")
    private WebElement cart;

    public void doubleClickBuyCow(){
        Actions act = new Actions(getDriver());
        act.doubleClick(buyCow).perform();
    }

    public void clickBuyBunny(){
        buyBunny.click();
    }

    public void clickCart(){
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
