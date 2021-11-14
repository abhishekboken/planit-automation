package com.planit.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class ContactPage extends CommonPageUtil<ContactPage> {

    public static String CONTACTPAGE_URL = "https://jupiter.cloud.planittesting.com/#/contact";

    @FindBy(id = "forename")
    private WebElement foreNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(className = "btn-contact")
    private WebElement submitButton;

    @FindBy(id = "forename-err")
    private WebElement foreNameError;

    @FindBy(id = "email-err")
    private WebElement emailError;

    @FindBy(id = "message-err")
    private WebElement messageError;

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    public void enterForeName(String forename) {
        foreNameField.sendKeys(forename);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterMessage(String message) {
        messageField.sendKeys(message);
    }

    public void clickSubmitButton() {
        clickByJavaScript(submitButton);
    }

    public String getForeNameErrorMessage() {
        return foreNameError.getText();
    }

    public boolean foreNameWebElementPresence() {
        try{
            return foreNameError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getEmailErrorMessage() {
        return emailError.getText();
    }

    public boolean emailWebElementPresence() {
        try{
            return emailError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getMessageError() {
        return messageError.getText();
    }

    public boolean messageWebElementPresence() {
        try{
            return messageError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public String getSuccessMessage(){
        waitForExpectedElement(successMessage);
        return successMessage.getText();
    }

    @Override
    protected void load() {
        getDriver().get(CONTACTPAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the contact page: " + url, CONTACTPAGE_URL, url);
    }
}


