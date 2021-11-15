package com.planit.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.planit.core.DriverFactory.*;

public class BaseTest {

    @BeforeMethod
    public void initialize() {
        setup();
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
    }

    /**
     * Captures the screenshot for failed test step under Report output in the testng report.
     * @param result instance that provides the result of the test
     */
    public void testFailureScreenshot(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
                        + "/target/surefire-reports";
                File destFile = new File(reportDirectory + "/failure_screenshots/" + methodName + "_"
                        + formatter.format(calendar.getTime()) + ".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
                        + "' height='300' width='400'/> </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterMethod
    public void close() {
        teardown();
    }
}

