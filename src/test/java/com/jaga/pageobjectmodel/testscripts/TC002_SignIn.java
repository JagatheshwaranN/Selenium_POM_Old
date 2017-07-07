/**
 * The below class is created to test the SignIn Functionality
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
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.pageobjectmodel.excelreader.Excel_Reader;
import com.jaga.pageobjectmodel.pagelibrary.SignIn;
import com.jaga.pageobjectmodel.testbase.Common_Functions;

/**
 * A class is created with name : TC002_SignIn
 * TC002_SignIn class extends Common_Functions class to utilize the reusable methods of the Common_Functions class
 *
 * @author Jagatheshwaran
 */
public class TC002_SignIn extends Common_Functions {

	/**
	 * Initializing Logger
	 * SignIn class Object reference is initialized
	 */
	static Logger logger = Logger.getLogger(TC002_SignIn.class.getName());
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
	public Object[][] getData(String excelName, String testcase) {
		Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir") + "//src//test//resources//testdata//" + excelName);
		int rowNum = Data.getRowCount(testcase);
		int colNum = Data.getColumnCount(testcase);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
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
	public Object[][] signinData() {
		Object[][] data = getData("TestData.xlsx", "SignIn");
		return data;
	}

	/**
	 * This method will used to perform the Test of SignIn Functionality
	 * This method will fetch the data for the method from dataProvider
	 * @param dataProvider
	 * @param RunMode,TestCase,UserName,Password
	 * 
	 * @author Jagatheshwaran 
	 */
	@Test(dataProvider = "signinData")
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
	public void close() {
		closeBrowser();

	}

}
