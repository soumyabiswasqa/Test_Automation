package com.self.TestAutomation.WebUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*
 * WebDriverUtil - driver Method of this class initializes the WebDriver and launches the application for the selected browser type
 * 
 * Created By - SOUMYADEEP BISWAS
 *  
 * */

public class WebDriverUtil 
{
	 public static WebDriver driver; 
	 public String executionType; 
	 public String browserType;
	 
	 public void driver() throws Throwable 
	 {
		 String os =System.getProperty("os.name").toLowerCase();	  
		 if (os.contains("win")) 
		 {
			Properties pro = new Properties();
			FileInputStream inputProperties = new FileInputStream(System.getProperty("user.dir") + "//input.properties");
			pro.load(inputProperties);
			browserType = pro.getProperty("BrowserType");
			if (browserType.equalsIgnoreCase("Chrome")) 
			{
				File chromedriver = new File(System.getProperty("user.dir") + "//DriverWindow//chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized", "--disable-infobars", "--no-sandbox");
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			}
			else if(browserType.equalsIgnoreCase("Edge"))
			{
				
			}
		}
	}	 
}
