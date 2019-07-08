package com.appium.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.base.BaseTest;

import io.appium.java_client.MobileDriver;

/**
 * Description: Utility class contains all common methods which are available
 * throughout the project
 * 
 * @author sagar.sonar
 *
 */
public class Utility extends BaseTest {
	// private static AndroidDriver<MobileElement> driver;

	// This method will be used to enter text in given input field
	public static void enterText(WebElement element, String text) {
		waitUntilDisplayed(element);
		element.sendKeys(text);

	}

	// This method will be used for synchronization purpose
	public static void waitUntilDisplayed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(BaseTest.getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// This method will be used to click on button
	public static void clickButton(WebElement element) {
		waitUntilDisplayed(element);
		element.click();
	}

	public static void waitUntilEnabled(WebElement element) {

	}

	public static String getTimeStamp() {
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String timeStamp = format.format(new Date());
		return timeStamp;
	}
}
