package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.TestCasePage;
import com.mystore.utility.Log;

public class TestCaseTest extends BaseClass {
	HomePage homePage;
	TestCasePage testCasePage;
	
	@BeforeMethod
	public void setup() {
		launchApp();
		
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	@Test(description = "Test case 7 - Test Case Page",groups= {"regression"})
	public void verifyTestCasePage() {
		Log.startTestCase("verifyTestCasePage");
		homePage = new HomePage();
		Log.info("Verifying Home is Loaded");
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Assert.assertTrue(homePageLoad, "Home page failed to load");
		Log.info("Clicked on TestCase page");
		testCasePage = homePage.clickOnTestCase();
		Log.info("Verifying if Test Case page loaded");
		boolean testCaseLoad = testCasePage.verifyTestCasePageVisible();
		if(testCaseLoad) {
		String currentUrl = getDriver().getCurrentUrl();
		Log.info("Verifying if actual and expected url are same");
		Assert.assertEquals(currentUrl, "https://automationexercise.com/test_cases");	
		}
		Log.endTestCase("verifyTestCasePage");
	}
	
	

}
