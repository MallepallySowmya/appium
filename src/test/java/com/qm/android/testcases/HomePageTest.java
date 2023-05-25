package com.qm.android.testcases;

import com.qm.android.screens.AddContact;
import com.qm.android.screens.HomePage;
import com.qm.listeners.MobileEvent;
import com.qm.listeners.SuiteEvent;
import com.qm.utilities.ReportManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({SuiteEvent.class, MobileEvent.class})

public class HomePageTest {

    @Test(description =  "addContactAndVerifyContactDetails", groups = "regression")

   public void addContactAndVerifyContactDetails()
    {
        ReportManager.logInfo("Create New Contact And Verify Contact Details");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createAndVerifyContact();
    }
    @Test(description =  "addContactAndDeleteContact", groups = "regression")
    public void addContactAndDeleteContact() throws InterruptedException {
        ReportManager.logInfo("AddContactAndDeleteContactDetails");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createContact();
        homePage.navigateBack();
        homePage.searchContact();
        homePage.deleteContact();

    }

    @Test(description =  "addContactAndAddToFavorites", groups = "regression")
    public void addContactAndAddToFavorites() throws InterruptedException {
        ReportManager.logInfo("addContactAndAddToFavorites");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createContact();
        homePage.navigateBack();
        homePage.searchContact();
        homePage.addToFavorites();

    }

    @Test(description =  "sendSmsToContact", groups = "regression")
    public void sendSmsToContact() throws InterruptedException {
        ReportManager.logInfo("sendSmsToContact");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createContact();
        homePage.navigateBack();
        homePage.searchContact();
        homePage.sendSmsToContact();
        Thread.sleep(2000);
    }

    @Test(description =  "addContactAndEditContactDetails", groups = "regression")
    public void addContactAndEditContactDetails() throws InterruptedException {
        ReportManager.logInfo("Create New Contact And Verify Contact Details");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createContact();
        homePage.addCompanyNameAndVerifyInExistingContact();
    }

    @Test(description =  "addContactAndBlock", groups = "regression")
    public void addContactAndBlock(){
        ReportManager.logInfo("Create New Contact And block it");
        HomePage homePage = new HomePage();
        homePage.allowAndSkipButton();
        homePage.createContact();

    }

}
