package com.qm.android.screens;

import com.qm.android.utils.MobileActions;
import com.qm.utilities.DateUtil;
import com.qm.utilities.MobileUtil;
import org.openqa.selenium.By;

public class AddContact {
    String Phone = DateUtil.randomNumberGenerator();
  public static String FirstName = "Auto"+ DateUtil.getCurrentDateTime();
    String LastName = "Auto"+ DateUtil.getCurrentDateTime();
    String Company ="QM";
    MobileActions mobileActions = new MobileActions();

    public void createAndVerifyContact(){
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
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
        String contactName = mobileActions.getText(By.xpath("(//android.widget.EditText)[1]"));
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        saveButton();
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        String ActualName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"));
        mobileActions.verifyText(ActualName,contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
    }

    public void addToFavourites() throws InterruptedException {

//        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
//        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
//        mobileActions.isEnabled(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),"Create Contact button is enabled");
//        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),
//                "Create Contact button");
//        String expectedCreateContactText = "Create contact";
//        String actualCreateContactText = mobileActions
//                .getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_CreateContact"));
//        mobileActions.verifyText(expectedCreateContactText, actualCreateContactText);
//        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterPhone"),Phone);
//        String ExpectedPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Phone"));
//        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterFirstName"),FirstName);
//        String contactName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_FirstName"));
//        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
//        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
//        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
//        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        navigateBack();
        searchContact(FirstName);
        Thread.sleep(3000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Searched_Contact"),"Searched_Contact");
        Thread.sleep(10000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Add_To_Favorities"),"Searched_Contact");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
    }



    public void editContactWithEmailAndCompanyDetails() throws InterruptedException {

        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
        mobileActions.isEnabled(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"), "Create Contact button is enabled");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_CreateContact"),
                "Create Contact button");
        String expectedCreateContactText = "Create contact";
        String actualCreateContactText = mobileActions
                .getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_CreateContact"));
        mobileActions.verifyText(expectedCreateContactText, actualCreateContactText);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterPhone"), Phone);
        String ExpectedPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Phone"));
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterFirstName"), FirstName);
        String contactName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_FirstName"));
        System.out.println("*****" + contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        Thread.sleep(15000);
        searchContact(contactName);
        Thread.sleep(3000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Searched_Contact"), "Searched_Contact");
        Thread.sleep(5000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Edit_Contact"), "EditTheContact");
        Thread.sleep(3000);
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_Company"), "##QMTest##");
        Thread.sleep(3000);
        mobileActions.swipeUpFindElement(1,MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email"));
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email"), "qmtestemail@gmail.com");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "SaveContact");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Phone_Contacts_Tab"), "Phone_Contacts_Tab");
        searchContact(contactName);
        String actualContactName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"), "GetContactName");
        mobileActions.verifyText(actualContactName,contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Phone_Contacts_Tab"), "Phone_Contacts_Tab");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email_Contacts_Tab"), "Email_Contacts_Tab");
        searchContact(contactName);
        String actualContactName1 = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"), "GetContactName");
        mobileActions.isDisplayed(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email_In_Contact"), "EmailVerificationInContact");
        mobileActions.verifyText(actualContactName1,contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Email_Contacts_Tab"), "Email_Contacts_Tab");

        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Company"), "Company_Tab");
        searchContact(contactName);
        String actualContactName2 = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_Name"), "GetContactName");
        mobileActions.isDisplayed(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Company_In_Contact"), "CompanyVerificationInContact");
        mobileActions.verifyText(actualContactName2,contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");


    }


    public void deleteTheContact() throws InterruptedException {

        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
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
        String contactName = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_FirstName"));
        System.out.println("*****"+contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        Thread.sleep(15000);
        searchContact(contactName);
        Thread.sleep(3000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Searched_Contact"),"Searched_Contact");
        Thread.sleep(5000);


    }

    public void sendSmsToContact() throws InterruptedException {

        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
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
        String contactName =mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_FirstName"));
        System.out.println("*****"+contactName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "NavigateUp");
        Thread.sleep(15000);
        searchContact(contactName);
        Thread.sleep(3000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Searched_Contact"),"Searched_Contact");
        Thread.sleep(5000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Text_SMS"),"Text_SMS");
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Text_Message"),"***TestMessage***");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Send_Message_Button"),"SendMessageButton");

    }

    public void saveButton()
    {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
        return;
    }


    public void deleteContact()
    {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "More_Options"), "More_Options");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Delete"), "Delete");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Delete"), "Delete");
    }
    public void searchContact(String value) throws InterruptedException {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "SearchContact"), "Search Contact");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_SearchText"), "Search Contact");
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Enter_SearchText"), value);
        Thread.sleep(2000);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Contact"), "Contact Deatails");

    }

    public void navigateBack()
    {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "NavigateUp"), "Previous Page");
        return;
    }

  /*  public void addContact(String Phone, String FirstName, String LastName) {
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("PopUp", "btn_allow"), "allow button");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsWithGoogle", "btn_skip"), "Skip button");
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
        mobileActions.sendKeys(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "EnterLastName"),LastName);
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "Mobile"), "Mobile");
        mobileActions.click(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "btn_Save"), "Save");
        String ActualPhoneNo = mobileActions.getText(MobileUtil.returnByBasedOnPageNameAndObjectName("ContactsPage", "txt_PhoneNo"));
        mobileActions.verifyText(ExpectedPhoneNo, ActualPhoneNo);
    }*/
}
