package com.planit.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener extends BaseTest implements ITestListener {

    /**
     * This method is invoked to capture a screenshot when any test method gets failed.
     * @param result instance that provides the result of the test
     */
    public void onTestFailure(ITestResult result) {
        super.testFailureScreenshot(result);
    }
}

