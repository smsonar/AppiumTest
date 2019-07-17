package com.appium.base;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.appium.utils.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @Description :
 * 
 *              The BaseTest class has implemented as "setup" method with
 *              parameters(platform,udid,platformVersio) setup method is used to
 *              read the property file, driver initialization and reporting
 *              setup and screenshot functionality Implemented TestNG annotation
 *              like @BeforeTest,@AfterTest and @AfterNethod
 * 
 * @author sagar.sonar
 * 
 */

public class BaseTest {
	public static AndroidDriver mobileDriver;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	public static Properties property = new Properties();
	static String screnshotFileDest;
	public static String time;
	String timestamp = Utility.getTimeStamp();

	// Extent Report initialization
	public ExtentReports report;
	public ExtentTest parent;
	public ExtentTest child;

	@BeforeSuite
	public void initExtentReport() throws IOException {
		File f = new File((System.getProperty("user.dir") + "/report/ExtentReport.html"));
		if (f.exists()) {
			f.delete();
		}
		reportSetup();
		getPropertyFile();
	}

	@BeforeClass
	public void initReport1() {
		report = new ExtentReports(System.getProperty("user.dir") + "/report/ExtentReport.html", false);
	}

	@BeforeMethod

	/**
	 * @Description : setup method is used to read property file ,initialization of
	 *              driver and configuring extent report.
	 * 
	 * @param platform
	 * @param udid
	 * @param platformVersion
	 * @throws IOException
	 * @throws InterruptedException
	 */

	@Parameters({ "platform", "udid", "url", "platformVersion" })
	public void setup(String platform, String udid, String url, String platformVersion)
			throws IOException, InterruptedException {
		driverIntilization(platform, udid, url, platformVersion);
	}

	@AfterMethod
	public void getReport(ITestResult result) throws IOException {
		String screenshotPath = takeScreenshot();
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				child.log(LogStatus.FAIL, result.getThrowable());
				child.log(LogStatus.FAIL, child.addScreenCapture(screenshotPath));
				child.log(LogStatus.FAIL, "Test Case Failed is :" + result.getName());

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				child.log(LogStatus.PASS, "Pass Test Case is :" + result.getName());

			} else if (result.getStatus() == ITestResult.SKIP) {
				child.log(LogStatus.SKIP, "test Case skip is:- " + result.getName());

			} else if (result.getStatus() == ITestResult.STARTED) {
				child.log(LogStatus.INFO, "Test Case started");
			}
			quitBrowser();
			report.endTest(child);
		} catch (Exception es) {
			System.out.println("Report genration Excepion is:- " + es.getMessage());
		}
	}

	@AfterClass
	public void afterClass() {
		System.out.println("***************" + parent.getDescription() + "***************");
		report.endTest(parent);
		report.flush();
		report.close();
	}

	@AfterSuite
	/**
	 * 
	 * @Description :sendEmailableReport method is sending an emailable report
	 *              generated by TestNg. This method contains SMTP configuration.
	 *              generated an email with attachment using this configuration and
	 *              Delivering this email to configured "To_Mailaddress".
	 */
	public void sendEmailableReport() {

		// Create the attachment

		EmailAttachment attachment = new EmailAttachment();
		String file = System.getProperty("user.dir") + "//report//ExtentReport.html";
		attachment.setPath(file);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Mobile_Extent_Report");
		attachment.setName("Extent Report");

		// Create the email message

		MultiPartEmail email = new MultiPartEmail();
		email.setSubject("Mobile Automation Report " + Utility.getTimeStamp());
		email.setSslSmtpPort(property.getProperty("SMTP_Port"));
		email.setTLS(true);
		email.setHostName(property.getProperty("SMTP_Host"));
		email.setAuthentication(property.getProperty("SMTP_UserName"), property.getProperty("SMTP_User_Password"));
		try {
			email.addTo(property.getProperty("To_MailAddress"), property.getProperty("To_Name"));
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.setFrom(property.getProperty("From_MailAddress"), property.getProperty("From_Name"));
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// add the attachment
		try {
			email.attach(attachment);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sending");
		// send the email
		try {
			email.send();
			System.out.println("Done");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method will be closing all browser
	public void quitBrowser() {
		if (mobileDriver != null)
			mobileDriver.quit();
	}

	public static AndroidDriver<MobileElement> getDriver() {
		return mobileDriver;
	}

	public void getPropertyFile() throws IOException {
		property = new Properties();
		FileInputStream inputPropertyFile = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/appium/config/Config.property");
		property.load(inputPropertyFile);
	}

	/**
	 * @Description: This method is used to initialize the driver according to the
	 *               driver mentioned in configuration file.
	 * 
	 * @param platform
	 * @param udid
	 * @param platformVersion
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */

	public void driverIntilization(String platform, String udid, String url, String platformVersion)
			throws MalformedURLException, InterruptedException {

		if (platform.equalsIgnoreCase("android")) {
			capabilities.setCapability("deviceName", "Android Emulator");
			capabilities.setCapability(MobileCapabilityType.UDID, udid);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			capabilities.setCapability("automationName", "UIAutomator2");
			capabilities.setCapability("app", System.getProperty("user.dir") + "/build/wordpress.apk");
			capabilities.setCapability("appPackage", "org.wordpress.android");
			capabilities.setCapability("appActivity", "org.wordpress.android.ui.WPLaunchActivity");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("platformName", "android");
			mobileDriver = new AndroidDriver(new URL(url), capabilities);
		}

		else if (platform.equalsIgnoreCase("iOS")) {
			capabilities.setCapability("automationName", "XCuiTest");
			capabilities.setCapability("deviceName", "iPhone 7");
			capabilities.setCapability("platformVersion", "11.1");
			capabilities.setCapability("sendKeyStrategy", "grouped");
			capabilities.setCapability("app", "");
			Thread.sleep(2000);
		}
	}

	/**
	 * This method will setup the report and being called in setup() method
	 */
	public void reportSetup() {
		try {
			report.addSystemInfo("Host Name", "Extent Report").addSystemInfo("Environment", "Automation Testing")
					.addSystemInfo("UserName", "NitorAdmin");
			report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		} catch (Exception ex) {
			System.out.println("Issue is" + ex.getMessage());
		}
	}

	public String takeScreenshot() throws IOException {
		TakesScreenshot tc = (TakesScreenshot) mobileDriver;
		File src = tc.getScreenshotAs(OutputType.FILE);
		screnshotFileDest = System.getProperty("user.dir") + "\\Screenshot\\" + timestamp + ".png";
		File destination = new File(screnshotFileDest);
		FileUtils.copyFile(src, destination);
		return screnshotFileDest;
	}

}
