/**
 * The below class is created to perform SignIn Functionality
 *
 * @author Jagatheshwaran
 */

/**
 * Importing Package
 */
package com.jaga.pageobjectmodel.pagelibrary;

/**
 * Importing the necessary predefined classes
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//import java.util.logging.Logger;
import org.apache.log4j.*;

import junit.framework.AssertionFailedError;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.jaga.pageobjectmodel.testbase.Common_Functions;

/**
 * A class is created with name : SignIn
 * SignIn class extends Common_Functions class to utilize the reusable methods of the Common_Functions class
 *
 * @author Jagatheshwaran
 */
@SuppressWarnings("unused")
public class SignIn extends Common_Functions {
	
	/**
	 * Initializing Logger
	 * Common_Functions class Object is initialized
	 */
	static Logger logger = Logger.getLogger(SignIn.class.getName());
	Common_Functions cf = new Common_Functions(); 
	
	
	/**
	 * Declaring all the Page object locators using By class
	 * 
	 * @author Jagatheshwaran
	 */
	By Email = By.name("email_address");
	By Password = By.name("password");
	By SignIn = By.id("tdb5");
	By Greeting = By.xpath(".//*[@id='bodyContent']//*[@class='greetUser']");
	By SignInError = By.xpath(".//*[@class='messageStackError']//td[1]");

	/**
	 * This method will enter the credentials and then click the SignIn button
	 * 
	 * @author Jagatheshwaran
	 * @throws IOException 
	 */
	public void SignInTOAccount(String userName, String passWord) throws InvocationTargetException {

		
		//The Test Data fetched from the property file
		String ExpectedUrl = cf.getTestData("SignInUrl");
		

		try {
			
			logger.info("Before SignIn into Account");
			cf.enterTextByName(Email, userName);
			cf.enterTextByName(Password, passWord);
			cf.clickById(SignIn);		
			logger.info("After SignIn into Account");
			
			String ActualUrl = cf.getUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				logger.info("SignIn into Account is Successful");
				logger.info(cf.getTextByXpath(Greeting));
				Assert.assertEquals(ActualUrl, ExpectedUrl);
			} else {
				logger.info("SignIn into Account is Unsuccessful");
				logger.info(cf.getTextByXpath(SignInError));
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
