package functionalLibrary;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.iface.Submit.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.support.SoapUIException;

import net.sf.json.JSONException;

public class SoapProjects {


	
	@SuppressWarnings("unchecked")
	public static void setProjectProperties(String Project, String jsonInString ) throws JSONException, XmlException, IOException, SoapUIException {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		map = mapper.readValue(jsonInString, HashMap.class);
		
		// Set the SOAP UI Project Information
		String path = "src/main/resources/public/soapProjects/" + Project + ".xml";
		WsdlProject project = new WsdlProject(path);
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			project.setPropertyValue(key, value);
			System.out.println(key);
			System.out.println(value);
		}

	}
	
	public static void runTestwithKey(String Project, String TestSuite, String TestCase, String outPutKey, String outputValue) throws XmlException, IOException, SoapUIException {

		// Set the SOAP UI Project Information
		String path = "src/main/resources/public/soapProjects/" + Project + ".xml";
		WsdlProject project = new WsdlProject(path);
		WsdlTestSuite wsdlTestSuite = project.getTestSuiteByName(TestSuite);
		WsdlTestCase wsdlTestCase = wsdlTestSuite.getTestCaseByName(TestCase);

		// Execute SOAP UI test
		TestRunner tcRunner = wsdlTestCase.run(new PropertiesMap(), false);

		// Store Output to a Map
		DataStorage.put(outPutKey, project.getPropertyValue(outputValue));

		System.out.println(project.getPropertyValue(outputValue));
		System.out.println(tcRunner.getStatus().toString());

		// Validate the SUCCESS of SOAP UI Execution
		Assert.assertEquals(Status.FINISHED.toString(), tcRunner.getStatus().toString());
	}

	

	public static void runTest(String Project, String TestSuite, String TestCase, String outPutKey, String outputValue,
			String env, String esbEnv) throws XmlException, IOException, SoapUIException {

		// Set the SOAP UI Project Information
		String path = "src/main/resources/public/soapProjects/" + Project + ".xml";
		WsdlProject project = new WsdlProject(path);
		WsdlTestSuite wsdlTestSuite = project.getTestSuiteByName(TestSuite);
		WsdlTestCase wsdlTestCase = wsdlTestSuite.getTestCaseByName(TestCase);

		// Set the Environment details
		project.setPropertyValue("Environment", env);
		project.setPropertyValue("esbEnvironment", esbEnv);
		

		// Execute SOAP UI test
		TestRunner tcRunner = wsdlTestCase.run(new PropertiesMap(), false);

		// Store Output to a Map
		DataStorage.put(outPutKey, project.getPropertyValue(outputValue));

		System.out.println(project.getPropertyValue(outputValue));
		System.out.println(tcRunner.getStatus().toString());

		// Validate the SUCCESS of SOAP UI Execution
		Assert.assertEquals(Status.FINISHED.toString(), tcRunner.getStatus().toString());
	}
	
	public static void runTestToGetMultipleValues(String Project, String TestSuite, String TestCase, String outPutKey1, String outputValue1, String outPutKey2, String outputValue2, String outPutKey3, String outputValue3,
			String env, String esbEnv) throws XmlException, IOException, SoapUIException {

		// Set the SOAP UI Project Information
		String path = "src/main/resources/public/soapProjects/" + Project + ".xml";
		WsdlProject project = new WsdlProject(path);
		WsdlTestSuite wsdlTestSuite = project.getTestSuiteByName(TestSuite);
		WsdlTestCase wsdlTestCase = wsdlTestSuite.getTestCaseByName(TestCase);

		// Set the Environment details
		project.setPropertyValue("Environment", env);
		project.setPropertyValue("esbEnvironment", esbEnv);
		project.setPropertyValue("eServiceEnvironment", env);

		// Execute SOAP UI test
		TestRunner tcRunner = wsdlTestCase.run(new PropertiesMap(), false);

		// Store Output to a Map
		DataStorage.put(outPutKey1, project.getPropertyValue(outputValue1));
		DataStorage.put(outPutKey2, project.getPropertyValue(outputValue2));
		DataStorage.put(outPutKey3, project.getPropertyValue(outputValue3));

		System.out.println(project.getPropertyValue(outputValue1));
		System.out.println(tcRunner.getStatus().toString());

		// Validate the SUCCESS of SOAP UI Execution
		Assert.assertEquals(Status.FINISHED.toString(), tcRunner.getStatus().toString());
	}

}
