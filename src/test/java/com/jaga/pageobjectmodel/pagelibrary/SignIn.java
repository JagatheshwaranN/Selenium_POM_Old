package com.jaga.pageobjectmodel.pagelibrary;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import com.jaga.pageobjectmodel.testbase.Common_Functions;


public class SignIn {

	static Logger logger = Logger.getLogger(SignIn.class.getName());
	Common_Functions cf = new Common_Functions();

	By Email = By.name("email_address");
	By Password = By.name("password");
	By SignIn = By.id("tdb5");
	
	public void SignInTOAccount(String UserName,String Passwords) throws InvocationTargetException {

		String EmailAddress = UserName;
		String AccPassword = Passwords;
		//String EmailAddress = cf.getTestData("CreateAccAddrEmail");
		//String AccPassword = cf.getTestData("CreateAccPassword");
		logger.info("Before Account SignIn");
		try {

			cf.enterTextByName(Email, EmailAddress);
			cf.enterTextByName(Password, AccPassword);
			cf.clickById(SignIn);
			
			logger.info("SignIn Successful");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
