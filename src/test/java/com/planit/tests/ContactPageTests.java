package com.planit.tests;

import com.planit.pageobjects.ContactPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

@Listeners(ScreenshotListener.class)
public class ContactPageTests extends BrowserHooks {

    public static final String FORENAME = "TestForename";
    public static final String EMAIL = "testemail@xyz.com";
    public static final String FEEDBACK_MESSAGE = "Test Message";

    @Test
    public void mandatoryFields() {
        ContactPage contact = new ContactPage();
        contact.get();

        contact.clickSubmitButton();
        assertThat("Forename mandatory error message is not present", contact.getForeNameErrorMessage(),
                equalTo("Forename is required"));
        assertThat("Email mandatory error message is not present", contact.getEmailErrorMessage(),
                equalTo("Email is required"));
        assertThat("Message field mandatory error message is not present", contact.getMessageError(),
                equalTo("Message is required"));

        contact.enterForeName(FORENAME);
        assertFalse("Forename mandatory error message is still present", contact.foreNameWebElementPresence());

        contact.enterEmail(EMAIL);
        assertFalse("Email mandatory error message is still present", contact.emailWebElementPresence());

        contact.enterMessage(FEEDBACK_MESSAGE);
        assertFalse("Message mandatory error message is still present", contact.messageWebElementPresence());
    }

    @Test (invocationCount = 5)
    public void submitFeedback() {
        ContactPage contact = new ContactPage();
        contact.get();

        contact.enterForeName(FORENAME);
        contact.enterEmail(EMAIL);
        contact.enterMessage(FEEDBACK_MESSAGE);
        contact.clickSubmitButton();

        assertThat("Feedback success message is not displayed", contact.getSuccessMessage(),
                equalTo("Thanks " + FORENAME +  ", we appreciate your feedback."));
    }

}
