package com.jaga.pageobjectmodel.testscripts;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jaga.pageobjectmodel.pagelibrary.CreateAnAccount;
import com.jaga.pageobjectmodel.testbase.Common_Functions;

public class TC001_CreateAccount extends Common_Functions {

	Common_Functions cf;
	CreateAnAccount acc;

	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@Test
	public void testCreateAccount() throws InterruptedException, IOException {
		try {

			acc = new CreateAnAccount();
			acc.createAccountRegistration();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void close() {
		closeBrowser();
	}

}
