package com.mystore.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.ContactUsPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.utility.Log;
import com.mystore.utility.NewExcelLibrary;

public class ContactUsTest extends BaseClass {
	HomePage homePage;
	ContactUsPage contactUspage;
	NewExcelLibrary excelRead = new NewExcelLibrary();

	@BeforeMethod
	public void setup() {
		launchApp();
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}

	@Test(description ="Test Case 6- Contact us subscription",groups= {"regression","sanity"})
	public void contactUsFormSubmit() {
		Log.startTestCase("contactUsFormSubmit");
		homePage = new HomePage();

		Log.info("Verifying if home page is visible");
		boolean homePageLoad = homePage.verifyHomePageVisible();
		System.out.println(homePageLoad);
		Assert.assertTrue(homePageLoad);

		Log.info("Home page is visible");
		contactUspage = homePage.clickOnContactUsButton();
		boolean contactUsPageLoad = contactUspage.verifyGetInTouch();
		Assert.assertEquals(contactUsPageLoad, true);
		Log.info("Get in touch is visible");
				
		boolean contactUstext = contactUspage.verifyContactUsIsVisible();
		SoftAssert softassert = new SoftAssert();//calling soft assertion but not reporting failure with assertAll()
		softassert.assertTrue(contactUstext, "contact us is not visible");
		Log.info("Contact us heading is visible");
		//since its a single fetch not using data providers
		Log.info("Filling form");
		String name = excelRead.getCellData("ContactUs TestCase6", 1, 2);
		String email = excelRead.getCellData("ContactUs TestCase6", 2, 2);
		String subject = excelRead.getCellData("ContactUs TestCase6", 3, 2);
		String message = excelRead.getCellData("ContactUs TestCase6", 4, 2);
		String path = excelRead.getCellData("ContactUs TestCase6", 5, 2);
		System.out.println(name+email+subject+message);
		contactUspage.fillContactUsForm(name, email, subject, message);
		
		contactUspage.uploadFileInContactUsPage(path);
		Log.info("Form filled and file uploaded");
		contactUspage.clickOnSubmitBtn();
		HomePage returnedHomePage= contactUspage.ClickOnHomeButton();
		boolean flag =returnedHomePage.verifyHomePageVisible();
		Assert.assertEquals(flag,true);
		Log.endTestCase("contactUsFormSubmit");
		
	}
	

}
