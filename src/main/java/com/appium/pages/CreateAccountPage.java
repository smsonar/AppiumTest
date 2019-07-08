package com.appium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

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

	public CreateAccountPage(AndroidDriver mobileDriver) {
		super(mobileDriver);
		PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
	}

}
