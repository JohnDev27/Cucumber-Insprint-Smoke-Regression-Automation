package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import functionalLibrary.ObjectMethods;


public class ButtonScriptless {
	
	WebDriver driver;
	
	public ButtonScriptless (WebDriver driver) {
		this.driver = driver;
	}
	
	ObjectMethods objectMethods = new ObjectMethods();
	
	private static final Logger LOG = LogManager.getLogger(ButtonScriptless.class);
   
	public ButtonScriptless clickButtonWhereLabel(String labelName)
    {

        clickButtonWhereLabelNthOccurence(labelName, "1");
        return this;

    }
	
	public ButtonScriptless waitForButtonWhereAnyLetterUnderLinedIsVisible(String labelName, String underLinedLetter)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By buttonBy =  By.xpath("//span[@class='g-underlined' and text() = '" + underLinedLetter + "']");
            	          	            
                objectMethods.waitTillElementVisible(driver, buttonBy);
                
               
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
	
	public ButtonScriptless waitForButtonWhereAnyLetterUnderLinedIsClickable(String labelName, String underLinedLetter)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By buttonBy =  By.xpath("//span[@class='g-underlined' and text() = '" + underLinedLetter + "']");
            	          	            
                objectMethods.waitTillElementClickable(driver, buttonBy);
                
               
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
    
    public ButtonScriptless clickButtonWhereLabelNthOccurence(String labelName, String occurnence)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By buttonBy =  By.xpath("(//*[normalize-space(text())='" + labelName + "' and contains(@class,'btn')])["
                        + occurnence + "]");
            	objectMethods.findObject(driver, buttonBy, 30);          	            
                WebElement buttonEle = driver.findElement(buttonBy);
                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+buttonEle.getLocation().y+")");
                // ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+buttonEle.getLocation().x+")");
                buttonEle.click();
                
               
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
    
    public ButtonScriptless clickButtonWhereAnyLetterUnderLined(String labelName, String underLinedLetter)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By buttonBy =  By.xpath("//span[@class='g-underlined' and text() = '" + underLinedLetter + "']");
            	objectMethods.findObject(driver, buttonBy, 30);          	            
                WebElement buttonEle = driver.findElement(buttonBy);
                buttonEle.click();
                
               
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
    
	public ButtonScriptless jsClickButtonWhereLabel(String labelName)
    {

		jsClickButtonWhereLabelNthOccurence(labelName, "1");
        return this;

    }
    
    
    public ButtonScriptless jsClickButtonWhereLabelNthOccurence(String labelName, String occurnence)
    {

        boolean isFlagged = false;

        while (!isFlagged)
        {
            try
            {

            	By buttonBy =  By.xpath("(//*[normalize-space(text())='" + labelName + "' and contains(@class,'btn')])["
                        + occurnence + "]");
            	objectMethods.findObject(driver, buttonBy, 30);          	            
                WebElement buttonEle = driver.findElement(buttonBy);
                jsClick(buttonEle);                
               
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
    
   public void jsClick(WebElement element){
    	
    	
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", element);
    }
   
   public ButtonScriptless clickNewClaim() throws InterruptedException
   {

       boolean isFlagged = false;

       while (!isFlagged)
       {
           try
           {

           	   By buttonBy =  By.xpath("//SPAN[@id='TabBar:ClaimTab-btnWrap']/self::SPAN");
           	   objectMethods.findObject(driver, buttonBy, 60);
           	   
               WebElement buttonEle = driver.findElement(buttonBy);
               Actions actions = new Actions(driver);
               actions.moveToElement(buttonEle, buttonEle.getSize().width - 2, 2).click().build().perform();
               
               By newclaim =  By.xpath("//a[@id= 'TabBar:ClaimTab:ClaimTab_FNOLWizard_Auto-itemEl']");
               objectMethods.findObject(driver, newclaim, 30);          	            
               WebElement newclaimEle = driver.findElement(newclaim);
               newclaimEle.click();     
              
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
