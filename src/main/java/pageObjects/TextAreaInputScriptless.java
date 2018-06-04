package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;


public class TextAreaInputScriptless {
	WebDriver driver;

	public TextAreaInputScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(TextInputScriptless.class);

	public TextAreaInputScriptless enterTextAreaInputWhereLabelFirstOccurence(String labelName, String value) {

		enterTextAreaInputWhereLabelNthOccurence(labelName, value, "1");
		return this;

	}

	public TextAreaInputScriptless enterTextAreaInputWhereLabelNthOccurence(String labelName, String value,
			String occurnence) {

		boolean isFlagged = false;
		while (!isFlagged) {
			try {
				By textAreaInputBy = By.xpath("(//label[text()='" + labelName
						+ "']/parent :: td/parent :: tr/td[2]//textarea)[" + occurnence + "]");

				objectMethods.findObject(driver, textAreaInputBy, 30);
				WebElement textAreaInputEle = driver.findElement(textAreaInputBy);
				textAreaInputEle.clear();
				textAreaInputEle.sendKeys(value);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
			}
		}

		return this;

	}

	public TextAreaInputScriptless enterTextAreaInputWhereObjectTagId(String domid, String value) {

		boolean isFlagged = false;
		while (!isFlagged) {
			try {
				By textAreaInputBy = By.xpath("//textarea[contains(@id,'" + domid + "')]");

				objectMethods.findObject(driver, textAreaInputBy, 30);
				WebElement textAreaInputEle = driver.findElement(textAreaInputBy);
				textAreaInputEle.clear();
				textAreaInputEle.sendKeys(value);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
			}
		}

		return this;

	}

}