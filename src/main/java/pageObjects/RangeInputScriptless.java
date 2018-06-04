package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import functionalLibrary.ObjectMethods;


public class RangeInputScriptless {

	WebDriver driver;

	public RangeInputScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(RangeInputScriptless.class);

	public RangeInputScriptless enterRangeInputWhereLabelFirstOccurence(String labelName, String value) {

		enterRangeInputWhereLabelNthOccurence(labelName, value, "1");
		return this;

	}

	public RangeInputScriptless enterRangeInputWhereLabelNthOccurence(String labelName, String value,
			String occurnence) {

		boolean isFlagged = false;
		while (!isFlagged) {
			try {
				By rangeInput = By.xpath("(//label[text()='" + labelName
						+ "']/parent :: td/parent :: tr/td[2]//input[@role='combobox'])[" + occurnence + "]");

				objectMethods.findObject(driver, rangeInput, 30);
				WebElement rangeInputele = driver.findElement(rangeInput);
				rangeInputele.clear();
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + rangeInputele.getLocation().y + ")");
				rangeInputele.sendKeys(value);
				rangeInputele.sendKeys(Keys.TAB);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
				continue;
			}
		}

		return this;

	}

	public RangeInputScriptless verifyRangeInputWhereLabel(String labelName, String value, String count) {

		boolean isFlagged = false;
		// boolean found = false;
		while (!isFlagged) {
			try {
				By rangeInput = By.xpath("//label[text()='" + labelName
						+ "']/parent :: td/parent :: tr//td[2]/table/tbody/tr/td[2]/div");
				objectMethods.findObject(driver, rangeInput, 30);
				WebElement rangeInputele = driver.findElement(rangeInput);
				jsClick(rangeInputele);
				By rangeInput1 = By.xpath("//div[@class='x-boundlist-list-ct x-unselectable']/ul//li");
				objectMethods.findObject(driver, rangeInput1, 30);
				int elementcount = driver.findElements(rangeInput1).size();
				int rangeCount = Integer.parseInt(count);
				if (rangeCount == elementcount) {
					for (int i = 1; i <= elementcount; i++) {
						By rangeInput2 = By
								.xpath("//div[@class='x-boundlist-list-ct x-unselectable']/ul//li[" + i + "]");
						String getRangeInput = driver.findElement(rangeInput2).getText();

						if (value.equalsIgnoreCase(getRangeInput)) {
							isFlagged = true;
							break;
						}

					}

				}
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
				continue;
			}
		}

		return this;

	}

	public void jsClick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public RangeInputScriptless selectVehicleLocation(String value) {
		By rangeInput = By.xpath("/*[contains(id,'VehicleLocDuringBusHrs_RangeInput')]");
		objectMethods.findObject(driver, rangeInput, 30);
		WebElement rangeInputele = driver.findElement(rangeInput);
		Select dropList = new Select(rangeInputele);
		dropList.selectByValue(value);
		return this;
	}

}
