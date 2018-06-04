package functionalLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectMethods {
	/**
	 * WebElement element.
	 */

	private WebElement element = null;

	/**
	 * Initialises Object Methods Class.
	 * 
	 * @return
	 */

	public ObjectMethods init() {
		return this;
	}

	/**
	 * Method to attempt to find object after an amount of time.
	 * 
	 * @param driver
	 * @param by
	 * @param timeInSeconds
	 * @return
	 */

	public WebElement findObject(WebDriver driver, By by, int timeInSeconds) {

		// boolean found = false;
		int timeInMilliseconds = timeInSeconds * 1000;

		Thread timer = new Timer(timeInMilliseconds);
		timer.start();

		while (timer.isAlive()) {
			try {

				Thread.sleep(200);
				element = driver.findElement(by);
				
				break;
			} catch (Exception ex) {
				
				continue;
			}
		}

		

		return element;

	}

	public ObjectMethods hardWait(int waitTime) {

		try {

			Thread.sleep(waitTime);

		} catch (Exception ex) {

		}

		return this;

	}
	
	public ObjectMethods waitTillElementVisible(WebDriver driver, By by) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 3000);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (Exception ex) {

		}

		return this;

	}

	public ObjectMethods waitTillElementClickable(WebDriver driver, By by) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 3000);
			element = wait.until(ExpectedConditions.elementToBeClickable(by));

		} catch (Exception ex) {

		}

		return this;

	}
	/**
	 * Timer class to used for Searching for objects.
	 * 
	 *
	 */
	// Description("Timer class to used for Searching for objects.")
	class Timer extends Thread {
		/**
		 * time variable.
		 */
		private final int time;

		/**
		 * Set's local time variable using parameter.
		 * 
		 * @param time
		 */
		public Timer(int time) {
			this.time = time;

		}

		/**
		 * Run's timer class.
		 */
		public void run() {
			try {
				Thread.sleep(time);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
