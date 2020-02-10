package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;

public class LoginTestCRM extends BaseClass {

	@Test
	public void LoginApp() {

		logger = report.createTest("Login To CRM");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// logger.info("");
		logger.info("Starting Application");
		loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));

		logger.pass("Login Succesful");

	}

	@Test
	public void LoginApp1() {

		logger = report.createTest("Logout");

		logger.fail("Logout Failed");

	}

}
