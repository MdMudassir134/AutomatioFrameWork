/**
 * 
 */
package com.mystore.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreatedPage;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.CheckOutPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginSignUpPage;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.PaymentSuccessfullPage;
import com.mystore.pageobjects.SignUpPage;
import com.mystore.utility.Log;

/**
 * 
 */
public class PlaceOrderTest extends BaseClass {
	HomePage homePage;
	CartPage cartPage;
	LoginSignUpPage loginSignUpPage;
	SignUpPage signUpPage;
	AccountCreatedPage accountCreatedPage;
	CheckOutPage checkOutPage;
	PaymentPage paymentPage;
	PaymentSuccessfullPage paymentSuccessfullPage;

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();

	}

	@Test(description = "Test Case- 14 Place an order",groups= {"regression","sanity"})
	public void placeOrder() {
		Log.startTestCase("placeOrder");
		homePage = new HomePage();
		Log.startTestCase("placeOrder");
		Assert.assertTrue(homePage.verifyHomePageVisible());
		Log.info("Verified Home page is Visible");
		homePage.mouseHoverAddtoCartContinueShopping(3);
		homePage.mouseHoverAddtoCartContinueShopping(4);
		Log.info("Added 2 products to cart");
		cartPage = homePage.clickOnCartButton();
		Log.info("Clicked on cart");
		Assert.assertTrue(cartPage.verifySubscriptionBoxIsVisible());
		Log.info("Verified Cart Page is Visible");
		cartPage.clickOnProceedToCheckOut();
		Log.info("Clicked on Proceed to checkout Button");
		Log.info("Clicking on register/Login from PopUp");
		loginSignUpPage = cartPage.clickOnLoginRegisterOnPopUp();
		Log.info("Filling user name and Email in SignUp section and clicking SignUp Button");
		signUpPage = loginSignUpPage.signUp("Mohammed", "MohammedaaTetsCase1ABC23@xyz.com");
		Assert.assertTrue(signUpPage.verifyEmailNamePopulatedInSignUpForm());
		Log.info("Verified name and emial is populated");
		accountCreatedPage = signUpPage.signUp();
		Log.info("Filling SignUp Form and creating account");
		Assert.assertTrue(accountCreatedPage.validateAccountCreated());
		Log.info("Validated Account is created");
		homePage = accountCreatedPage.clickOnContinueButton();
		Log.info("Clicked on continue Button");
		assertTrue(homePage.verifyHomePageVisible());
		Log.info("Verified Home Page is visible");
		Assert.assertTrue(homePage.verfyCreatedUserIsVisible());
		Log.info("Verified Created User is Visible");
		cartPage = homePage.clickOnCartButton();
		Log.info("Clicked on Cart");
		checkOutPage = cartPage.clickOnProceedToCheckWithUserLoggedIn();
		Log.info("Clicked on Proceed to checkOut button");
		Assert.assertTrue(checkOutPage.verifyBillingAddressIsVisible());
		Log.info("Billing Address is Visible");
		Assert.assertTrue(checkOutPage.verifyDeliveryAddressIsVisible());
		Log.info("Delivery Addresss is Visible");
		Assert.assertTrue(checkOutPage.verifyReviewOrderIsVisible());
		checkOutPage.enterCommentForOrder("This is a test Comment");
		Log.info("Comments added for order");
		paymentPage = checkOutPage.clickOnPlaceOrderBtn();
//		Log.info("Clicked on Place Order button");
//		paymentPage.fillPaymentDetails();
//		Log.info("Payment details filled");
//		paymentSuccessfullPage = paymentPage.clickOnPayAndConfirmButton();
//		System.out.println(paymentPage.verifySucessfullMsg());
//		Assert.assertEquals(paymentPage.verifySucessfullMsg(), "Your order has been placed successfully!");

	}

}
