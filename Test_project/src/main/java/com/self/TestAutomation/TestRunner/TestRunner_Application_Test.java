package com.self.TestAutomation.TestRunner;


import org.testng.annotations.*;
import com.self.TestAutomation.WebUtil.Base;
import com.self.TestAutomation.WebUtil.XMLUtil;

public class TestRunner_Application_Test extends Base
{
	public String testCaseXML;
	
	@BeforeSuite
	public void setSuitePreconditions() throws Throwable
	{
		StartextentReport("Validation_Report");
	}	
	@BeforeMethod
	public void setTestPreconditions() throws Throwable
	{
		testReadyness();
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
		EndDriver();
	}
	@AfterSuite
	public void endReport() throws Throwable
	{
		EndextentReport();
	}
}
