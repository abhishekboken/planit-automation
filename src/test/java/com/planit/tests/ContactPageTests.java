package com.planit.tests;

import com.planit.pageobjects.ContactPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@Listeners(ScreenshotListener.class)
public class ContactPageTests extends BrowserHooks {

    @Test
    public void mandatoryFields(){
        ContactPage contact = new ContactPage();
        contact.get();

        contact.clickSubmitButton();
        assertThat("Forename mandatory error message is not present", contact.getForeNameErrorMessage(),
                equalTo("Forename is required"));
        assertThat("Email mandatory error message is not present", contact.getEmailErrorMessage(),
                equalTo("Email is required"));
        assertThat("Message field mandatory error message is not present", contact.getMessageError(),
                equalTo("Message is required"));

        contact.enterForeName();
        assertFalse("Forename mandatory error message is still present", contact.foreNameWebElementPresence());

        contact.enterEmail();
        assertFalse("Email mandatory error message is still present", contact.emailWebElementPresence());

        contact.enterMessage();
        assertFalse("Message mandatory error message is still present", contact.messageWebElementPresence());
    }
}
