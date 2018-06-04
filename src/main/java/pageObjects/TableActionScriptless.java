package pageObjects;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functionalLibrary.DataStorage;
import functionalLibrary.ObjectMethods;

public class TableActionScriptless {

	WebDriver driver;

	public TableActionScriptless(WebDriver driver) {
		this.driver = driver;
	}

	ObjectMethods objectMethods = new ObjectMethods();

	private static final Logger LOG = LogManager.getLogger(TableActionScriptless.class);

	public TableActionScriptless doubleClickOnTableElementAndSetText(String elementlocator, String input) {

		try {
			By elementBy = By.xpath(elementlocator);

			objectMethods.findObject(driver, elementBy, 30);
			WebElement element = driver.findElement(elementBy);

			Actions actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			actions.moveToElement(element).doubleClick().build().perform();
			actions.click();
			actions.doubleClick();
			actions.build().perform();

			actions.click();
			actions.doubleClick();
			actions.build().perform();
			objectMethods.hardWait(3000);

			driver.switchTo().activeElement().clear();

			driver.switchTo().activeElement().sendKeys(input);
			objectMethods.hardWait(3000);
			driver.switchTo().activeElement().sendKeys(Keys.TAB);

		} catch (StaleElementReferenceException e) {
			LOG.info("Into Stale");

		}

		return this;

	}

	public TableActionScriptless clickOnTableElement(String elementlocator) {

		try {
			By elementBy = By.xpath(elementlocator);

			objectMethods.findObject(driver, elementBy, 30);
			WebElement element = driver.findElement(elementBy);

			Actions actions = new Actions(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			actions.moveToElement(element).click().build().perform();
			actions.click();

			objectMethods.hardWait(3000);

		} catch (StaleElementReferenceException e) {
			LOG.info("Into Stale");

		}

		return this;

	}

	public TableActionScriptless changeCoverageStatusInEditPolicyCoverageTable(String criteriaColumnName,
			String criteriaColumnValue, String actionColumnName, String inputValue) {

		try {

			String tableId = "//*[@id = 'table ID from the DOM']";
			String strFirst = tableId + "//tr[";
			String strSecond = "]/td[";
			String strThird = "]";
			String strFourth = "/div";

			// Set the Column header
			DataStorage.put("COL 2 name from DOM", "2");
			DataStorage.put("COL 3 name from DOM", "3");
			DataStorage.put("COL 4 name from DOM", "4");
			DataStorage.put("COL 5 name from DOM", "5");

			// Get the column index of the criteria column
			String criteriaColumnIndex = DataStorage.get("COL 2 name from DOM");

			// Get the column index of the action column
			String actionColumnIndex = DataStorage.get("COL 3 name from DOM");

			// Get the row number of the criteria
			int intRowCount = driver.findElements(By.xpath(tableId + "//tr")).size();
			// System.out.println(intRowCount);

			for (int intCnt1 = 1; intCnt1 <= intRowCount; intCnt1++) {
				// System.out.println("Into For Loop");

				String strFinal = strFirst + intCnt1 + strSecond + criteriaColumnIndex + strThird;
				String strTableData = driver.findElement(By.xpath(strFinal)).getText().trim();
				// System.out.println(strFinal);
				// System.out.println(strTableData);
				if (strTableData.equals(criteriaColumnValue.trim())) {

					String finalLocator = strFirst + intCnt1 + strSecond + actionColumnIndex + strThird + strFourth;
					// System.out.println(finalLocator);
					doubleClickOnTableElementAndSetText(finalLocator, inputValue);

					break;
				}

			}

		} catch (StaleElementReferenceException e) {
			LOG.info("Into Stale");

		}

		return this;

	}

	

	public TableActionScriptless validateText(String elementlocator, String expectedString) {

		try {
			By elementBy = By.xpath(elementlocator);

			objectMethods.findObject(driver, elementBy, 30);
			WebElement element = driver.findElement(elementBy);
			String actualText = element.getText();
			System.out.println(actualText);
			System.out.println(expectedString);
			Assert.assertTrue(actualText.contains(expectedString));

		} catch (StaleElementReferenceException e) {
			LOG.info("Into Stale");

		}

		return this;

	}

	public TableActionScriptless clickSelectButtonInTableWhere(String lvName, String row) {
		boolean isFlagged = false;

		while (!isFlagged) {
			try {
				By buttonBy = By.xpath("//*[contains(@id,'" + lvName + "') and @class='x-panel x-panel-default x-grid']"
						+ "/div[contains(@class,'x-grid-body')]/div/table/tbody/tr[" + row
						+ "]//a[contains(@id,'_Select') or contains(@id,':selectButton')]");
				objectMethods.findObject(driver, buttonBy, 30);
				WebElement buttonEle = driver.findElement(buttonBy);
				jsClick(buttonEle);
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
	
public TableActionScriptless checkCheckAll(String lvName) {

		
		try {
			By elementBy = By.xpath("//div[contains(@id,'"+lvName+"')]//span[contains(@id, 'rowcheckcolumn')]/div");

			objectMethods.findObject(driver, elementBy, 30);
			WebElement element = driver.findElement(elementBy);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			

		} catch (StaleElementReferenceException e) {
			LOG.info("Into Stale");
			
		}
	

	return this;

}

	public boolean verifyWebTableElements(String strTable, String strExpData, String strColIdx, String strCtrlCol) {
		boolean blnStatus = false;
		try {
			String[] arrExpData = strExpData.split("\\|");
			String[] arrColIdx = strColIdx.split("\\|");
			int intLen = arrExpData.length;
			int intRowCount = driver.findElements(By.xpath(strTable + "//tr")).size();
			String strFirst = strTable + "//tr[";
			String strSecond = "]/td[";
			String strThird = "]";
			for (int intCnt1 = 1; intCnt1 <= intRowCount; intCnt1++) {
				String strFinal = strFirst + intCnt1 + strSecond + arrColIdx[0] + strThird;
				String strTableData = driver.findElement(By.xpath(strFinal)).getText().trim();
				if (strTableData.contains(arrExpData[0].trim())) {
					Assert.assertEquals(arrExpData[0], strTableData);
					blnStatus = true;
					for (int intCount = 1; intCount < intLen; intCount++) {
						strFinal = strFirst + intCnt1 + strSecond + arrColIdx[intCount] + strThird;
						strTableData = driver.findElement(By.xpath(strFinal)).getText().trim();
						if (strTableData.contains(arrExpData[intCount].trim())) {
							Assert.assertEquals(arrExpData[intCount], strTableData);
						} else {
							blnStatus = false;
							Assert.assertEquals(arrExpData[intCount], strTableData);
							if (intCount == intLen) {
								break;
							}
						}
					}
					if (blnStatus) {
						if (strCtrlCol != null) {
							String strControls;
							WebElement element;
							String[] arrCtrlCol = strCtrlCol.split("\\|");
							strControls = strFirst + intCnt1 + strSecond + arrCtrlCol[1] + strThird;
							Actions actions = new Actions(driver);
							switch (arrCtrlCol[0].toUpperCase()) {
							case "CHECKBOX":
								strControls = strControls + "/div/img";
								element = driver.findElement(By.xpath(strControls));
								actions.moveToElement(element).click().perform();
								element.click();
								break;
							case "RADIOBUTTON":
								strControls = strControls + "/div/div";
								break;
							case "LINK":
								strControls = strControls + "/div/a";
								break;
							case "IMAGE":
								strControls = strControls + "/div/img[@src='images/app/' " + arrCtrlCol[2].trim()
										+ " .png'";
								break;
							case "MULTIPLE_LINKS":
								strControls = strControls + "/div/a[text()='" + arrCtrlCol[2].trim() + "']";
								break;
							case "INPUTTEXT":
								strControls = strControls + "/div";
								element = driver.findElement(By.xpath(strControls));
								actions.moveToElement(element).click().perform();
								actions.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME)
										.keyUp(Keys.SHIFT).sendKeys(Keys.BACK_SPACE).sendKeys(arrCtrlCol[2]).perform();
								break;
							case "LABEL":
								strControls = strControls + "/div";
								break;
							default:
								break;
							}
							if (!arrCtrlCol[0].equalsIgnoreCase("INPUTTEXT")
									&& !arrCtrlCol[0].equalsIgnoreCase("CHECKBOX")) {
								element = driver.findElement(By.xpath(strControls));
								element.click();
								// BrowserActions.hardWaitInSeconds(5);
								objectMethods.findObject(driver, By.xpath(strControls), 30);
							}
						}
						break;
					}
				} else if (intCnt1 == intRowCount + 1) {
					Assert.assertFalse("There is no record in the table", blnStatus);
				}
			}
		} catch (NoSuchElementException e) {
			Assert.assertFalse("Web Table is not present", blnStatus);
		} catch (StaleElementReferenceException e) {
			Assert.assertFalse(e.getMessage(), blnStatus);
		} catch (ArrayIndexOutOfBoundsException e) {
			Assert.assertFalse(e.getMessage(), blnStatus);
		} catch (WebDriverException e) {
			Assert.assertFalse(e.getMessage(), blnStatus);
		}
		return blnStatus;
	}

}