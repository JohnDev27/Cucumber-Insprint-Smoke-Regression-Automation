package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;







public class LinkScriptless {
	
	WebDriver driver;
	
	public LinkScriptless (WebDriver driver) {
		this.driver = driver;
	}	
	
	ObjectMethods objectMethods = new ObjectMethods();
	
	private static final Logger LOG = LogManager.getLogger(LinkScriptless.class);
	
	
	public LinkScriptless clickButtonWhereLabel(String labelName)
    {

		clickLinkWhereLabelNthOccurence(labelName, "1");
        return this;

    }
    
    public LinkScriptless clickLinkWhereLabelNthOccurence(String labelName, String occurnence)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By linkBy = By.xpath("//*[contains(text(),'" + labelName + "')][" + occurnence + "]");
            	objectMethods.findObject(driver, linkBy, 30);          	            
                WebElement linkEle = driver.findElement(linkBy);
                linkEle.click();
                
               
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
    
    public LinkScriptless clickActionLinks(String labelName)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By linkBy = By.xpath("//*[text() ='" + labelName + "']");
            	objectMethods.findObject(driver, linkBy, 30);          	            
                WebElement linkEle = driver.findElement(linkBy);
                linkEle.click();
                
               
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


}
