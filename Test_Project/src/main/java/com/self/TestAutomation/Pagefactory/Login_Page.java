package com.self.TestAutomation.Pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.self.TestAutomation.WebUtil.Base;

/* 
 * #####################################################################################
 * 
 * This the PageFactory class. In this class all the WebElements are defined are defined.
 * 
 * Created By : SOUMYADEEP BISWAS
 * 
 * #####################################################################################
 * 
 * */

public class Login_Page extends Base
{
	@FindBy(xpath="//input[contains(@id,'user-name')]")
	public WebElement userName;
	
	@FindBy(xpath="//input[contains(@id,'user-name')]//parent::div//following-sibling::div/input")
	public WebElement password;
	
	@FindBy(xpath="//div[@data-test='secondary-header']//select[@data-test='product-sort-container']")
	public WebElement selectPageFilter;
	
	@FindBy(xpath="//button[contains(@id,'bike')]")
	public WebElement itemBikeLight;
	
	@FindBy(xpath="//button[contains(@id,'bolt-t-shirt')]")
	public WebElement itemTshirt;
	
	@FindBy(xpath="//input[contains(@id,'login-button')]")
	public WebElement logInBttn;
	
	@FindBy(xpath="//button[contains(@id,'backpack')]")
	public WebElement itemBackpack;
	
	@FindBy(xpath="//button[contains(@id,'fleece-jacket')]")
	public WebElement itemFleeceJacket;
	
	@FindBy(xpath="//div[@id='shopping_cart_container']")
	public WebElement iconShoppingCart;
	
	
}
