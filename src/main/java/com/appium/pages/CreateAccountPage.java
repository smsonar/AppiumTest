package com.appium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.base.BasePage;
import com.appium.utils.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;

/**
 * 
 * @Description : This is a CreateAccountPage class which contains Create
 *              Account page elements and there respective methods Implemented
 *              page object and page factory
 * 
 * @author sagar.sonar
 *
 */
public class CreateAccountPage extends BasePage {
	@AndroidFindBy(id = "org.wordpress.android:id/email_address")
	public WebElement emailAddress;

	@AndroidFindBy(id = "org.wordpress.android:id/username")
	public WebElement userName;

	@AndroidFindBy(id = "org.wordpress.android:id/password")
	public WebElement password;

	@AndroidFindBy(id = "org.wordpress.android:id/site_url")
	public WebElement blogAddress;

	@AndroidFindBy(id = "org.wordpress.android:id/signup_button")
	public WebElement createAccountButton;
	
	@AndroidFindBy(id = "org.wordpress.android:id/create_account_label")
	public WebElement createAccountLable;

	public CreateAccountPage(AndroidDriver mobileDriver) {
		super(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
	}
	
	public void isAT() {
		Assert.assertTrue(createAccountLable.getText().contains("Create an account"));
	}
	public void createAccount(ExtentTest child) {
		String emailId=Utility.generateEmail();
		emailAddress.sendKeys(emailId);
		child.log(LogStatus.INFO, "User has enterd emailAddress");
		userName.sendKeys("TestUser");
		child.log(LogStatus.INFO, "User has enterd UserName");
		password.sendKeys("Pass@12345");
		child.log(LogStatus.INFO, "User has enterd Password");
		blogAddress.sendKeys("testuser");
		child.log(LogStatus.INFO, "User has enterd BlogAddress");
		createAccountButton.click();
		child.log(LogStatus.INFO, "User has clicked on Create Account Button");
	}
}
