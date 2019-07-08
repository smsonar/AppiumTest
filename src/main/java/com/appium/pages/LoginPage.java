package com.appium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.appium.base.BasePage;
import com.appium.utils.Utility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * 
 * @Description : This is a LoginPage class which contains LoginPage elements
 *              and there respective methods Implemented using page object and page
 *              factory
 * 
 * @author sagar.sonar
 *
 */
public class LoginPage extends BasePage {
	AndroidDriver<MobileElement> driver;

	@AndroidFindBy(id = "org.wordpress.android:id/nux_username")
	public WebElement userNameInput;

	@AndroidFindBy(id = "org.wordpress.android:id/nux_password")
	public WebElement passwordInput;

	@AndroidFindBy(id = "org.wordpress.android:id/nux_create_account_button")
	public WebElement createAccountButton;

	@AndroidFindBy(id = "org.wordpress.android:id/nux_sign_in_button")
	public MobileElement nextButton;

	private By nextButton1 = MobileBy.AccessibilityId("org.wordpress.android:id/nux_sign_in_button");

	@AndroidFindBy(id = "org.wordpress.android:id/password_layout")
	public WebElement passwordLayout;

	@AndroidFindBy(id = "org.wordpress.android:id/nux_dialog_title")
	public WebElement loginValidationMessage;

	private static Logger log = Logger.getLogger(LoginPage.class.getClass());

	public LoginPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * @param UserName
	 * @param Password
	 * @param child    ExtentReport
	 * @throws InterruptedException
	 */
	public void signIn(String UserName, String Password, ExtentTest child) throws InterruptedException {
		// mobileActions.verticalSwipeByPercentages(0.6, 0.3,0.5);
		enterUserName(UserName);
		child.log(LogStatus.INFO, "User has enterd UserName");
		nextButton.click();
		child.log(LogStatus.INFO, "User has clicke on Next Button");
		enterPassword(Password);
		child.log(LogStatus.INFO, "User has enterd password");
		nextButton.click();
		child.log(LogStatus.INFO, "User has clicke on Next Button");
	}

	public void enterUserName(String UserName) {
		Assert.assertTrue(userNameInput.isDisplayed());
		userNameInput.clear();
		Utility.enterText(userNameInput, UserName);
		log.info("Enterd UserName");
	}

	public void enterPassword(String Password) {
		Assert.assertTrue(passwordInput.isDisplayed());
		passwordInput.clear();
		Utility.enterText(passwordInput, Password);
		log.info("Enterd Password");
	}
}
