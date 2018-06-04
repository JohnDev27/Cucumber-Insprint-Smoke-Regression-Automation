package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;






public class RadioInputScriptless {


	WebDriver driver;
	
	public RadioInputScriptless (WebDriver driver) {
		this.driver = driver;
	}
	
	ObjectMethods objectMethods = new ObjectMethods();
	
	private static final Logger LOG = LogManager.getLogger(RadioInputScriptless.class);
	
	 public RadioInputScriptless clickRadioInputWhereLabelAndChoice(String labelName, String value)
	    {

	        boolean isFlagged = false;

	        while (!isFlagged)
	        {
	            try
	            {

	            	 By radioInputele = By.xpath("//label[text()='" + labelName
	                         + "']/parent :: td/following-sibling:: td//label[text()='" + value
	                         + "']/preceding-sibling :: input [@role='radio'][1]");
	            	objectMethods.findObject(driver, radioInputele, 30);          	            
	                WebElement radioInput = driver.findElement(radioInputele);
	                radioInput.click();
	                
	               
	                break;
	            }
	            catch (StaleElementReferenceException e)
	            {
	                LOG.info("Into Stale");
	                continue;
	            }
	        }

	        return this;

	    }

	    public RadioInputScriptless clickRadioInputWhereChoice(String value)
	    {

	        boolean isFlagged = false;
	        while (!isFlagged)
	        {
	            try
	            {
	                By radioInputele = By.xpath("//label[text()='" + value + "']/preceding :: input[@role='radio'][1]");

	                objectMethods.findObject(driver, radioInputele, 30);
	                WebElement radioInput = driver.findElement(radioInputele);
	                radioInput.click();
	                break;
	            }
	            catch (StaleElementReferenceException e)
	            {
	                LOG.info("Into Stale");
	            }
	        }

	        return this;

	    }
}
