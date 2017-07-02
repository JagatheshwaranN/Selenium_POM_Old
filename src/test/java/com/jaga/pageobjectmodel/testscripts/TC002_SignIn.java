package com.jaga.pageobjectmodel.testscripts;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jaga.pageobjectmodel.excelreader.Excel_Reader;
import com.jaga.pageobjectmodel.pagelibrary.SignIn;
import com.jaga.pageobjectmodel.testbase.Common_Functions;

public class TC002_SignIn extends Common_Functions {

	Common_Functions cf;
	SignIn sign;

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	public Object[][] getData(String ExcelName, String testcase) {
		Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir")
				+ "//src//test//resources//testdata//" + ExcelName);
		int rowNum = Data.getRowCount(testcase);
		System.out.println(rowNum);
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
	public Object[][] loginData() {
		Object[][] data = getData("TestData.xlsx", "SignIn");
		return data;
	}

	@Test(dataProvider = "loginData")
	public void testSignIn(String RunMode, String TestCase, String UserName,
			String Passwords) throws InterruptedException, IOException {
		try {
			if (RunMode.equals("N")) {
				throw new SkipException("Skipping the test");
			}
			sign = new SignIn();
			sign.SignInTOAccount(UserName, Passwords);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testSignIn() throws InterruptedException, IOException {
		try {
			sign = new SignIn();
			// sign.SignInTOAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void close() {
		closeBrowser();
	}

}
