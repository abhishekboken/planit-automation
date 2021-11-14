package com.planit.tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener extends BrowserHooks implements ITestListener {

    public void onTestFailure(ITestResult result) {
        super.testFailureScreenshot(result);
    }
}

