package com.planit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class HomePage extends CommonPageUtil<HomePage>{

    public static String HOMEPAGE_URL = "https://jupiter.cloud.planittesting.com/#/";

    @FindBy(css = "div.hero-unit > p:nth-child(3)  > a[href*='#/shop']")
    private WebElement startShopping;

    public void clickStartShopping() {
        startShopping.click();
    }

    @Override
    protected void load() {
        getDriver().get(HOMEPAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the home page: " + url, HOMEPAGE_URL, url);
    }
}
