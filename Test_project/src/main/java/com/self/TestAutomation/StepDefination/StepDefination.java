package com.self.TestAutomation.StepDefination;

import org.openqa.selenium.support.PageFactory;

import com.self.TestAutomation.Pagefactory.Login_Page;
import com.self.TestAutomation.WebUtil.Base;

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
			elementOperations(loginPage.selectPageFilter,"select","selectByValue","lohi");						
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