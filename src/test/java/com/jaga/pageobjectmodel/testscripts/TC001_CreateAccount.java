package com.jaga.pageobjectmodel.testscripts;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.pageobjectmodel.excelreader.Excel_Reader;
import com.jaga.pageobjectmodel.pagelibrary.CreateAnAccount;
import com.jaga.pageobjectmodel.testbase.Common_Functions;

public class TC001_CreateAccount extends Common_Functions {

	Common_Functions cf;
	CreateAnAccount acc;

	@BeforeMethod
	public void setup() throws IOException {
		init();
	}

	public Object[][] getData(String ExcelName, String testcase) {
		Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir") + "//src//test//resources//testdata//" + ExcelName);
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

	@DataProvider
	public Object[][] createAccountData() {
		Object[][] data = getData("TestData.xlsx", "CreateAccount");
		return data;
	}

	@Test(dataProvider = "createAccountData")
	public void createAccountRegistration(String RunMode, String TestCase, String FirstName, String LastName, String DateofBirth, String Email, String Company, String Address, String Zipcode, String City, String State, String Country, String TelPhoneNo, String Password, String ConfirmPassword)
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

	@AfterMethod
	public void close() {
		closeBrowser();
	}

}
