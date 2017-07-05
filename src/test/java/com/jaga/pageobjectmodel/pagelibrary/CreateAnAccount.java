package com.jaga.pageobjectmodel.pagelibrary;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.jaga.pageobjectmodel.testbase.Common_Functions;

public class CreateAnAccount {
	static WebDriver driver;
	static Logger logger = Logger.getLogger(CreateAnAccount.class.getName());
	Common_Functions cf = new Common_Functions();

	By CreateAccContinueBtn = By.xpath(".//*[@id='tdb4']//*[text()='Continue']");
	By Male = By.xpath(".//*[@name='gender'][1]");
	By FirstName = By.name("firstname");
	By LastName = By.name("lastname");
	By DateofBirth = By.id("dob");
	By Email = By.name("email_address");
	By Company = By.name("company");
	By Address = By.name("street_address");
	By Zipcode = By.name("postcode");
	By City = By.name("city");
	By State = By.name("state");
	By Country = By.name("country");
	By TelPhoneNo = By.name("telephone");
	By Password = By.name("password");
	By ConfirmPassword = By.name("confirmation");
	By Submit = By.id("tdb4");
	By SuccessMsg = By.xpath("//*[@class='grid_16 push_4']/h1");
	By AccountCreationError = By.xpath("//*[@class='messageStackError']//td");

	public void createAccountRegistration(String firstName, String lastName, String dateofBirth, String email, String company, String address, String zipcode, String city, String state, String country, String telPhoneNo, String password, String confirmPassword) throws InvocationTargetException {
		String ExpectedUrl = cf.getTestData("SuccessUrl");
		System.out.println(ExpectedUrl);

		try {
			logger.info("The Registration Details for the Create Account");

			cf.clickByXpath(CreateAccContinueBtn);
			cf.clickByXpath(Male);
			cf.enterTextByName(FirstName, firstName);
			cf.enterTextByName(LastName, lastName);
			cf.enterTextById(DateofBirth, dateofBirth);
			cf.enterTextByName(Email, email);
			cf.enterTextByName(Company, company);
			cf.enterTextByName(Address, address);
			cf.enterTextByName(Zipcode, zipcode);
			cf.enterTextByName(City, city);
			cf.enterTextByName(State, state);
			cf.selectVisibleTextByName(Country, country);
			cf.enterTextByName(TelPhoneNo, telPhoneNo);
			cf.enterTextByName(Password, password);
			cf.enterTextByName(ConfirmPassword, confirmPassword);
			cf.clickById(Submit);
			System.out.println(cf.getCurrentUrl());
			String ActualUrl = cf.getCurrentUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				logger.info("The Account has been Registered and Created Successfully");
				logger.info(cf.getTextByXpath(SuccessMsg));
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			} else {
				logger.info("The Account has not been Registered and Created Successfully");
				logger.info(cf.getTextByXpath(AccountCreationError));
				Assert.assertEquals(ActualUrl, ExpectedUrl);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
