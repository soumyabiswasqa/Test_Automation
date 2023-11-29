package com.self.TestAutomation.WebUtil;

import java.io.*;
import java.util.*;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.*;
import org.apache.commons.io.FileUtils;
import org.openqa.*;
import org.w3c.*;
import org.w3c.dom.Document;
import org.xml.*;
import org.xml.sax.SAXException;
import com.aventstack.*;
import com.fasterxml.*;
import com.google.gson.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;



public class Base extends WebDriverUtil
{
	public String extentFilelocation;
	public String extentFileName;
	public ExtentReports extent;
	public ExtentTest logger;
	public Document xmlDoc;
	public String logFile;
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
			FileInputStream inputproperties = new FileInputStream(System.getProperty("user.dir") + "input.properties");
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
		File file = new File(filePath);
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		xmlDoc = dB.parse(file);
		xmlDoc.getDocumentElement().normalize();
	}
	
	public void StartextentReport(String msName)
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
	
	public void EndextentReport(String msName)
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
	
	public void testReadyness(String testName)
	{
		try
		{
			FileUtils.copyFile(srcFile, destFile);
		}catch(Exception e)
		{
			System.err.println("Unable to report Status as FAIL. ERROR --> "+e);
		}
	}
}
