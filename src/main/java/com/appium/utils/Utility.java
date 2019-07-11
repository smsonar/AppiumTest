package com.appium.utils;

import java.io.File;
import java.io.FileFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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
     //Description : generating current time stamp
	public static String getTimeStamp() {
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String timeStamp = format.format(new Date());
		return timeStamp;
	}
	
	 //  @Description -generate random email id's.
	 
	public static String generateEmail() {
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		String randomEmail = new String();
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			randomEmail = randomEmail+ alphabet.charAt(r.nextInt(alphabet.length()));
		randomEmail = randomEmail + "@mailinator.com";
		return randomEmail;
	}
	// @Description : return a latest file from folder.
	
	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}
}
