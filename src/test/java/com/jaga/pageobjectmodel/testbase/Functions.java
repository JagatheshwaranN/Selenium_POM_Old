/**
 * The below Interface is created to have the common reusable methods to be implemented
 * 
 * @author Jagatheshwaran
 */

/**
 * Importing Package 
 */
package com.jaga.pageobjectmodel.testbase;

/**
 * Importing the necessary predefined classes
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A interface is created with name : Functions
 */
public interface Functions {

	public void init() throws IOException;

	public void loadProperties() throws IOException;

	public WebDriver selectBrowser(String browser);

	public void explicitWait(WebElement element, int timeToWaitInSec);

	public void waitForPageLoad(long timeoutInSec);

	public void enterTextById(By id, String data);

	public void enterTextByName(By name, String data);

	public void enterTextByCssSelector(By cssSelector, String data);

	public void enterTextByClassName(By className, String data);

	public void enterTextByXpath(By xpath, String data);

	public String getUrl();

	public boolean verifyUrl(String url);

	public boolean verifyTitle(String title);

	public void verifyTextById(By id, String text);

	public void verifyTextByName(By name, String text);

	public void verifyTextByCssSelector(By cssSelector, String text);

	public void verifyTextByClassName(By className, String text);

	public void verifyTextByXpath(By xpath, String text);

	public void verifyTextContainsById(By id, String text);

	public void verifyTextContainsByName(By name, String text);

	public void verifyTextContainsByCssSelector(By cssSelector, String text);

	public void verifyTextContainsByClassName(By className, String text);

	public void verifyTextContainsByXpath(By xpath, String text);

	public void clickById(By id);

	public void clickByName(By name);

	public void clickByCssSelector(By cssSelector);

	public void clickByClassName(By className);

	public void clickByXpath(By xpath);

	public void clickByPartialLinkText(By partialLinkText);

	public void clickByLinkText(By linkText);

	public String getTextById(By id);

	public String getTextByName(By name);

	public String getTextByCssSelector(By cssSelector);

	public String getTextByClassName(By className);

	public String getTextByXpath(By xpath);

	public void selectVisibleTextById(By id, String value);

	public void selectVisibleTextByName(By name, String value);

	public void selectVisibleTextByXpath(By xpath, String value);

	public void selectIndexById(By id, int index);

	public void selectIndexByName(By name, int index);

	public void selectIndexByXpath(By xpath, int index);

	public void switchToParentWindow();

	public void switchToParentWindow(int totalWindowExpected);

	public void switchToLastWindow();

	public void switchToLastWindow(int totalWindowExpected);

	public void acceptTheAlert();

	public void dismissTheAlert();

	public void getTextofAlert();

	public boolean verifyAlertPresent();

	public boolean switchToAlert();

	public void sendTextToAlertAndAccept(String text);

	public void sendTextToAlertAndDismiss(String text);

	public boolean isEnabledById(By id);

	public boolean isEnabledByName(By name);

	public boolean isEnabledByCssSelector(By cssSelector);

	public boolean isEnabledByXpath(By xpath);

	public boolean isDisplayedById(By id);

	public boolean isDisplayedByName(By name);

	public boolean isDisplayedByCssSelector(By cssSelector);

	public boolean isDisplayedByXpath(By xpath);

	public boolean isCheckedById(By id);

	public boolean isCheckedByName(By name);

	public boolean isCheckedByCssSelector(By cssSelector);

	public boolean isCheckedByXpath(By xpath);

	public List<WebElement> getListofElementsById(By id);

	public List<WebElement> getListofElementsByCssSelector(By cssSelector);

	public List<WebElement> getListofElementsByXpath(By xpath);

	public void switchToFrameById(By id);

	public void switchToFrameByName(By name);

	public void switchToFrameByCssSelector(By cssSelector);

	public void switchToFrameByXpath(By xpath);

	public void closeBrowser();

	public void closeAllBrowsers();

	public String getTestData(String property) throws InvocationTargetException;

}
