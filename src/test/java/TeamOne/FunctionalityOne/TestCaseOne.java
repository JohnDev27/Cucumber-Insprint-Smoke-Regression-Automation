package TeamOne.FunctionalityOne;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import functionalLibrary.DataStorage;
//import functionalLibrary.DataStorage;
import functionalLibrary.ObjectMethods;
import functionalLibrary.WebDriverFactory;
import pageObjects.ButtonScriptless;
import pageObjects.LabelScriptless;
import pageObjects.LinkScriptless;
import pageObjects.RadioInputScriptless;
import pageObjects.RangeInputScriptless;
import pageObjects.SideMenuScriptless;
import pageObjects.TableActionScriptless;
import pageObjects.TextAreaInputScriptless;
import pageObjects.TextInputScriptless;

public class TestCaseOne

{
	/** The log. */
	// driver initiation
	static Logger log = Logger.getLogger(TestCaseOne.class);

	/** The driver. */
	WebDriverFactory webDriverFactory;
	WebDriver driver;

	ButtonScriptless button;
	TextInputScriptless textInput;
	SideMenuScriptless sideMenuScriptless;
	RangeInputScriptless rangeInput;
	TextAreaInputScriptless textArea;

	RadioInputScriptless radioInputScriptless;
	TableActionScriptless tableAction;
	LinkScriptless link;
	LabelScriptless label;

	ObjectMethods objectMethods = new ObjectMethods();
	Properties prop = DataStorage.loadProperties();

	String navigatorEnvironment = prop.getProperty("navigator.environment");
	String esbEnvironment = prop.getProperty("esb.environment");
	String url = prop.getProperty("landing.page.url");

	/** Page objects Initialization */
	public TestCaseOne() {

		driver = WebDriverFactory.getWebDriver(prop.getProperty("webdriver.browserType"));
		button = new ButtonScriptless(driver);
		textInput = new TextInputScriptless(driver);
		sideMenuScriptless = new SideMenuScriptless(driver);
		rangeInput = new RangeInputScriptless(driver);
		textArea = new TextAreaInputScriptless(driver);
		
		radioInputScriptless = new RadioInputScriptless(driver);
		tableAction = new TableActionScriptless(driver);
		link = new LinkScriptless(driver);
		label = new LabelScriptless(driver);

	}


	@Given("^I navigate to the login page$")
	public void i_navigate_to_the_login_page() throws Throwable {
		System.out.println("I am at the LogIn Page");
	}

	@When("^I submit username and password$")
	public void i_submit_username_and_password() throws Throwable {
		System.out.println("I Submit my Username and Password");
	}

	@Then("^I should be logged in$")
	public void i_should_be_logged_in() throws Throwable {
		System.out.println("I am logged on to the website");
	}

	
}
