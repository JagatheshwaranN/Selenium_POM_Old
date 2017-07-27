/**
 * The below class is created to test all the Functionalities
 *
 * @author Jagatheshwaran
 */

/**
 * Importing Package
 */
package com.jaga.pageobjectmodel.testscripts;

/**
 * Importing the necessary predefined classes
 */
import java.io.IOException;
import java.util.logging.Logger;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.pageobjectmodel.excelreader.Excel_Reader;
import com.jaga.pageobjectmodel.pagelibrary.CreateAnAccount;
import com.jaga.pageobjectmodel.pagelibrary.SignIn;
import com.jaga.pageobjectmodel.testbase.Common_Functions;


/**
 * A class is created with name : TestCaseExecutionScript
 * TestCaseExecutionScript class extends Common_Functions class to utilize the reusable methods of the Common_Functions class
 *
 * @author Jagatheshwaran
 */
public class TestCaseExecutionScript extends Common_Functions {
	
	/**
	 * Initializing Logger
	 * CreateAnAccount class Object reference is initialized
	 */
	static Logger logger = Logger.getLogger(TestCaseExecutionScript.class.getName());
	CreateAnAccount acc;
	SignIn sign;

	
	/**
	 * This method will used to perform the entry operations of the Test 
	 * This method will run before each methods under Test
	 * 
	 * @author Jagatheshwaran 
	 */
	@BeforeMethod
	public void setup() throws IOException {
		init();

	}

	/**
	 * This method will used to fetch the data from the Excel sheet
	 * @param excelName
	 * @param testcase
	 * 
	 * @author Jagatheshwaran 
	 */
	public Object[][] getData(String excelName, String sheet) {
		Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir") + "//src//test//resources//testdata//" + excelName);
		int rowNum = Data.getRowCount(sheet);
		int colNum = Data.getColumnCount(sheet);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(sheet, j, i);
			}
		}
		return sampleData;
	}

	/**
	 * This method will used to get the data from Excel sheet and store it in object 
	 * 
	 * @author Jagatheshwaran 
	 */
	@DataProvider
	public Object[][] createAccountData() {
		Object[][] data = getData("TestData.xlsx", "CreateAccount");
		return data;
	}
	
	/**
	 * This method will used to perform the Test of CreateAccount Functionality
	 * This method will fetch the data for the method from dataProvider
	 * @param dataProvider
	 * @param RunMode,TestCase,FirstName,LastName,DateofBirth,Email,Company,Address,Zipcode,City,State,Country,TelPhoneNo,Password,ConfirmPassword
	 * 
	 * @author Jagatheshwaran 
	 */
	@Test(dataProvider = "createAccountData")
	public void testcreateAccountRegistration(String RunMode, String TestCase, String FirstName, String LastName, String DateofBirth, String Email, String Company, String Address, String Zipcode, String City, String State, String Country, String TelPhoneNo, String Password, String ConfirmPassword)
			throws InterruptedException, IOException {
		try {
			if (RunMode.equals("N")) {
				throw new SkipException("Skipping the test");
			}
			acc = new CreateAnAccount();
			acc.createAccountRegistration(FirstName, LastName, DateofBirth, Email, Company, Address, Zipcode, City, State, Country, TelPhoneNo, Password, ConfirmPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will used to get the data from Excel sheet and store it in object
	 * 
	 * @author Jagatheshwaran
	 */
	@DataProvider
	public Object[][] signinData() {
		Object[][] data = getData("TestData.xlsx", "SignIn");
		
		return data;
	}

	/**
	 * This method will used to perform the Test of SignIn Functionality
	 * This method will fetch the data for the method from dataProvider
	 * This method will run after the testcreateAccountRegistration method
	 * 
	 * @param dataProvider
	 * @param RunMode,TestCase,UserName,Password
	 * 
	 * @author Jagatheshwaran
	 */
	@Test(dataProvider = "signinData",dependsOnMethods = {"testcreateAccountRegistration"})
	public void testSignIn(String RunMode, String TestCase, String UserName, String Password) throws InterruptedException, IOException {
		try {
			if (RunMode.equals("N")) {
				throw new SkipException("Skipping the test");
			}

			sign = new SignIn();
			sign.SignInTOAccount(UserName, Password);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will used to perform the exit operations of the Test 
	 * This method will run after each methods under Test
	 * 
	 * @author Jagatheshwaran 
	 */
	@AfterMethod
	public void teardown() {
		closeBrowser();
		
	}
	
	
}
