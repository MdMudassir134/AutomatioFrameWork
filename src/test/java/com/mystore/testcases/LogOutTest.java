/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.utility.Log;




/**
 * 
 */
public class LogOutTest extends BaseClass {
	HomePage homePage;
	LoginSignUpPage loginSignUpPage;
	
	@BeforeMethod
	public void setUp() {
		launchApp();
		//before logout test cases logging
		homePage=new HomePage();
		loginSignUpPage = homePage.clickOnSignUpSingInButton();
	    homePage=loginSignUpPage.signIn(prop.getProperty("email"),prop.getProperty("password"));
	    boolean homePageLoad=homePage.verifyHomePageVisible();		
	    Assert.assertTrue(homePageLoad, "After login homepage load fail");
	    Log.info("Login was successfull from setup, starting test logout");
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(priority =1, description = "LogOut testCase test case4",groups= {"regression"})
	public void logout() {
		homePage =new HomePage();
		//verifying if user is logged-in
		Log.startTestCase("logout");
		boolean userLoggedin=homePage.verfyCreatedUserIsVisible();
		Assert.assertTrue(userLoggedin, "There is no user logged-in..!");
		Log.info("Verifyied-User is Logged In");
		loginSignUpPage = homePage.clickOnLogoutButton();
		Log.info("LogOut button clicked");
		boolean loginSignUpPageLoad=loginSignUpPage.verifyLoginAccountIsVisible();
		Log.info("verifying that account got logged out and we are at signUpSignIn Page");
		Assert.assertTrue(loginSignUpPageLoad, "Failed to logged out");
		Log.endTestCase("logout");
		
	}

}
