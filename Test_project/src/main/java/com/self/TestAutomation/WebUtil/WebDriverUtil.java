package com.self.TestAutomation.WebUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverUtil 
{
	public WebDriver driver;
	public String executionType;
	public String browserType;
	
	public void driver() throws Throwable
	{
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("Operating System is: "+os);
		
		if(os.contains("win"))
		{
			Properties pro = new Properties();
			FileInputStream inputProperties = new FileInputStream(System.getProperty("user.dir") + "//input.properties");
			pro.load(inputProperties);
			browserType = pro.getProperty("BrowserType");
			System.out.println("Browser Type --> " + browserType);
			if(browserType.equalsIgnoreCase("Chrome"))
			{
				File chromedriver = new File(System.getProperty("user.dir") + "//DriverWindow//chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized","--disable-infobars","--no-sandbox");
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
			}
		}
	}
}
