package com.planit.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.planit.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class ContactPage extends CommonPageUtil<ContactPage> {

    public static String CONTACTPAGEURL = "https://jupiter.cloud.planittesting.com/#/contact";
    public static final String FORENAME = "TestForename";
    public static final String EMAIL = "testemail@xyz.com";
    public static final String MESSAGE = "Test Message";

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

    public void enterForeName() {
        foreNameField.sendKeys(FORENAME);
    }

    public void enterEmail() {
        emailField.sendKeys(EMAIL);
    }

    public void enterMessage() {
        messageField.sendKeys(MESSAGE);
    }

    public void clickSubmitButton() {
        submitButton.click();
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

    @Override
    protected void load() {
        getDriver().get(CONTACTPAGEURL);
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageLoad();
        String url = getDriver().getCurrentUrl();
        assertEquals("Not on the contact page: " + url, "https://jupiter.cloud.planittesting.com/#/contact", url);
    }
}


