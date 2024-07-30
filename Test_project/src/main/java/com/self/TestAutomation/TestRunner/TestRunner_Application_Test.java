package com.self.TestAutomation.TestRunner;

import org.testng.annotations.*;
import com.self.TestAutomation.WebUtil.Base;
import com.self.TestAutomation.WebUtil.XMLUtil;

/* #####################################################################################
 * 
 * This the TestRunner class. This is the starting point of the automated test run.
 * 
 * Created By : SOUMYADEEP BISWAS
 * 
 * #####################################################################################
 * */

public class TestRunner_Application_Test extends Base
{
	public String testCaseXML;
	
	@BeforeSuite
	public void setSuitePreconditions() throws Throwable
	{
		try 
		{
			startExtentReport("Validation_Report");
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to Start Extent Report. Error is --> "+e);
		}
	}	
	
	@BeforeMethod
	public void setTestPreconditions() throws Throwable
	{
		try 
		{
			testReadyness();
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to make the browser and driver ready. Error is --> "+e);
		}		
	}
	
	@Test
	public void verifyTestApplication()
	{
		try
		{
			XMLUtil xmlUtil = new  XMLUtil();
			testCaseXML = System.getProperty("user.dir")+"\\"+"TestCases\\TestLogin\\testLogin.XML";
			xmlUtil.RunFrameworkCore(testCaseXML);
		}
		catch(Exception e)
		{
			System.out.println("Error in running test. Error is -> "+e);
		}
	}
	
	@Test
	public void verifyApplication()
	{
		try
		{
			XMLUtil xmlUtil = new  XMLUtil();
			testCaseXML = System.getProperty("user.dir")+"\\"+"TestCases\\TestLogin\\testValidation.XML";
			xmlUtil.RunFrameworkCore(testCaseXML);
		}
		catch(Exception e)
		{
			System.out.println("Error in running test. Error is -> "+e);
		}
	}
	
	@AfterMethod
	public void endTest() throws Throwable
	{
		try 
		{
			endDriver();
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to Quit Driver. Error is --> "+e);
		}
	}
	
	@AfterSuite
	public void endReport() throws Throwable
	{
		try 
		{
			endExtentReport();
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to End Extent Report. Error is --> "+e);
		}
	}
}
