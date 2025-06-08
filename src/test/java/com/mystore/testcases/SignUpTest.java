package com.mystore.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreatedPage;
import com.mystore.pageobjects.DeleteAccountPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.pageobjects.SignUpPage;
import com.mystore.utility.Log;

public class SignUpTest extends BaseClass {
	HomePage homePage;
	LoginSignUpPage loginSignUpPage;
	SignUpPage signUpPage;
	AccountCreatedPage accountCreatedPage;
	DeleteAccountPage deleteAccountPage;
	
	@BeforeMethod
	public void setUp() {
		launchApp();
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	@Test(priority = 1, description = " test case1 create a user and delete account TestCase1",groups= {"regression","sanity"})
	public void registerUser() {
		Log.startTestCase("registerUser");
		//verify page is loaded
		homePage=new HomePage();
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Log.info("verifying the home page is visiable");
		Assert.assertTrue(homePageLoad);
		
		Log.info("LoginSignup button clicked");
		loginSignUpPage=homePage.clickOnSignUpSingInButton();
		boolean signUpPageload = loginSignUpPage.verifyNewUserSignUpIsVisible();
		Log.info("Verifying if the signup page is loadded");
		Assert.assertTrue(signUpPageload);
		
		//enter name email click singup button, signup form load
		signUpPage =loginSignUpPage.signUp(prop.getProperty("signupname"), prop.getProperty("signupEmail"));
		Log.info("Entered username and email in textbox and signup button is clicked");
		
		boolean nameEmailPopulated = signUpPage.verifyEmailNamePopulatedInSignUpForm();
		Log.info("verifying if previously entered email and name is populated on form");
		Assert.assertTrue(nameEmailPopulated);
		
		
		accountCreatedPage = signUpPage.signUp();
		Log.info("Form filled and checkbox clicked and create account button is clicked");
		boolean UserAccountCreated=accountCreatedPage.validateAccountCreated();
		Log.info("Validating if account got created");
		Assert.assertTrue(UserAccountCreated);
		
		
		homePage = accountCreatedPage.clickOnContinueButton();
		Log.info("continue button clicked");
		boolean validateuser = homePage.verfyCreatedUserIsVisible();
		Log.info("Verifying is new user is showing in homepage");
		Assert.assertTrue(validateuser);
		deleteAccountPage = homePage.clickOnDeleteAccountButton();
		Log.info("Account deletion button clicked");
		deleteAccountPage.clickOnContinueButton();
		Log.endTestCase("registerUser");
			
	}
	
	
	

}
