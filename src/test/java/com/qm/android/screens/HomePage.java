package com.qm.android.screens;

import com.qm.android.utils.MobileActions;
import com.qm.utilities.DateUtil;
import com.qm.utilities.DriverFactory;
import com.qm.utilities.MobileUtil;
import com.qm.utilities.ReportManager;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;


public class HomePage {
   public  String ExpectedName;
    String Phone = DateUtil.randomNumberGenerator();

    public static String FirstName = "Auto"+ DateUtil.getCurrentDateTime();

    String LastName = "Auto"+ DateUtil.getCurrentDateTime();

    String Company ="QM";

    MobileActions mobileActions = new MobileActions();


    public void saveButton()
    {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");

    }


    public void deleteContact() throws InterruptedException {
        Thread.sleep(2000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "More_Options"), "More_Options");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Delete"), "Delete");
        Thread.sleep(2000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Delete"), "Delete");
        Thread.sleep(2000);
        mobileActions.enterKeyboard(AndroidKey.ENTER);
        mobileActions.isDisplayed(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "No_Result"), "No Results");
        ReportManager.logInfo("Successfully Contact Deleted and Verified");
    }
    public void searchContact() throws InterruptedException {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "SearchContact"), "Search Contact");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_SearchText"), "Search Contact");
        Thread.sleep(5000);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_SearchText"), ExpectedName);
        Thread.sleep(2000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Contact"), "Contact Details");
        ReportManager.logInfo("Search and Select Contact");
    }

    public void navigateBack()
    {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "Previous Page");
        ReportManager.logInfo("Successfully Navigated to Previous Screen ");
    }

    public void allowAndSkipButton(){
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
//        if(mobileActions.isElmPresent(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow")))
//        {
//            mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
//
//        }
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
    }

    public String createContact() {
        mobileActions.isEnabled(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),"Create Contact button is enabled");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),
                "Create Contact button");
        String expectedCreateContactText = "Create contact";
        String actualCreateContactText = mobileActions
                .getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_CreateContact"));
        mobileActions.verifyText(expectedCreateContactText, actualCreateContactText);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterFirstName"),FirstName);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterLastName"),LastName);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterPhone"),Phone);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        String ExpectedPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Phone"));
        saveButton();
        ExpectedName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"));
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        ReportManager.logInfo("Successfully Contact Created ");
        return ExpectedName;

    }

    public void createAndVerifyContact(){
        mobileActions.isEnabled(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),"Create Contact button is enabled");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),
                "Create Contact button");
        String expectedCreateContactText = "Create contact";
        String actualCreateContactText = mobileActions
                .getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_CreateContact"));
        mobileActions.verifyText(expectedCreateContactText, actualCreateContactText);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterPhone"),Phone);
        String ExpectedPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Phone"));
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterFirstName"),FirstName);
        String ExpectedContactName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_FirstName"));
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        saveButton();
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        String ActualName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"));
        mobileActions.verifyText(ActualName,ExpectedContactName);
        ReportManager.logInfo("Successfully Contact Created and Verified");
    }


    public void addToFavorites() {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Add_To_Favorities"),"Add_To_Favorities");
        navigateBack();
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Contact"), "Contact Deatails");
        mobileActions.isEnabled(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enabled_FavoritesIcon"),"Add_To_Favorities");
        ReportManager.logInfo(" Contact is Added to Favorites ");
    }

    public void sendSmsToContact() throws InterruptedException {

        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Text_SMS"),"Text_SMS");
        String text_Message = "***Test Message***";
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Text_Message"),text_Message);
        Thread.sleep(2000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Send_Message_Button"),"SendMessageButton");
        Thread.sleep(2000);
        navigateBack();
        swichApp();
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("Message", "SearchBar"),"SearchBar");
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("Message", "Enter_SearchBar"),ExpectedName);
        mobileActions.enterKeyboard(AndroidKey.ENTER);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("Message", "Select_Contact"),"Select Contact");
        String Expected_Name = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("Message", "txt_Message"));
        mobileActions.verifyText(text_Message,Expected_Name);
        ReportManager.logInfo("Successfully Verified Sent SMS in Message App ");

    }

    public void swichApp()  {
        Activity activity = new Activity("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity");
        DriverFactory.getInstance().getMobileDriver().startActivity(activity);
        ReportManager.logInfo("Successfully Switched To Message App");
    }

    public void addCompanyNameAndVerifyInExistingContact() throws InterruptedException {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Edit_Contact"), "EditTheContact");
        String company_name = "##QMTest##";
        String email = "qmtestemail@gmail.com";
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_Company"), company_name);
        mobileActions.swipeUpFindElement(1,MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email"));
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email"),email);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "SaveContact");
        ReportManager.logInfo("Successfully added company name and email address");

        navigateBack();
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage","Company"),"ClickCompanyTab");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage","Company_Name"),"ClickCompanyName");
        searchContact();
        mobileActions.swipeUp(1);
        String expected_email = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email_In_ContactInfo"));
        String expected_company_name = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Company_Name_In_ContactInfo"));
        mobileActions.verifyText(email,expected_email);
        mobileActions.verifyText(company_name,expected_company_name);
        ReportManager.logInfo("Successfully verified company name and email");
    }

    public void blockContact(){
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage","Fix&Manage"),"Fix&Manage");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage","Blocked_Contacts"),"Blocked Contacts");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage","Add_A_Number_blocked"),"Adding a number to blocked contacts");

    }

}
