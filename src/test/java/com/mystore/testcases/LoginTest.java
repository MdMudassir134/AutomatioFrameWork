package com.mystore.testcases;



import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreatedPage;
import com.mystore.pageobjects.DeleteAccountPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.pageobjects.SignUpPage;

//import jdk.internal.org.jline.utils.Log;
import com.mystore.utility.Log;

public class LoginTest extends BaseClass {	
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
	
	@Test(description = "Test Case2 -login into a user account and delete account ",
			dataProvider = "loginCredentials",
			dataProviderClass=DataProviders.class,groups= {"regression","sanity"})
	public void Login(String email,String password) {
		homePage=new HomePage();
		Log.startTestCase("Login");
		boolean homePageLoad=homePage.verifyHomePageVisible();
		Log.info("Verifying if home page is visible");
		Assert.assertTrue(homePageLoad, "HomePage is not visible");
		loginSignUpPage = homePage.clickOnSignUpSingInButton();
		Log.info("clicked on SignupSignIn button");
		//homePage = loginSignUpPage.signIn(prop.getProperty("email"),prop.getProperty("password"));
		homePage = loginSignUpPage.signIn(email,password);
		deleteAccountPage = homePage.clickOnDeleteAccountButton();
		homePage = deleteAccountPage.clickOnContinueButton();
		homePageLoad = homePage.verifyHomePageVisible();
		Assert.assertTrue(homePageLoad, "Home is not visiable after account delete");
		Log.endTestCase("Login");
			
		
		
	}
	@Test(description = "Test Case3-Invalid login attempt ",
			dataProvider="invalidLoginCrendentials",
			dataProviderClass=DataProviders.class,groups= {"regression"})
	public void invalidLogin(String invalidEmail,String invalidPwd) {
		//login with email id that doesn't exist
		Log.startTestCase("invalidLogin");
		homePage=new HomePage();
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Log.info("Validating if HomePage is Visible");
		Assert.assertTrue(homePageLoad, "Home Page is not visible");
		loginSignUpPage = homePage.clickOnSignUpSingInButton();
		Log.info("SignupSignIn Button clicked");
		WebElement invalidMessage = loginSignUpPage.invalidSignIn(invalidEmail, invalidEmail);
		Log.info("Entered Email and password and signin button clicked");
		Assert.assertEquals(invalidMessage.getText(), "Your email or password is incorrect!");
		Log.info("Invalid username Password validated");
		Log.endTestCase("invalidLogin");
					
		
	}
	@Test(description ="Existing email signup testCase5",groups= {"regression"})
	public void existingEmailSignUp() {
		//signUp with email id already exist
		Log.startTestCase("existingEmailSignUp");
		homePage= new HomePage();
		homePage.verifyHomePageVisible();
		Log.info("Verified home page is visible");
		loginSignUpPage = homePage.clickOnSignUpSingInButton();
		Log.info("SingInSignUpButton clicked");
		WebElement inavlidMsg=loginSignUpPage.registerExistingUser(prop.getProperty("name"),prop.getProperty("email"));
		Assert.assertEquals(inavlidMsg.getText(), "Email Address already exist!","Failed this is a new emailid");
		Log.info("Invalid message verified");
		Log.endTestCase("existingEmailSignUp");
		
	}
	

}
