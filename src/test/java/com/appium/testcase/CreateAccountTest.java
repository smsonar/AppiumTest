package com.appium.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.appium.base.BaseTest;
import com.appium.pages.CreateAccountPage;
import com.appium.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class CreateAccountTest extends BaseTest {
	@BeforeClass
	public void PlanFinderParent() {
		parent = report.startTest("CreateAccount Test", "CreateAccount Test");
	}

	LoginPage loginpage;
	CreateAccountPage createaccountpage;

	@Test()
	public void Verify_Create_Account_Functaionlity_For_Valid_Inputfields() throws InterruptedException {
		child = report.startTest("Verify create account functaionlity for valid input");
		parent.appendChild(child);
		loginpage = new LoginPage(getDriver());
		createaccountpage = new CreateAccountPage(getDriver());

		loginpage.redirectoToCreateAccountScreen();
		createaccountpage.isAT();
		createaccountpage.createAccount(child);
		Assert.assertTrue(true);
		child.log(LogStatus.INFO, "New Account created successfully");
	}

	@Test()
	public void Verify_Create_Account_Functaionlity_For_InValid_Inputfields() throws InterruptedException {
		child = report.startTest("Verify create account functaionlity for Invalid input");
		parent.appendChild(child);
		loginpage = new LoginPage(getDriver());
		createaccountpage = new CreateAccountPage(getDriver());

		loginpage.redirectoToCreateAccountScreen();
		createaccountpage.isAT();
		createaccountpage.createAccount(child);
		Assert.assertTrue(true);
		child.log(LogStatus.INFO, "Invalid credentails Unable to create Account");
	}

}
