package com.appium.testcase;

import org.testng.Assert;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.appium.base.BaseTest;
import com.appium.pages.HelpPage;
import com.appium.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

/**
 * This test class is defining login functionality with valid and invalid
 * credentials
 * 
 * @author sagar.sonar
 *
 *
 */
public class LoginTest extends BaseTest {
	@BeforeClass
	public void PlanFinderParent() {
		parent = report.startTest("Login Test", "Login Test");
	}

	LoginPage loginpage;
	HelpPage helppage;

	@Test()
	public void Verify_Login_Functionality_For_Valid_credentials() throws InterruptedException {
		child = report.startTest("Verify Login functaionality for valid credentials");
		parent.appendChild(child);
		loginpage = new LoginPage(getDriver());
		loginpage.signIn(property.getProperty("username"), property.getProperty("password"), child);
		Assert.assertEquals(true, false);
	}

	@Test()
	public void Verify_Login_Functionality_For_InValid_credentials() throws InterruptedException {
		child = report.startTest("Verify Login functaionality for Invalid credentials");
		parent.appendChild(child);
		loginpage = new LoginPage(getDriver());
		loginpage.signIn(property.getProperty("username"), property.getProperty("password"), child);
		Assert.assertEquals(false, false);
	}

	@Test()
	public void verify_Application_Log_With_Vertical_Scroll() throws InterruptedException {
		child = report.startTest("Verify application log");
		parent.appendChild(child);
		loginpage = new LoginPage(getDriver());
		loginpage.redirectToApplcationLog();
		helppage = new HelpPage(getDriver());
		helppage.VerifyVerticalScroll();
	}

}
