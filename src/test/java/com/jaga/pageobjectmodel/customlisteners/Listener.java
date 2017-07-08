/**
 * The below class is created to listen the Test and capture the screens of the Success or Failure of the Test
 *
 * @author Jagatheshwaran
 */

/**
 * Importing Package
 */
package com.jaga.pageobjectmodel.customlisteners;

/**
 * Importing the necessary predefined classes
 */
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.jaga.pageobjectmodel.testbase.Common_Functions;

/**
 * A class is created with name : Listener
 * Listener class extends Common_Functions class and implements ITestListener
 */
public class Listener extends Common_Functions implements ITestListener {

	/**
	 * This method will provide the start of the test in TestNG report
	 * 
	 * @author Jagatheshwaran
	 */
	@SuppressWarnings("static-access")
	public void onTestStart(ITestResult result) {
		Reporter.log("Test started running:" + result.getMethod().getMethodName() + " at:" + result.STARTED);
	}

	/**
	 * This method will capture the screen shot on Test Failure
	 * 
	 * @author Jagatheshwaran
	 */
	public void onTestFailure(ITestResult result) {
		if (!result.isSuccess()) {
			String userDirectory = System.getProperty("user.dir");
			String customLocation = "//src//test//resources//screenshots//failure//";
			String failureImageFileName = userDirectory + customLocation + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-" + result.getMethod().getMethodName() + ".png";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(failureImageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Reporter.log("<a href=\"" + failureImageFileName + "\"><img src=\"file:///" + failureImageFileName + "\" alt=\"\"" + "height='100' width='100'/> " + "<br />");
			Reporter.setCurrentTestResult(null);
			Reporter.log(result.getName() + "--Test method failed\n");

		}

	}

	/**
	 * This method will capture the screen shot on Test Success
	 * 
	 * @author Jagatheshwaran
	 */
	public void onTestSuccess(ITestResult result) {
		if (result.isSuccess()) {
			String userDirectory = System.getProperty("user.dir");
			String customLocation = "//src//test//resources//screenshots//success//";
			String failureImageFileName = userDirectory + customLocation + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + "-" + result.getMethod().getMethodName() + ".png";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(failureImageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Reporter.log("<a href=\"" + failureImageFileName + "\"><img src=\"file:///" + failureImageFileName + "\" alt=\"\"" + "height='100' width='100'/> " + "<br />");
			Reporter.setCurrentTestResult(null);
			Reporter.log(result.getName() + "--Test method failed\n");

		}
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
