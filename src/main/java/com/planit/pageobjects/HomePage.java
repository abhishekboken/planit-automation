package com.planit.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class HomePage extends CommonPageUtil<HomePage>{

    public static String HOME_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/";

    @FindBy(css = "div.hero-unit > p:nth-child(3)  > a[href*='#/shop']")
    private WebElement startShopping;

    /**
     * Clicks start shopping button on the home page.
     */
    public void clickStartShopping() {
        startShopping.click();
    }

    @Override
    protected void load() {
        getDriver().get(HOME_PAGE_URL);
        log.info("Navigating to home page: " + HOME_PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the home page: " + url, HOME_PAGE_URL, url);
    }
}
