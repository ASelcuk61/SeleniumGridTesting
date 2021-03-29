package com.saucedemo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
public class BasePage {
    WebDriver driver;
    Properties properties;
    public WebDriver initDriver(String browser){
        initProperties();
        String remoteWebDriver = properties.getProperty("remote");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (remoteWebDriver.equals("yes")) {
            URL url = null;
            try {
                url = new URL(properties.getProperty("huburl"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if(browser.equals("chrome")){
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("platform", "ANY");
                driver = new RemoteWebDriver(url, capabilities);
            }else if (browser.equals("firefox")) {
                capabilities.setCapability("browserName", "firefox");
                capabilities.setCapability("platform", "ANY");
                driver = new RemoteWebDriver(url, capabilities);
            }else{
                System.out.println("Browser doesn't exist");
            }
        }else{
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else{
                System.out.println("Browser doesn't exist");
            }
        }
        return driver;
    }
    public Properties initProperties(){
        properties = new Properties();
        String path = "./src/main/java/com/saucedemo/configuration/configuration.properties";
        try {
            FileInputStream file = new FileInputStream(path);
            properties.load(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
