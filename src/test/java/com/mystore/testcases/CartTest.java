package com.mystore.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.ProductDetailsPage;
import com.mystore.pageobjects.ProductsPage;
import com.mystore.utility.Log;

public class CartTest extends BaseClass {
	HomePage homePage;
	CartPage cartPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
		
	@Test(description = "test case 11",groups= {"regression","sanity"})
	public void subscriptionToMailFromCartPage() {
		Log.startTestCase("subscriptionToMailFromCartPage");
		homePage = new HomePage();
		Log.info("Verify homepage is visible");
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Assert.assertTrue(homePageLoad);
		Log.info("clicking on cart");
		cartPage = homePage.clickOnCartButton();
		String expectedCartURL = getDriver().getCurrentUrl();
		Log.info("validating cart is loaded");
		Assert.assertEquals(expectedCartURL, "https://automationexercise.com/view_cart");
		Log.info("Scrolling down");
		Action.scrollDownToFotterOfPage(getDriver());
		boolean subscriptionLoad = cartPage.verifySubscriptionBoxIsVisible();
		Log.info("verifying if subscription box is loaded");
		Assert.assertTrue(subscriptionLoad);
		Log.info("Filing email and submitting");
		String actualSuccessMsg = cartPage.submitSubscriptionWithEmail(prop.getProperty("email"));
		Log.info("Validating success message");
		Assert.assertEquals(actualSuccessMsg, "You have been successfully subscribed!");
		Log.endTestCase("subscriptionToMailFromCartPage");

	}
	@Test(description ="Test case- 12 Add product to cart and verify quantity and price",groups= {"regression","sanity"})
	public void addProductsToCart() {
		Log.startTestCase("addProductsToCart");
		homePage = new HomePage();
		Log.info("Verifying if homePage is visible");
		Assert.assertTrue(homePage.verifyHomePageVisible());
		productsPage= homePage.clickOnProducts();
		Log.info("Clicking on products section");
		Assert.assertTrue(productsPage.verifySaleLogo());
		Log.info("verifyed if sale logo is visible");
		Log.info("moving mouse to first product and clicking add to cart");
		productsPage.mouseHoverAddtoCartContinueShopping(1);
		
		Log.info("Moving to second element and clicking add to cart and selecting view cart from pop up");
		cartPage = productsPage.mouseHoverAddtoCartViewCart(2);		
		boolean cartPageLoad= cartPage.verifySubscriptionBoxIsVisible();
		Log.info("verifying if cart page is visible");
		Assert.assertTrue(cartPageLoad);
		Log.info("verfying only 2 products are present in cart");
		int productsInCart=cartPage.noOfProductsInCart();
		if(productsInCart==2) {
			Log.info("Verifying price and quantity total is same as displayed");
			boolean verification =cartPage.validatingPriceOfProductsQuantity();
			Assert.assertTrue(verification);
			Log.endTestCase("addProductsToCart");
		}else {
			Log.error("No of products are not 2, TestCase requried only 2 products");
		}
		
	}
	
	@Test(description= "Test Case13- Add a product with 4-quantity and verify it from cart",groups= {"regression"})
	public void verifyQuantityFromCart() {
		Log.startTestCase("verifyQuantityFromCart");
		homePage = new HomePage();
		Assert.assertTrue(homePage.verifyHomePageVisible());
		Log.info("Verified Home Page is visible");
		productDetailsPage = homePage.clickOnViewProductForProduct(5);
		Log.info("Clicked on product 5");
		Assert.assertTrue(productDetailsPage.verifyProductDetailsVisible());
		Log.info("Verifying if products are visible");
		boolean quantityAdded= productDetailsPage.addQuantityToProduct(4);
		Log.info("verifying quantity is change to 4");
		Assert.assertTrue(quantityAdded);
		productDetailsPage.clickOnAddToCart();
		Log.info("Clicked on Add to Cart Button");
		cartPage= productDetailsPage.clickOnViewCartFromPopUp();
		Log.info("Clicked on view cart from popup");
		int quantity =cartPage.quantityOfProductDisplay(1);
		Log.info("Getting quantity displayed and verifying its 4");
		Assert.assertEquals(quantity, 4);
		Log.endTestCase("verifyQuantityFromCart");
		
		
		 
		
	}


}
