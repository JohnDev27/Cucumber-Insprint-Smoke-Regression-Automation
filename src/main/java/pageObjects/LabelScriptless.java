/*
 * Copyright (c) 2015, Liberty Mutual
 * Proprietary and Confidential
 * All Rights Reserved
 */
package pageObjects;




import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import functionalLibrary.ObjectMethods;



public class LabelScriptless {
    

	WebDriver driver;

	public LabelScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(LabelScriptless.class);
	
	
	
	
    public LabelScriptless verifyLabelNameWhereLabelFirstOccurence(String labelName)
    {

        verifyLabelNameWhereLabelNthOccurence(labelName, "1");
        return this;

    }

    public LabelScriptless verifyLabelNameWhereLabelNthOccurence(String labelName, String occurnence)
    {

        try
        {
            By labelBy = By.xpath("(//*[text()='" + labelName + "'])[" + occurnence + "]");

            objectMethods.findObject(driver, labelBy, 30);
            WebElement labelDsiplayEle = driver.findElement(labelBy);
            String actualString = labelDsiplayEle.getText().trim(); 
            System.out.println("Got the Label");
            Assert.assertTrue(actualString.contains(labelName));
        }
        catch (StaleElementReferenceException e)
        {
            LOG.info("Into Stale");
        }
    

    return this;

   	
    }

           
}
  
