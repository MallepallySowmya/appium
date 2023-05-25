package com.qm.listeners;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.model.Test;
import com.qm.android.utils.MobileActions;
import com.qm.utilities.ConfigReader;
import com.qm.utilities.DriverFactory;
import com.qm.utilities.InitDriver;
import com.qm.utilities.MobilescreenRecord;
import com.qm.utilities.ReportManager;

public class MobileEvent implements ITestListener {

	InitDriver initDriver = new InitDriver();
	private static final String KEY = "platform";
	private static final String KEY1 = "udid";
	private static final String KEY2 = "systemPort";
	private static final String KEY3 = "deviceName";

	private static final String KEY4 = "deviceVersion";

	public String Platform;
	public String Udid;
	public String SystemPort;
	public String DeviceName = "";

	public String DeviceVersion = "";
	MobilescreenRecord mobilescreenRecord = new MobilescreenRecord();

	@Override
	public void onTestStart(ITestResult arg0) {

		ReportManager.startTestMobile(arg0.getMethod().getMethodName(), arg0.getMethod().getDescription(),
				ConfigReader.getValue("Execution_Mobile"));
		try {

			// If Test is directly running by dev team then it will value from appium config
			// file
			if (Platform == null) {
				Platform = ConfigReader.getAppiumProp("platform");
				Udid = ConfigReader.getAppiumProp("udid");
				SystemPort = ConfigReader.getAppiumProp("port");
				DeviceName = "";
				DeviceVersion = "";
			}

			System.out.println("key: " + Platform);
			System.out.println("key1: " + Udid);
			System.out.println("key2: " + SystemPort);

			initDriver.startMobileDriver(Platform, Udid, SystemPort, DeviceName, DeviceVersion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String description = arg0.getMethod().getDescription();
		String temp = description.split("]")[0];
		String[] split = temp.replace("[", "").split(", ");

		if (ConfigReader.getValue("browserstack").equalsIgnoreCase("true")) {

			JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getInstance().getMobileDriver();
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""
					+ arg0.getMethod().getMethodName()+" }}");
		}else {
			
		}
		//mobilescreenRecord.startRecording();
		MobileActions mobileActions = new MobileActions();
		mobileActions.sleep(2000);

		By allow = By.id("com.android.permissioncontroller:id/permission_allow_button");
		if (mobileActions.isElmPresent(allow)) {
			mobileActions.click(allow, "Click on allow");
		}

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("Test Success: " + iTestResult.getMethod().getMethodName());

		try {

			ReportManager.logScreenshotInfo();
			String description = iTestResult.getMethod().getDescription();
			String temp = description.split("]")[0];
			String[] split = temp.replace("[", "").split(", ");
			for (String s : split) {
				String testId = s;

			//	TestCaseResult.addResults(testId, Platform, "PASS");
			}
		} catch (Exception e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ConfigReader.getValue("browserstack").equalsIgnoreCase("true")) {
			JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getInstance().getMobileDriver();
			jse.executeScript(
					"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"\"}}");
		}else {
		}
		ReportManager.logPassMobile("Test case passed");

		Test model = ReportManager.getCurrentTest().getModel();
		String oldName = model.getName();
		
		ReportManager.endCurrentTestMobile();
		initDriver.tearDownMobileDriver();
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		try {
			String description = iTestResult.getMethod().getDescription();
			String temp = description.split("]")[0];
			String[] split = temp.replace("[", "").split(", ");
			for (String s : split) {
				String testId = s;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ConfigReader.getValue("browserstack").equalsIgnoreCase("true")) {
			JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getInstance().getMobileDriver();
			String errorMessage = iTestResult.getThrowable().getMessage().substring(0, 150);

			jse.executeScript(
					"browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \""
							+ errorMessage + "\"}}");
		}else {
		}
		System.out.println("Test Fail: " + iTestResult.getMethod().getMethodName());
		ReportManager.logFailMobile("Test case Fail");
		ReportManager.logFailMobile(iTestResult.getThrowable().getMessage());
		try {
			ReportManager.logScreenshotInfo();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Test model = ReportManager.getCurrentTest().getModel();
		String oldName = model.getName();
		model.setName(oldName);
		
		ReportManager.endCurrentTestMobile();
		initDriver.tearDownMobileDriver();
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
// TODO Auto-generated method stub

	}


	@Override
	public void onStart(ITestContext arg0) {
		Platform = arg0.getCurrentXmlTest().getParameter(KEY);
		Udid = arg0.getCurrentXmlTest().getParameter(KEY1);
		SystemPort = arg0.getCurrentXmlTest().getParameter(KEY2);
		DeviceName = arg0.getCurrentXmlTest().getParameter(KEY3);
		DeviceVersion = arg0.getCurrentXmlTest().getParameter(KEY4);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
// TODO Auto-generated method stub

	}

}