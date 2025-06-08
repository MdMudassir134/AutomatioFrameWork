/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.utility.Log;

/**
 * 
 */
public class HomeTest extends BaseClass {
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		launchApp();
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	@Test(description = "test case 10",groups= {"regression"})
	public void homePageSubscription() {
		Log.startTestCase("homePageSubscription");
		homePage= new HomePage();
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Log.info("Verifying HomePage is loaded");
		Assert.assertTrue(homePageLoad);
		Log.info("scrolling to bottom of page");
		Action.scrollDownToFotterOfPage(getDriver());
		boolean subscriptionLoad = homePage.verifySubscriptionBoxIsVisible();
		Log.info("verifying subscription box is visible");
		Assert.assertTrue(subscriptionLoad);
		Log.info("passing emailid from config  and clicking submit button");
		String expectedMessage = homePage.submitSubscriptionWithEmail(prop.getProperty("email"));
		Log.info(expectedMessage);
		Log.info("Validating expected and actual message");
		Assert.assertEquals(expectedMessage, "You have been successfully subscribed!");
		Log.endTestCase("homePageSubscription");
		
		
	}
	
	

}
