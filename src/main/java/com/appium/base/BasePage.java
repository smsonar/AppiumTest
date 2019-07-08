package com.appium.base;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.utils.MobileActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * 
 * @Description : The BasePage class has implemented to define common methods
 *              used by other pages classes
 * 
 * @author sagar.sonar
 *
 */
public abstract class BasePage {
	public AndroidDriver mobileDriver;
	public WebDriverWait wait;
	public MobileActions mobileActions;
	Properties property;

	public BasePage(AndroidDriver driver) {
		this.mobileDriver = driver;
		mobileActions = new MobileActions(driver); // We are using MobileActions with this instance. Composition.
		wait = new WebDriverWait(driver, 20);
	}
}
