package com.self.TestAutomation.WebUtil;

import java.io.*;
import java.time.Duration;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;


public class Base extends WebDriverUtil
{
	public String executionType;
	public String browserType;
	public String extentFilelocation;
	public String extentFileName;
	public static ExtentReports extent;
	public ExtentTest logger;
	public Document xmlDoc;
	public String logFile;
	public String testStepStatus;
	public String activeTestStepName;
	public ExtentSparkReporter htmlReporter;
	String extentFileLocation;
	File srcFile = new File ("TestData\\TestData.XML");
	File destFile = new File ("TestData\\TestData_OP.XML");
	
	public String readProps(String input)
	{
		String text = null;
		try
		{
			Properties properties = new Properties();
			FileInputStream inputproperties = new FileInputStream(System.getProperty("user.dir") + "\\"+"input.properties");
			properties.load(inputproperties);
			text = properties.getProperty(input);
		}catch(Exception e)
		{
			System.err.println("Unable to read properties files. ERROR --> "+e);
		}
		return text;
	}
	
	public void writeToLogFile(String fileContent)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(logFile, true));
			out.write(fileContent + "\n");
			out.close();
			
		}catch(Exception e)
		{
			System.err.println("Unable to write in LOG file. ERROR --> "+e);
		}
	}
	
	public void loadXML(String filePath) throws ParserConfigurationException, SAXException, IOException
	{
		try 
		{
			File file = new File(filePath);
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			xmlDoc = dB.parse(file);
			xmlDoc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) 
		{
			System.err.println("Unable to Load XML file. ERROR --> "+e);
		} 
	}
	
	public void startExtentReport(String msName)
	{
		try
		{
			String fileName = "target\\extents\\ExtentReport_" + msName +".html";
			extentFilelocation = fileName;
			extentFileName = "ExtentReport_" + msName +".html";
			// Delete the previous Extent Report if exists
			File file = new File(fileName);
			if(file.exists())
			{
				file.delete();
			}
			// Create the Extent HTML Reports
			htmlReporter = new ExtentSparkReporter(fileName);
			htmlReporter.loadXMLConfig("extent-config.xml");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			
		}catch(Exception e)
		{
			System.err.println("Unable to create Extent Report. ERROR --> "+e);
		}
	}
	
	public void endDriver()
	{
		try
		{
			driver.quit();		
		}catch(Exception e)
		{
			System.err.println("Unable to end Extent Report. ERROR --> "+e);
		}
	}
	
	public void endExtentReport()
	{
		try
		{
			extent.flush();			
		}catch(Exception e)
		{
			System.err.println("Unable to end Extent Report. ERROR --> "+e);
		}
	}
	
	public void CopyReportToResultFolder() throws IOException
	{
		try
		{
			Common_Utils objCom = new Common_Utils();
			File srcFile = new File(extentFilelocation);
			File destFile = new File(objCom.folderName + "\\" + extentFileName);
			FileUtils.copyFile(srcFile, destFile);			
		}catch(Exception e)
		{
			System.err.println("Unable to Copy Extent Report to Result Folder. ERROR --> "+e);
		}
	}
	
	public void ReportStepStatusPass(String stepName) 
	{
		try
		{
			logger.log(Status.PASS, stepName);	
		}catch(Exception e)
		{
			System.err.println("Unable to report Status as PASS. ERROR --> "+e);
		}
	}
	
	public void ReportStepStatusFail(String stepName) 
	{
		try
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(stepName, ExtentColor.RED));	
		}catch(Exception e)
		{
			System.err.println("Unable to report Status as FAIL. ERROR --> "+e);
		}
	}
	
	public void testReadyness() throws Throwable
	{
		try
		{
			FileUtils.copyFile(srcFile, destFile);
			Common_Utils objCom = new Common_Utils();
			objCom.userNavigateToApplication();
			
		}catch(Exception e)
		{
			System.err.println("Unable to report Status as FAIL. ERROR --> "+e);
		}
	}
	
	public void initializeDriver() throws Throwable
	{
		try
		{
			String executionType = readProps("ExecutionType");
			if(executionType.equalsIgnoreCase(executionType))
			{
				driver();
			}
		}catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
	}
	
	public void userNavigateToApplication() throws Throwable
	{
		initializeDriver();
		try
		{
			String URL = readProps("URL");
			driver.get(URL);
			Thread.sleep(5000);
			
			
		}catch(Exception e)
		{
			System.err.println("Unable to Initialize Driver. ERROR --> "+e);
		}
	}	
	
	public void elementOperations(WebElement element, String operation, String value) throws Throwable
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement ele = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			if (operation.equalsIgnoreCase("keyaction")) 
			{
				if (value.equalsIgnoreCase("Enter"))
					ele.sendKeys(Keys.ENTER);
				if (value.equalsIgnoreCase("Tab"))
					ele.sendKeys(Keys.TAB);
				if (value.equalsIgnoreCase("Down"))
					ele.sendKeys(Keys.ARROW_DOWN);
				if (value.equalsIgnoreCase("Up"))
					ele.sendKeys(Keys.ARROW_UP);
				if (value.equalsIgnoreCase("Left"))
					ele.sendKeys(Keys.ARROW_LEFT);
				if (value.equalsIgnoreCase("Right"))
					ele.sendKeys(Keys.ARROW_RIGHT);
			} 
			else if (operation.equalsIgnoreCase("click"))
				ele.click();
			else if (operation.equalsIgnoreCase("clear"))
				ele.clear();
			else if (operation.equalsIgnoreCase("sendkeys"))
				ele.sendKeys(value);			
		}
		catch(StaleElementReferenceException e)
		{
			System.err.println("Unable to perform Element Operations. ERROR --> "+e);
		}
	}
	
	public void elementOperations(WebElement element, String operation, String selectType, String value) throws Throwable
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement ele = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));		
			if (operation.equalsIgnoreCase("select")) 
			{
				Select objSet = new Select(element);
				if(selectType == "selectByValue")
					objSet.selectByValue(value);
				else if(selectType == "selectByVisibleText")
					objSet.selectByVisibleText(value);;
					
			} 
			else if (operation.equalsIgnoreCase("click"))
				ele.click();
			else if (operation.equalsIgnoreCase("clear"))
				ele.clear();
			else if (operation.equalsIgnoreCase("sendkeys"))
				ele.sendKeys(value);			
		}
		catch(StaleElementReferenceException e)
		{
			System.err.println("Unable to perform Element Operations. ERROR --> "+e);
		}
	}
	
	public Map<Object,Object> getTestData(String testDataFilePath, String testCaseName) throws ParserConfigurationException, SAXException, IOException
	{
		Map<Object,Object> returnMap = new HashMap<Object,Object>();
		try
		{
			if(xmlDoc == null)
				loadXML(testDataFilePath);
			NodeList tC = xmlDoc.getElementsByTagName("TestCase");
			for(int itr =0;itr<tC.getLength();itr++)
			{
				if(tC.item(itr).getAttributes().getNamedItem("name").getTextContent().toLowerCase()
						.equals(testCaseName.trim().toLowerCase()))
				{
					Node tcNode = tC.item(itr);
					Element tcElement = (Element) tcNode;
					NodeList Fields = tcElement.getElementsByTagName("Field");
					for(int flditr =0;flditr<Fields.getLength();flditr++)
					{
						Node fieldNode = Fields.item(flditr);
						Element fieldNodeElement = (Element) fieldNode;
						returnMap.put(fieldNodeElement.getAttributes().getNamedItem("name").getTextContent(),
								fieldNodeElement.getTextContent());								
					}
					
				}
			}
			
			
		}catch(Exception e)
		{
			System.err.println("Unable to Retrive Test Data. ERROR --> "+e);
		}
		
		return returnMap;
	}
}
