package com.planit.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    public static WebDriver driver;
    private static final int TIMEOUT = 15;

    /**
     * WebDriver instance setup using bonigarcia.
     */
    public static void setup() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
        }

    public static void teardown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

