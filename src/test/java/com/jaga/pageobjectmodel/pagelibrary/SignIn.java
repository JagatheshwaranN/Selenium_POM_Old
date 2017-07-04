package com.jaga.pageobjectmodel.pagelibrary;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import junit.framework.AssertionFailedError;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.jaga.pageobjectmodel.testbase.Common_Functions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SuppressWarnings("unused")
public class SignIn extends Common_Functions {

	static Logger logger = Logger.getLogger(SignIn.class.getName());
	Common_Functions cf = new Common_Functions();

	By Email = By.name("email_address");
	By Password = By.name("password");
	By SignIn = By.id("tdb5");
	By Greeting = By.xpath(".//*[@id='bodyContent']//*[@class='greetUser']");
	By SignInError = By.xpath(".//*[@class='messageStackError']//td[1]");

	public void SignInTOAccount(String userName, String password) throws InvocationTargetException {

		
		String ActualUrl = cf.getTestData("SignInUrl");

		try {
			logger.info("Before SignIn into Account");
			cf.enterTextByName(Email, userName);
			cf.enterTextByName(Password, password);
			cf.clickById(SignIn);
			
			String ExpectedUrl = cf.getCurrentUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				logger.info("SignIn into Account Successful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			} else {
				logger.info("SignIn into Account Unsuccessful");
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
