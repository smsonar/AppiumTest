package com.appium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.appium.base.BasePage;
import com.appium.utils.MobileActions;
import com.appium.utils.Utility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HelpPage extends BasePage {
	AndroidDriver<MobileElement> driver;
	MobileActions mobileaction = new MobileActions(driver);
	private static Logger log = Logger.getLogger(LoginPage.class.getClass());
	
	@AndroidFindBy(id = "org.wordpress.android:id/applog_button")
	public WebElement applicationLogButton;
	
	
	public HelpPage(AndroidDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void VerifyVerticalScroll() throws InterruptedException {
		Assert.assertTrue(applicationLogButton.isDisplayed());
		Utility.clickButton(applicationLogButton);
		log.info("clicked on Applicaton Log Button");
		Thread.sleep(3000);
		mobileActions.verticalSwipeByPercentages(0.8, 0.2,0.5);
	}
}
