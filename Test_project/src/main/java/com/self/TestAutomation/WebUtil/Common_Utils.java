package com.self.TestAutomation.WebUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common_Utils extends Base
{
	public String folderName;
	public String screenshotFolderPath;
	public String dataFolderpath;
	
	public void createResultFolderStructure()
	{
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dTF.format(now));
		String output = dTF.format(now).toString().replace(":", "-"); // Replacing colons for compatibility with HFS+ file system
		File folder = new File ("D:\\Soumyadeep Biswas - official\\Automation Codes\\JAVA\\TestResult" + output);
		if(!folder.exists())
		{
			try
			{
				folder.mkdir();	
			}catch(Exception e)
			{
				System.err.println("Unable to create folder. ERROR --> "+e);
			}
		}
		folderName = "D:\\Soumyadeep Biswas - official\\Automation Codes\\JAVA\\TestResult" + output;
		folder = new File(folderName + "\\Screen shots");
		folder.mkdir();
		folder = new File(folderName + "\\Data");
		folder.mkdir();
		screenshotFolderPath = folderName + "\\Screen shots";
		dataFolderpath = folderName + "\\Data";
		try
		{
			File file = new File(folderName + "\\Run Log.txt");
			if(file.createNewFile())
			{
				System.out.println("File Created : " + file.getName());
			}
			else
			{
				System.out.println("File Already Exist : " + file.getName());
			}
		}catch(Exception e)
		{
			System.err.println("Error occured while creating log file. ERROR --> "+e);
		}
		logFile = folderName+"\\Run log.txt";
	}
	
	public void writeToFile(String fileLocation, String fileContent)
	{
		try
		{
			File statText = new File(fileLocation);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(fileContent);
			w.close();			
		}catch(Exception e)
		{
			System.err.println("Unable to Write in the File. ERROR --> "+e);
		}
	}
	
	public void writeToFile(File file, String fileContent)
	{
		try
		{
			FileOutputStream is = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			w.write(fileContent);
			w.close();			
		}catch(Exception e)
		{
			System.err.println("Unable to Write in the File. ERROR --> "+e);
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
	
	
}
