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

public class TestCaseTwo

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
	public TestCaseTwo() {

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




	@Given("^User search for Lenovo Laptop$")
	public void user_searched_for_Lenovo_Laptop() throws Throwable {
		System.out.println("User searched for Lenovo Laptop");
	}

	@When("^Add the first laptop that appears in the search result to the basket$")
	public void add_the_first_laptop_that_appears_in_the_search_result_to_the_basket() throws Throwable {
		System.out.println("First search result added to bag");
	}

	
	@Given("^User navigate for Lenovo Laptop$")
	public void user_navigate_for_Lenovo_Laptop() throws Throwable {
		System.out.println("User navigated for Lenovo Laptop");
	}

	@When("^Add the laptop to the basket$")
	public void add_the_laptop_to_the_basket() throws Throwable {
		System.out.println("Laptop added to the basket");
	}

	@Then("^User basket should display with added item$")
	public void user_basket_should_display_with_item() throws Throwable {
		System.out.println("Bag is now contains the added product");
	}	


	
}
