package com.self.TestAutomation.WebUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * ######################################################################################################
 * 
 * 
 * XMLUtil - This is the actual framework core running mechanism. 
 * 
 * Framework Core - The Framework core determines the flow of the validation in this framework as 
 * mentioned in the test case XML in a defined format. The methods can have 
 * multiple parameter or no parameter. The framework calls the method with parameter treating 
 * the parameter as array of objects. The methods defined in the step definition
 * should have casting of parameter as required parsing the object array passed by the framework.
 * 
 * Created By - SOUMYADEEP BISWAS
 *  
 * #######################################################################################################
 * */

public class XMLUtil extends Base
{
	@SuppressWarnings("deprecation")
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
			// Create test for Extent 
			Node testCaseNode = nodelist.item(tCI);
			Element testCaseElement = (Element) testCaseNode;
			String testCaseName = testCaseElement.getElementsByTagName("TestCaseName").item(0).getTextContent().trim();
			logger = extent.createTest(testCaseName);
			// Get Test Steps for each Test Case
			NodeList testSteps = testCaseElement.getElementsByTagName("TestStep");
			for(int itr = 0;itr<testSteps.getLength();itr++)
			{
				Node node = testSteps.item(itr);
				//System.out.println("Node is ->>"+nodelist.item(itr).toString()+"For itr - "+itr);
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					testStepStatus = "fail";
					Element eElement = (Element) node;
					ArrayList<Object> parameterList = new ArrayList<Object>();
					String testStepName = eElement.getElementsByTagName("StepName").item(0).getTextContent();
					String packageName = eElement.getElementsByTagName("Package").item(0).getTextContent();
					String className = eElement.getElementsByTagName("ClassName").item(0).getTextContent();
					String methodName = eElement.getElementsByTagName("Method").item(0).getTextContent();
					NodeList parameterNodes = eElement.getElementsByTagName("Parameter");
					activeTestStepName = testStepName;
					for(int paramitr =0; paramitr < parameterNodes.getLength();paramitr++)
					{
						parameterList.add(parameterNodes.item(paramitr).getTextContent());
					}
					Class<?> objClass = Class.forName(packageName + "." + className);
					
					if(parameterList.size() > 0)
					{
						// For Methods with Parameters
						try
						{
							Object classObj = objClass.newInstance();
							Method method = objClass.getDeclaredMethod(methodName, ArrayList.class);
							testStepStatus = (String) method.invoke(classObj, parameterList);
							
						}catch(Exception e)
						{
							System.err.println("Error in calling function. ERROR --> "+"Error Method - "+methodName+" for Error ->  "+e);
						}
					}
					else
					{
						// For Methods Without Parameters
						try
						{
							Method method = objClass.getDeclaredMethod(methodName);
							Object obj = objClass.newInstance();
							testStepStatus = (String) method.invoke(obj);							
						}catch(Exception e)
						{
							System.err.println("Error in calling function. ERROR --> "+"Error Method - "+methodName+" for Error ->  "+e);
						}
					}
					
					if(testStepStatus.toLowerCase().contains("fail"))
					{
						ReportStepStatusFail(testStepName);						
						break;
					}
					else
					{
						ReportStepStatusPass(testStepName);
					}
				}
			}
		}
	}
	
}
