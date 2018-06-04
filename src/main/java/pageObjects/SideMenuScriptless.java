package pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import functionalLibrary.ObjectMethods;


public class SideMenuScriptless {

	WebDriver driver;

	public SideMenuScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(SideMenuScriptless.class);

	public void jsClick(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public SideMenuScriptless clickLinkSideMenu(String linkLabel) {

		boolean isFlagged = false;

		while (!isFlagged) {
			try {
				By linkBy = By
						.xpath("//span[contains(@class,'x-tree-node-text') and contains(text(),'" + linkLabel + "')]");

				objectMethods.findObject(driver, linkBy, 30);
				WebElement linkEle = driver.findElement(linkBy);
				linkEle.isDisplayed();
				jsClick(linkEle);
				break;
			} catch (StaleElementReferenceException e) {
				LOG.info("Into Stale");
				continue;
			}
		}

		return this;

	}

}
