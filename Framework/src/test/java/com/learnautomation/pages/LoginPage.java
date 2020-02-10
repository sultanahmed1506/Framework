package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver ldriver) {
		// This is new commit from Sultan
		this.driver = ldriver;
	}

	@FindBy(xpath = "//input[@placeholder='E-mail address']")
	WebElement e_mail;
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement pass;
	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement loginButton;

	public void loginToCRM(String emailApplication, String passwordApplication) {
		e_mail.sendKeys(emailApplication);
		pass.sendKeys(passwordApplication);
		loginButton.click();
	}
}
