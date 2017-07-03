package com.jaga.pageobjectmodel.pagelibrary;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.jaga.pageobjectmodel.testbase.Common_Functions;

public class CreateAnAccount 
{
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
	By City =  By.name("city");
	By State = By.name("state");
	By Country = By.name("country");
	By TelPhoneNo = By.name("telephone");
	By Password = By.name("password");
	By ConfirmPassword = By.name("confirmation");
	By Submit = By.id("tdb4");
	
	
	

	
	public void createAccountRegistration() throws InvocationTargetException
	{
		
		String EmailAddress = cf.getTestData("CreateAccAddrEmail");
		String companyName = cf.getTestData("CreateAccAddrCompany");
		String AccPassword = cf.getTestData("CreateAccPassword");
		String AccConfirmPassword = cf.getTestData("CreateAccConfirmPassword");
		
		try
		{
		logger.info("The Registration details for the Create Account");
		
		cf.clickByXpath(CreateAccContinueBtn);
		cf.clickByXpath(Male);
		cf.enterTextByName(FirstName, "John");
		cf.enterTextByName(LastName, "Smith");
		cf.enterTextById(DateofBirth, "08/29/1991");
		cf.enterTextByName(Email,EmailAddress);
		cf.enterTextByName(Company, companyName);
		cf.enterTextByName(Address, "Sholinganallur");
		cf.enterTextByName(Zipcode, "600119");
		cf.enterTextByName(City, "Chennai");
		cf.enterTextByName(State, "TamilNadu");
		cf.selectVisibleTextByName(Country, "India");
		cf.enterTextByName(TelPhoneNo, "1234567890");
		cf.enterTextByName(Password, AccPassword);
		cf.enterTextByName(ConfirmPassword, AccConfirmPassword);
		cf.clickById(Submit);
		
		Thread.sleep(5000);
		
		logger.info("The Account has been Created and Registered Successfully");	
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
