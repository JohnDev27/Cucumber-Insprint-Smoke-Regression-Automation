package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;


public class TextInputScriptless {

	WebDriver driver;

	public TextInputScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(TextInputScriptless.class);

	public TextInputScriptless enterTextinputWhereLabelFirstOccurence(String labelName, String value)
			throws InterruptedException {

		enterTextinputWhereLabelNthOccurence(labelName, value, "1");

		return this;

	}

	public TextInputScriptless enterTextinputWhereLabelNthOccurence(String labelName, String value, String occurnence)
			throws InterruptedException {

		boolean isFlagged = false;

		while (!isFlagged) {
			try {
				
				By textInputBy = By.xpath("(//label[text()='" + labelName
						+ "']/parent :: td/parent :: tr/td[2]//input[@role='textbox'])[" + occurnence + "]");
				objectMethods.findObject(driver, textInputBy, 30);
				Thread.sleep(2000);
				WebElement textInputele = driver.findElement(textInputBy);
				textInputele.isDisplayed();
				

				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + textInputele.getLocation().y + ")");
				textInputele.clear();
				textInputele.sendKeys(value);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
				continue;

			}

		}
		return this;

	}

	public TextInputScriptless enterTextInputWhereObjectTagId(String domid, String value) {

		boolean isFlagged = false;
		while (!isFlagged) {
			try {
				By textInputBy = By.xpath("//input[contains(@id,'" + domid + "')]");
				WebElement texInputEle = driver.findElement(textInputBy);
				texInputEle.clear();
				texInputEle.sendKeys(value);
				objectMethods.hardWait(3000);
				driver.switchTo().activeElement().sendKeys(Keys.TAB);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
			}
		}

		return this;

	}

}
