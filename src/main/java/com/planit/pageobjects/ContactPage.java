package com.planit.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class ContactPage extends CommonPageUtil<ContactPage> {

    public static String CONTACT_PAGE_URL = "https://jupiter.cloud.planittesting.com/#/contact";

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

    /**
     * Inputs the first name.
     */
    public void enterForeName(String forename) {
        foreNameField.sendKeys(forename);
    }

    /**
     * Inputs the email address.
     */
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    /**
     * Inputs the feedback message.
     */
    public void enterMessage(String message) {
        messageField.sendKeys(message);
    }

    /**
     * Click on the submit button.
     */
    public void clickSubmitButton() {
        clickByJavaScript(submitButton);
    }

    /**
     * Returns the first name mandatory field error message.
     */
    public String getForeNameErrorMessage() {
        return foreNameError.getText();
    }

    /**
     * Returns a boolean value for the presence of first name mandatory field error message.
     */
    public boolean foreNameWebElementPresence() {
        try{
            return foreNameError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Returns the email mandatory field error message.
     */
    public String getEmailErrorMessage() {
        return emailError.getText();
    }

    /**
     * Returns a boolean value for the presence of email mandatory field error message.
     */
    public boolean emailWebElementPresence() {
        try{
            return emailError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Returns the feedback mandatory field error message.
     */
    public String getMessageError() {
        return messageError.getText();
    }

    /**
     * Returns a boolean value for the presence of feedback mandatory field error message.
     */
    public boolean messageWebElementPresence() {
        try{
            return messageError.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * Returns the success message on submitting the feedback form.
     */
    public String getSuccessMessage(){
        waitForExpectedElement(successMessage);
        return successMessage.getText();
    }

    @Override
    protected void load() {
        getDriver().get(CONTACT_PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the contact page: " + url, CONTACT_PAGE_URL, url);
    }
}


