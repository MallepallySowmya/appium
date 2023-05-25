package com.qm.utilities;

import org.testng.log4testng.Logger;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {

    public Logger log = Logger.getLogger(com.codoid.products.utils.DateUtil.class);
    String Date;
    String Month;
    String Year;
    String PreviousDate;

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public void setPreviousDate(String previousDate) {
        PreviousDate = previousDate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void getCalendarDay() throws ParseException {

        if (Integer.parseInt(getCurrentDate().substring(0, 2)) < 10) {
            setDate(getCurrentDate().toString().substring(1, 2));
        } else {
            setDate(getCurrentDate().toString().substring(0, 2));
        }
        log.info("Current Date is : " + getDate());
        setMonth(getCurrentDate().toString().substring(3, 6));
        log.info("Current Month is : " + getMonth());
        setYear(getCurrentDate().toString().substring(7, 11));
        log.info("Current Year is : " + getYear());
        if (Integer.parseInt(getPreviousDay().substring(0, 2)) < 10) {
            setPreviousDate(getPreviousDay().toString().substring(1, 2));
        } else {
            setPreviousDate(getPreviousDay().toString().substring(0, 2));
        }
        log.info("Previous Date is : " + getPreviousDate());
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        java.util.Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public static String getCurrentDateStamp() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentLongDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getPreviousDay() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(new Date(dateFormat.parse(dateFormat.format(date)).getTime() - (24 * 3600000)));
    }

    public static String getNextDay() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(new Date(dateFormat.parse(dateFormat.format(date)).getTime() + (24 * 3600000)));
    }

    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String randomNumberGenerator() {
        Random rand = new Random();
        int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
        int num2 = rand.nextInt(743);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

        String phoneNumber = df3.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);
        System.out.println(phoneNumber);
        return phoneNumber;
    }

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
