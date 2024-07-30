package com.self.TestAutomation.StepDefination;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import com.self.TestAutomation.Pagefactory.Login_Page;
import com.self.TestAutomation.WebUtil.Base;

/* 
 * #####################################################################################
 * 
 * This the StepDefination class. In this class all the test steps are defined.
 * 
 * Created By : SOUMYADEEP BISWAS
 * 
 * #####################################################################################
 * 
 * */

public class StepDefination extends Base
{
	public String userLoginToApplication() throws Throwable
	{
		try
		{
			String uName = readProps("UserName");
			String pWD = readProps("Password");			
			Login_Page loginPage = PageFactory.initElements(driver, Login_Page.class);			
			elementOperations(loginPage.userName,"sendkeys",uName);				
			elementOperations(loginPage.password,"sendkeys",pWD);			
			elementOperations(loginPage.logInBttn,"click","Login Button Clicked");			
		}
		catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
		return "pass";
	}
	
	public String applicationFilter() throws Throwable
	{
		try
		{
			Login_Page loginPage = PageFactory.initElements(driver, Login_Page.class);
			Map<Object,Object> dataMap = new HashMap<Object,Object>();
			dataMap = getTestData("TestData\\TestData.XML","Validate user is able to Login to the application and filter and add items");
			elementOperations(loginPage.selectPageFilter,"select","selectByValue",dataMap.get("filterLowtoHigh").toString());						
			elementOperations(loginPage.itemBikeLight,"click","Login Button Clicked");			
		}
		catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
		return "pass";
	}
	
	public String addItems() throws Throwable
	{
		try
		{
			Login_Page loginPage = PageFactory.initElements(driver, Login_Page.class);						
			elementOperations(loginPage.itemBikeLight,"click","Bike Loght Clicked");	
			elementOperations(loginPage.itemBackpack,"click","Backpack Clicked");
			elementOperations(loginPage.itemFleeceJacket,"click","Fleece Jacket");
			elementOperations(loginPage.itemTshirt,"click","T-Shirt Clicked");
		}
		catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
		return "pass";
	}
	
	public String shoppingCart() throws Throwable
	{
		try
		{
			Login_Page loginPage = PageFactory.initElements(driver, Login_Page.class);						
			elementOperations(loginPage.iconShoppingCart,"click","Shopping Cart Clicked");	
			
		}
		catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
		return "pass";
	}
}
