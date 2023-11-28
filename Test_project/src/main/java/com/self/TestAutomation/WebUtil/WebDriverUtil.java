package com.self.TestAutomation.WebUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Properties;
import org.openqa.selenium.*;


public class WebDriverUtil 
{
	public WebDriver driver;
	public String executionType;
	public String browserType;
	
	public void driver() throws Throwable
	{
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("Operating System is: "+os);
		
		
	}
}
