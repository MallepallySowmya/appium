package com.qm.android.utils;



import com.qm.utilities.ConfigReader;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
public class AppiumServerJava {

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;

    public void startServer() {
        //Set Capabilities
        cap = new DesiredCapabilities();
//        cap.setCapability("noReset", "false");

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(ConfigReader.getAppiumProp("serverIP"));
        builder.usingPort(Integer.parseInt(ConfigReader.getAppiumProp("port")));
  //     builder.usingDriverExecutable(new File("C:/Program Files/nodejs/node"))
       builder.withAppiumJS(new File("C:/Users/Mallepally-Sowmya/AppData/Roaming/npm/node_modules/appium/lib/main.js"));
       builder.withCapabilities(cap);
//        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

    public void stopServer() {
        try {
            service.stop();
        } catch (Exception e) {
            System.err.println("Something went wrong when stopping server: " + e.getMessage());
        }
    }

    public boolean isServerRunning() {
        try {
            URL serverUrl = new URL(ConfigReader.getAppiumProp("serverIP") + "/wd/hub");
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void main(String[] args) {
        AppiumServerJava appiumServerJava = new AppiumServerJava();
        appiumServerJava.isServerRunning();
    }
}
