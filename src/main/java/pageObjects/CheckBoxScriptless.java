package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;





public class CheckBoxScriptless{
	
	WebDriver driver;
	
	public CheckBoxScriptless (WebDriver driver) {
		this.driver = driver;
	}
	
	ObjectMethods objectMethods = new ObjectMethods();
	
	private static final Logger LOG = LogManager.getLogger(ButtonScriptless.class);
    public CheckBoxScriptless checkCheckBoxWhereLabelFirstOccurence(String labelName)
    {

        checkCheckboxInputWhereLabelNthOccurence(labelName, "1");
        return this;

    }

    public CheckBoxScriptless checkCheckboxInputWhereLabelNthOccurence(String labelName, String occurnence)
    {

        boolean isFlagged = false;
        while (!isFlagged)
        {
            try
            {
                By checkboxInput = By.xpath("(//label[text()='" + labelName
                        + "']/parent :: td/parent :: tr/td[2]//input[@role='checkbox']|//label[text()='" + labelName
                        + "']/preceding-sibling :: input|//*[text()='" + labelName
                        + "']/parent :: */preceding-sibling :: table//input[@role='checkbox']|//*[text()='" + labelName
                        + "']/preceding-sibling :: table//input[@role='checkbox'])[" + occurnence + "]");

                objectMethods.findObject(driver, checkboxInput, 30);
                WebElement checkboxInputele = driver.findElement(checkboxInput);
                checkboxInputele.click();
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

    public CheckBoxScriptless checkCheckboxInputWhereObjectTagId(String labelName, String domid)
    {

        boolean isFlagged = false;
        while (!isFlagged)
        {
            try
            {
                By checkboxInput = By.xpath("//input[contains(@id,'" + domid + "')]");

                objectMethods.findObject(driver, checkboxInput, 30);
                WebElement checkboxInputele = driver.findElement(checkboxInput);
                checkboxInputele.click();
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
