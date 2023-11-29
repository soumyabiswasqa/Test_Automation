package com.self.TestAutomation.WebUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtil extends Base
{
	public void RunFrameworkCore(String filePath) throws ParserConfigurationException, SAXException, IOException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException, SecurityException, ClassNotFoundException
	{
		File file = new File(filePath);
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		// An instance builder to parse the specified XML file
		DocumentBuilder dB = dBF.newDocumentBuilder();
		Document doc = dB.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nodelist = doc.getElementsByTagName("TestCase");
		for(int tCI = 0;tCI<nodelist.getLength();tCI++)
		{
			Node testCaseNode = nodelist.item(tCI);
			Element testCaseElement = (Element) testCaseNode;
			String testCaseName = testCaseElement.getElementsByTagName("TestCaseName").item(0).getTextContent().trim();
			
		}
	}
	
}
