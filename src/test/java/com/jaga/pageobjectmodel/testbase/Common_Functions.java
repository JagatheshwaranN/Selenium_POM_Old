/**
 * Package creation
 * 
 * @author Jagatheshwaran
 * @Date : 5/6/2017
 */
package com.jaga.pageobjectmodel.testbase;

/**
 * Importing the necessary classes
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * A class is created with name : Common_Functionalities
 */
public class Common_Functions {

	/**
	 * Declaring all the Global variables
	 * 
	 * @author Jagatheshwaran
	 */
	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public WebElement element;
	Common_Functions cf;
	public static int indexSI = 1;

	/**
	 * This method will load the property file,select the browser and launch the
	 * browser with the URL
	 * 
	 * @author Jagatheshwaran
	 */

	public void init() throws IOException {

		loadProperties();
		selectBrowser(Repository.getProperty("browser"));
		driver.get(Repository.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	/**
	 * This method will initialize and load the Property file
	 * 
	 * @author Jagatheshwaran
	 */
	public void loadProperties() throws IOException {
		f = new File(System.getProperty("user.dir") + "//src//test//java//com//jaga//pageobjectmodel//config//config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);

	}

	/**
	 * This method will initialize the browser object
	 * 
	 * @param browser
	 * @return browser driver
	 * 
	 * @author Jagatheshwaran
	 */
	public WebDriver selectBrowser(String browser) {
		try {
			if (browser.equals("FIREFOX") || browser.equals("firefox")) {
				System.setProperty("webdriver.firefox.marionette", "E:/ECLIPSE ENV//SELENIUM LIBS//geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				return driver;
			} else if (browser.equals("CHROME") || browser.equals("chrome")) {
				System.getProperty("webdriver.chrome.driver", "");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				return driver;
			} else if (browser.equals("IE") || browser.equals("ie")) {
				System.getProperty("webdriver.ie.driver", "");
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
				return driver;
			}

		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in invokeApp() " + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return null;

	}

	/**
	 * This method will wait till the visibility of particular webElement
	 * 
	 * @param element
	 * @param timeToWaitInSec
	 * 
	 * @author Jagatheshwaran
	 */
	public void explicitWait(WebElement element, int timeToWaitInSec) {
		wait = new WebDriverWait(driver, timeToWaitInSec);
		// wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * This method will wait for the page to load
	 * 
	 * @param timeoutInSec
	 * 
	 * @author Jagatheshwaran
	 */
	public void waitForPageLoad(long timeoutInSec) {
		ExpectedCondition<Boolean> expectTime = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("waiting for page to load...");
			wait = new WebDriverWait(driver, timeoutInSec);
			wait.until(expectTime);

		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Throwable e) {
			System.out.println("Timeout waiting for Page Load Request to complete after " + timeoutInSec + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	/**
	 * This method will wait for the specified time to check visibility of
	 * WebElements
	 * 
	 * @author Jagatheshwaran
	 */
	public static void waitFunction() {
		wait = new WebDriverWait(driver, 50);
	}

	/**
	 * This method will enter the text by Id
	 * 
	 * @param id
	 * @param data
	 * 
	 * @author Jagatheshwaran
	 */
	public void enterTextById(By id, String data) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.presenceOfElementLocated(id)).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(id)).sendKeys(data);
			System.out.println("The value " + data + " is entered at element " + id);
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in entering value" + data + " into element with id : " + id + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will enter the text by Name
	 * 
	 * @param name
	 * @param data
	 * 
	 * @author Jagatheshwaran
	 */
	public void enterTextByName(By name, String data) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.presenceOfElementLocated(name)).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(data);
			System.out.println("The value " + data + " is entered at element " + name);
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in entering value" + data + " into element with name : " + name + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will enter the text by CssSelector
	 * 
	 * @param CssSelector
	 * @param data
	 * 
	 * @author Jagatheshwaran
	 */
	public void enterTextByCssSelector(By cssSelector, String data) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.presenceOfElementLocated(cssSelector)).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector)).sendKeys(data);
			System.out.println("The value " + data + " is entered at element " + cssSelector);
		} catch (TimeoutException e) {
			System.err.println("The element with cssSelector :" + cssSelector + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in entering value" + data + " into element with cssSelector : " + cssSelector + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will enter the text by ClassName
	 * 
	 * @param className
	 * @param data
	 * 
	 * @author Jagatheshwaran
	 */
	public void enterTextByClassName(By className, String data) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.presenceOfElementLocated(className)).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(className)).sendKeys(data);
			System.out.println("The value " + data + " is entered at element " + className);
		} catch (TimeoutException e) {
			System.err.println("The element with className :" + className + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in entering value" + data + " into element with className : " + className + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will enter the text by Xpath
	 * 
	 * @param xpath
	 * @param data
	 * 
	 * @author Jagatheshwaran
	 */
	public void enterTextByXpath(By xpath, String data) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.presenceOfElementLocated(xpath)).clear();
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).sendKeys(data);
			System.out.println("The value " + data + " is entered at element " + xpath);
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in entering value" + data + " into element with xpath : " + xpath + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the page Title
	 * 
	 * @param title
	 * 
	 * @author Jagatheshwaran
	 */
	public boolean verifyTitle(String title) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.titleIs(title)))
				System.out.println("Actual Title is matched with expected title");
			else
				System.out.println("Actual Title doesnot matches with expected title");

		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying title with expected tilte :" + title + " \n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return true;

	}

	/**
	 * This method will verify the Text by Id
	 * 
	 * @param id
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextById(By id, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(id, text)))
				System.out.println("Actual text is matched with expected text");
			else
				System.out.println("Actual text doesnot matches with expected text");
		} catch (TimeoutException e) {
			System.err.println("The element with id : " + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying Text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text by Name
	 * 
	 * @param name
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextByName(By name, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBe(name, text)))
				System.out.println("Actual text is matched with expected text");
			else
				System.out.println("Actual text doesnot matches with expected text");
		} catch (TimeoutException e) {
			System.err.println("The element with name: " + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying Text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text by CssSelector
	 * 
	 * @param cssSelector
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextByCssSelector(By cssSelector, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBe(cssSelector, text)))
				System.out.println("Actual text is matched with expected text");
			else
				System.out.println("Actual text doesnot matches with expected text");
		} catch (TimeoutException e) {
			System.err.println("The element with cssSelector: " + cssSelector + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying Text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text by ClassName
	 * 
	 * @param className
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextByClassName(By className, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBe(className, text)))
				System.out.println("Actual text is matched with expected text");
			else
				System.out.println("Actual text doesnot matches with expected text");
		} catch (TimeoutException e) {
			System.err.println("The element with className: " + className + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying Text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text by Xpath
	 * 
	 * @param xpath
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextByXpath(By xpath, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBe(xpath, text)))
				System.out.println("Actual text is matched with expected text");
			else
				System.out.println("Actual text doesnot matches with expected text");
		} catch (TimeoutException e) {
			System.err.println("The element with xpath: " + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception occured in verifying Text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text using contains by Id
	 * 
	 * @param id
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */
	public void verifyTextContainsById(By id, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(id, text)))
				System.out.println("The Actual text contains the provided text");
			else
				System.out.println("The Actual text doesn't contains the provided text");
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("unexpected exception occured in verifying  actual text contains expected text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text using contains by Name
	 * 
	 * @param name
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */

	public void verifyTextContainsByName(By name, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(name, text)))
				System.out.println("The Actual text contains the provided text");
			else
				System.out.println("The Actual text doesn't contains the provided text");
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("unexpected exception occured in verifying  actual text contains expected text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text using contains by CssSelector
	 * 
	 * @param cssSelector
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */

	public void verifyTextContainsByCssSelector(By cssSelector, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(cssSelector, text)))
				System.out.println("The Actual text contains the provided text");
			else
				System.out.println("The Actual text doesn't contains the provided text");
		} catch (TimeoutException e) {
			System.err.println("The element with cssSelector :" + cssSelector + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("unexpected exception occured in verifying  actual text contains expected text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text using contains by ClassName
	 * 
	 * @param className
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */

	public void verifyTextContainsByClassName(By className, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(className, text)))
				System.out.println("The Actual text contains the provided text");
			else
				System.out.println("The Actual text doesn't contains the provided text");
		} catch (TimeoutException e) {
			System.err.println("The element with className :" + className + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("unexpected exception occured in verifying  actual text contains expected text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will verify the Text using contains by Xpath
	 * 
	 * @param xpath
	 * @param text
	 * 
	 * @author Jagatheshwaran
	 */

	public void verifyTextContainsByXpath(By xpath, String text) {
		try {
			waitFunction();
			if (wait.until(ExpectedConditions.textToBePresentInElementLocated(xpath, text)))
				System.out.println("The Actual text contains the provided text");
			else
				System.out.println("The Actual text doesn't contains the provided text");
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("unexpected exception occured in verifying  actual text contains expected text " + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by Id
	 * 
	 * @param id
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickById(By id) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(id));
			driver.findElement(id).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by id :" + id + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by Name
	 * 
	 * @param name
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByName(By name) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(name));
			driver.findElement(name).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by name :" + name + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by CssSelector
	 * 
	 * @param cssSelector
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByCssSelector(By cssSelector) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(cssSelector));
			driver.findElement(cssSelector).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with cssSelector :" + cssSelector + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by cssSelector :" + cssSelector + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by ClassName
	 * 
	 * @param className
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByClassName(By className) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(className));
			driver.findElement(className).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with className :" + className + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by className :" + className + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by Xpath
	 * 
	 * @param xpath
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByXpath(By xpath) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(xpath));
			driver.findElement(xpath).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by xpath :" + xpath + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by PartialLinkText
	 * 
	 * @param partialLinkText
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByPartialLinkText(By partialLinkText) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(partialLinkText));
			driver.findElement(partialLinkText).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with partialLinkText :" + partialLinkText + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by partialLinkText :" + partialLinkText + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will click the WebElement by LinkText
	 * 
	 * @param linkText
	 * 
	 * @author Jagatheshwaran
	 */
	public void clickByLinkText(By linkText) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.elementToBeClickable(linkText));
			driver.findElement(linkText).click();
			System.out.println("Element clicked successfully");
		} catch (TimeoutException e) {
			System.err.println("The element with linkText :" + linkText + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in clicking by linkText :" + linkText + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will get the Text of WebElement by Id
	 * 
	 * @param id
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTextById(By id) {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(id)).getText();
			System.out.println("Returning Actual Text " + text);
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in geting text by locator id :" + id + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return text;

	}

	/**
	 * This method will get the Text of WebElement by Name
	 * 
	 * @param name
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTextByName(By name) {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(name)).getText();
			System.out.println("Returning Actual Text " + text);
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in geting text by locator name :" + name + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return text;

	}

	/**
	 * This method will get the Text of WebElement by CssSelector
	 * 
	 * @param cssSelector
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTextByCssSelector(By cssSelector) {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector)).getText();
			System.out.println("Returning Actual Text " + text);
		} catch (TimeoutException e) {
			System.err.println("The element with cssSelector :" + cssSelector + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in geting text by locator cssSelector :" + cssSelector + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return text;

	}

	/**
	 * This method will get the Text of WebElement by ClassName
	 * 
	 * @param className
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTextByClassName(By className) {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(className)).getText();
			System.out.println("Returning Actual Text " + text);
		} catch (TimeoutException e) {
			System.err.println("The element with className :" + className + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in geting text by locator className :" + className + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return text;

	}

	/**
	 * This method will get the Text of WebElement by Xpath
	 * 
	 * @param xpath
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTextByXpath(By xpath) {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)).getText();
			System.out.println("Returning Actual Text " + text);
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in geting text by locator xpath :" + xpath + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
		return text;

	}

	/**
	 * This method will select visible Text by Id
	 * 
	 * @param id
	 * @param value
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectVisibleTextById(By id, String value) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(id)));
			select.selectByVisibleText(value);
			System.out.println("DropDown option " + value + " is selected at element " + id);
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting visible option by id:" + id + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will select visible Text by Name
	 * 
	 * @param name
	 * @param value
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectVisibleTextByName(By name, String value) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(name)));
			select.selectByVisibleText(value);
			System.out.println("DropDown option " + value + " is selected at element " + name);
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting visible option by name:" + name + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will select visible Text by Xpath
	 * 
	 * @param xpath
	 * @param value
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectVisibleTextByXpath(By xpath, String value) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)));
			select.selectByVisibleText(value);
			System.out.println("DropDown option " + value + " is selected at element " + xpath);
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting visible option by xpath:" + xpath + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will select Index by Id
	 * 
	 * @param id
	 * @param index
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectIndexById(By id, int index) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(id)));
			select.selectByIndex(index);
			System.out.println("The option  at index " + index + " is selected");
		} catch (TimeoutException e) {
			System.err.println("The element with id :" + id + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting by index by id :" + id + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will select Index by Name
	 * 
	 * @param name
	 * @param index
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectIndexByName(By name, int index) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(name)));
			select.selectByIndex(index);
			System.out.println("The option  at index " + index + " is selected");
		} catch (TimeoutException e) {
			System.err.println("The element with name :" + name + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting by index by name :" + name + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will select Index by Xpath
	 * 
	 * @param xpath
	 * @param index
	 * 
	 * @author Jagatheshwaran
	 */
	public void selectIndexByXpath(By xpath, int index) {
		try {
			waitFunction();
			Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)));
			select.selectByIndex(index);
			System.out.println("The option  at index " + index + " is selected");
		} catch (TimeoutException e) {
			System.err.println("The element with xpath :" + xpath + " doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in selecting by index by xpath :" + xpath + "\n" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will switch to Parent Window
	 * 
	 * 
	 * @author Jagatheshwaran
	 */
	public void switchToParentWindow() {
		try {
			Set<String> windowhandleSet = driver.getWindowHandles();
			System.out.println("count of opened windows :" + windowhandleSet.size());
			for (String windowhandle : windowhandleSet) {
				System.out.println("navigating windows :" + windowhandle);
				driver.switchTo().window(windowhandle);
				break;
			}
			System.out.println("control is switched to parent window");
		} catch (NoSuchWindowException e) {
			System.err.println("Exception occured while switching to parent window  " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in switching to parent window:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will switch to Parent Window
	 * 
	 * @param totalWindowExpected
	 * 
	 * @author Jagatheshwaran
	 */
	public void switchToParentWindow(int totalWindowExpected) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowExpected));
			Set<String> windowhandleSet = driver.getWindowHandles();
			System.out.println("count of opened windows :" + windowhandleSet.size());
			for (String windowhandle : windowhandleSet) {
				System.out.println("navigating windows :" + windowhandle);
				driver.switchTo().window(windowhandle);
				break;
			}
			System.out.println("control is switched to parent window");
		} catch (TimeoutException e) {
			System.err.println("Expected no of windows doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (NoSuchWindowException e) {
			System.err.println("Exception occured while switching to parent window  " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in switching to parent window:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will switch to Last Window
	 * 
	 * 
	 * @author Jagatheshwaran
	 */
	public void switchToLastWindow() {
		try {
			Set<String> windowhandleSet = driver.getWindowHandles();
			System.out.println("count of opened windows :" + windowhandleSet.size());
			for (String windowhandle : windowhandleSet) {
				System.out.println("navigating windows :" + windowhandle);
				driver.switchTo().window(windowhandle);
			}
			System.out.println("control is switched to last window");
		} catch (NoSuchWindowException e) {
			System.err.println("Exception occured while switching window  " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in switching to Last Window:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will switch to Last Window
	 * 
	 * @param
	 * 
	 * @author Jagatheshwaran
	 */
	public void switchToLastWindow(int totalWindowExpected) {
		try {
			waitFunction();
			wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowExpected));
			Set<String> windowhandleSet = driver.getWindowHandles();
			System.out.println("count of opened windows :" + windowhandleSet.size());
			for (String windowhandle : windowhandleSet) {
				System.out.println("navigating windows :" + windowhandle);
				driver.switchTo().window(windowhandle);
			}
			System.out.println("control is switched to last window");
		} catch (TimeoutException e) {
			System.err.println("Expected no of windows doesn't exists " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (NoSuchWindowException e) {
			System.err.println("Exception occured while switching window  " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in switching to Last Window:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will handle Alert accept
	 * 
	 * 
	 * @author Jagatheshwaran
	 */

	public void acceptTheAlert() {
		try {
			waitFunction();
			wait.until(ExpectedConditions.alertIsPresent()).accept();
			System.out.println("Alert accepted");
		} catch (TimeoutException e) {
			System.err.println("Alert is not present" + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (NoAlertPresentException e) {
			System.err.println("Exception occured in accepting alert " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in accepting alert:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will handle Alert dismiss
	 * 
	 * 
	 * @author Jagatheshwaran
	 */

	public void dismissTheAlert() {
		try {
			waitFunction();
			wait.until(ExpectedConditions.alertIsPresent()).dismiss();
			System.out.println("Alert dismissed");
		} catch (TimeoutException e) {
			System.err.println("Alert is not present" + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (NoAlertPresentException e) {
			System.err.println("Exception occured in accepting alert " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in accepting alert:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will get the text of Alert
	 * 
	 * 
	 * @author Jagatheshwaran
	 */

	public void getTextofAlert() {
		String text = null;
		try {
			waitFunction();
			text = wait.until(ExpectedConditions.alertIsPresent()).getText();
			System.out.println("Alert text" + text);
		} catch (TimeoutException e) {
			System.err.println("Alert is not present" + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (NoAlertPresentException e) {
			System.err.println("Exception occured in accepting alert " + e.getMessage());

			throw new RuntimeException("FAILED");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in accepting alert:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}
	}

	/**
	 * This method will close the current browser window
	 * 
	 * 
	 * @author Jagatheshwaran
	 */
	public void closeBrowser() {

		try {
			if (driver != null)
				driver.close();
			System.out.println("Browser closed");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in Close browser:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will close all the browser windows
	 * 
	 * 
	 * @author Jagatheshwaran
	 */
	public void closeAllBrowsers() {

		try {
			if (driver != null)
				driver.quit();
			System.out.println("All Browsers closed");
		} catch (WebDriverException e) {
			System.err.println("The Browser could not be found " + e.getMessage());
			throw new RuntimeException("FAILED");
		} catch (Exception e) {
			System.err.println("Unexpected exception in Close browser:" + e.getMessage());
			throw new RuntimeException("FAILED");
		}

	}

	/**
	 * This method will fetch the Test Data from the Property file
	 * 
	 * @param Property
	 * 
	 * @author Jagatheshwaran
	 */
	public String getTestData(String property) throws InvocationTargetException {

		f = new File(System.getProperty("user.dir") + "//src//test//java//com//jaga//pageobjectmodel//config//config.properties");
		try {
			FI = new FileInputStream(f);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Repository.load(FI);

		} catch (IOException e) {
			e.printStackTrace();
		}
		String PropData = Repository.getProperty(property);
		System.out.println("The Data from Property file is : " + PropData);
		try {

			String data = PropData.trim();
			return data;
		} catch (IllegalStateException e) {
			return null;
		}

	}

	public String getCurrentUrl() {
		String Url = driver.getCurrentUrl();
		return Url;
	}
}
