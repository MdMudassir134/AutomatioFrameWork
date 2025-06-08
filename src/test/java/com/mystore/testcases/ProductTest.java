/**
 * 
 */
package com.mystore.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.CartPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.ProductDetailsPage;
import com.mystore.pageobjects.ProductsPage;
import com.mystore.utility.Log;

/**
 * 
 */
public class ProductTest extends BaseClass{
	HomePage homePage;
	ProductsPage productsPage; 
	ProductDetailsPage productDetailsPage;
	CartPage cartPage;
	@BeforeMethod
	public void setUp() {
		launchApp();
		
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	@Test(description = "Test Case 8 - Verify all products and products details",groups= {"regression"})
	public void productDetailsValidation() {
		Log.startTestCase("productDetailsValidation");
		homePage = new HomePage();
		Log.info("Verifying if Home Page is Visible");
		boolean homePageLoad = homePage.verifyHomePageVisible();
		Assert.assertTrue(homePageLoad);
		Log.info("HomePage is visible clickin on products");
		productsPage = homePage.clickOnProducts();
		Log.info("verifying sale logo is present ");
		boolean saleLogo = productsPage.verifySaleLogo();
		Assert.assertTrue(saleLogo);
		Log.info("sales logo is visible, Verifying if all products are present");
		boolean productsLoad = productsPage.VerfyAllProductsVisible();
		Log.info("Verifying if all products are visible in products page");
		Assert.assertTrue(productsLoad, "fails to load all products");
		Log.info("selecting view product for first product");
		//as per test case clicking view details of first product
		WebElement viewDetails1= productsPage.selectViewDetailsForaProduct(3);
		Log.info("clicking on view details for first product");
		productDetailsPage = productsPage.clickOnViewDetails(viewDetails1);
		Log.info("Product details page loaded");
		Boolean productdetails = productDetailsPage.verifyProductDetailsVisible();
		assertTrue(productdetails, "Failed to verify product details");
		Log.info("Verifyied product details are visible");
		Log.endTestCase("productDetailsValidation");
						
	}
	
}
