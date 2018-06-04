package functionalLibrary;

import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class WebDriverFactory {

	static Logger log = Logger.getLogger(WebDriverFactory.class);
	

	public static WebDriver getWebDriver(String browser) {
		WebDriver driver = null;
		Properties prop = DataStorage.loadProperties();

		try {
			switch (browser) {
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", prop.getProperty("webdriver.chrome.driverPath"));
				driver = new ChromeDriver();
				break;

			case "FIREFOX":
				driver = new FirefoxDriver();
				break;

			case "IE":
				System.setProperty("webdriver.ie.driver", prop.getProperty("webdriver.ie.driverPath"));
				driver = new InternetExplorerDriver();
				break;

			case "SAUCE":				

				String USERNAME = prop.getProperty("sauce.api.name");
				String ACCESS_KEY = prop.getProperty("sauce.api.key");
				String URL = "https://" + USERNAME + ":" + ACCESS_KEY + prop.getProperty("sauce.url.suffix");

				DesiredCapabilities caps = DesiredCapabilities.chrome();
				
				
				caps.setCapability("platform", prop.getProperty("sauce.capabilities.platform"));
				caps.setCapability("version", prop.getProperty("sauce.capabilities.version"));
				caps.setCapability("parent-tunnel", prop.getProperty("sauce.capabilities.parent.tunnel"));
				caps.setCapability("tunnel-identifier", prop.getProperty("sauce.capabilities.tunnel.identifier"));
				caps.setCapability("name", prop.getProperty("sauce.capabilities.name"));
				caps.setCapability("avoidProxy", true);
				caps.setCapability("maxDuration", 10800);

				driver = new RemoteWebDriver(new URL(URL), caps);
				break;

			case "HEADLESS":

				System.setProperty("webdriver.chrome.driver", prop.getProperty("webdriver.chrome.driverPath"));

				// Add options to Google Chrome. The window-size is important
				// for responsive sites
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				options.addArguments("window-size=1200x600");

				driver = new ChromeDriver(options);
				break;

			default:
				driver = new FirefoxDriver();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;
	}

}
