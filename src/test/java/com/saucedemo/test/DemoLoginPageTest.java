package com.saucedemo.test;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DemoLoginPageTest {

    @Test
    public void loginTest(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("platform", "ANY");
        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("https://www.saucedemo.com");
        driver.findElementById("user-name").sendKeys("test123");
        driver.findElementById("password").sendKeys("test123");
        driver.findElementById("login-button").click();
    }


}
