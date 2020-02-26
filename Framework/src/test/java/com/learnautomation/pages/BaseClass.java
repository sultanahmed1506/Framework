package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {

		Reporter.log("Setting up reports and Test getting started ", true);
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		// Extent Report
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("User", "Sultan");
		report.attachReporter(extent);

		Reporter.log("Setting done and Test can be started ", true);

	}

	@BeforeClass
	public void setup() {
		Reporter.log("Starting Browser and getting application ready", true);
		driver = BrowserFactory.startApplcation(driver, config.getBrowser(), config.getStagingURL());
		Reporter.log("Browser and Application is up and running", true);
	}

	@AfterClass
	public void tearDown() {

		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult results) {
		Reporter.log("Test is about to end", true);
		if (results.getStatus() == ITestResult.FAILURE) {
			// Take screenshot
			Helper.captureScreenshots(driver);
			try {
				logger.fail("Test Failed ",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
			} catch (IOException e) {
				System.out.println("Unable to capture screenshot" + e.getMessage());
			}
		} else if (results.getStatus() == ITestResult.SUCCESS) {
			try {
				logger.pass("Test Passed ",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
			} catch (IOException e) {
				System.out.println("Unable to capture screenshot" + e.getMessage());
			}
		} else if (results.getStatus() == ITestResult.SKIP) {
			try {
				logger.skip("Test Skipped ",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
			} catch (IOException e) {
				System.out.println("Unable to capture screenshot" + e.getMessage());
			}
		}
		report.flush();
		Reporter.log("Test completed and Extent Reports generated", true);
	}
}
