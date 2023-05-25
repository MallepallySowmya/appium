package com.qm.utilities;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.winium.WiniumDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.remote.MobileCapabilityType;


public class DriverFactory {
	
    private static DriverFactory instance = null;
    ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    ThreadLocal<AndroidDriver> appiumDriver = new ThreadLocal<AndroidDriver>();
    ThreadLocal<WiniumDriver> windowDriver = new ThreadLocal<WiniumDriver>();

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public final void setWebDriver(String browser) throws Exception {

        DesiredCapabilities caps = null;

        switch (browser) {

            case "Chrome":
				/*
				 * caps = DesiredCapabilities.chrome(); ChromeOptions chOptions = new
				 * ChromeOptions(); Map<String, Object> chromePrefs = new HashMap<String,
				 * Object>(); chromePrefs.put("credentials_enable_service", false);
				 * chOptions.setExperimentalOption("prefs", chromePrefs);
				 * chOptions.addArguments("--disable-plugins", "--disable-extensions",
				 * "--disable-popup-blocking"); caps.setCapability(ChromeOptions.CAPABILITY,
				 * chOptions); caps.setCapability("applicationCacheEnabled", false);
				 * WebDriverManager.chromedriver().setup(); webDriver.set(new ChromeDriver());
				 * getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 * getWebDriver().manage().window().maximize();
				 */

                break;

            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver.set(new FirefoxDriver());
                getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                getWebDriver().manage().window().maximize();
                break;

            case "Edge":
                WebDriverManager.edgedriver().setup();
                webDriver.set(new EdgeDriver());
                getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                getWebDriver().manage().window().maximize();
                break;

            case "IE":
                WebDriverManager.iedriver().setup();
                webDriver.set(new InternetExplorerDriver());
                getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                getWebDriver().manage().window().maximize();
                break;

        }

    }

    public final void setMobileDriver(String platform, String udid, String systemPort, String deviceName,
                                      String deviceVersion) throws Exception {
        String dateStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String executionType = System.getProperty("browserstack", ConfigReader.getValue("browserstack"));


        if (executionType.equalsIgnoreCase("false")) {

            String[] platformInfo = platform.split(" ");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            capabilities.setCapability(MobileCapabilityType.UDID, udid);

            // Install app from framework
//            capabilities.setCapability("app",
//                    System.getProperty("user.dir") + "/src/test/resources/MobileApps/Contacts_4.6.26.523229226_Apkpure.apk");

            capabilities.setCapability("appPackage", "com.google.android.contacts");
            capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");

            capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);

                appiumDriver.set(new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities));
           

            getMobileDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {

            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser
                    .parse(new FileReader("src/test/resources/ConfigFiles/parallel.conf.json"));
            JSONArray envs = (JSONArray) config.get("environments");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserstack.idleTimeout", "300");
            capabilities.setCapability("browserstack.geoLocation", "IN");
            capabilities.setCapability("browserstack.gpsLocation", "12.9716,77.5946");
            @SuppressWarnings("unchecked")
            Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceVersion));
            Iterator it = envCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry pair = (Map.Entry) it.next();
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }

            @SuppressWarnings("unchecked")
            Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
            it = commonCapabilities.entrySet().iterator();
            while (it.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry pair = (Map.Entry) it.next();
                if (capabilities.getCapability(pair.getKey().toString()) == null) {
                    capabilities.setCapability(pair.getKey().toString(), pair.getValue());
                }
            }

            String username = System.getenv("BROWSERSTACK_USERNAME");
            if (username == null) {
                username = (String) config.get("username");
            }

            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            if (accessKey == null) {
                accessKey = (String) config.get("access_key");
            }

            String app = System.getenv("BROWSERSTACK_APP_ID");
            if (app != null && !app.isEmpty()) {
                capabilities.setCapability("app", app);
            }
            appiumDriver.set(new AndroidDriver(
                    new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"),
                    capabilities));
            getMobileDriver().setLocation(new Location(12.9716, 77.5946, 920));
        }
    }

    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    public AndroidDriver getMobileDriver() {
        return appiumDriver.get();
    }

    public void removeDriver() {
        appiumDriver.remove();
    }

}