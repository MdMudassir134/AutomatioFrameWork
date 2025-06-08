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
import com.mystore.pageobjects.ProductsPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

/**
 * 
 */
public class SearchTest extends BaseClass {
	HomePage homePage;
	ProductsPage productsPage;
	SearchResultPage searchResultPage;
	@BeforeMethod
	public void setUp() {
		launchApp();
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	@Test(description = "test Case 9- search a product",groups= {"regression","sanity"})
	public void searchProduct() {
		Log.startTestCase("searchProduct");
		homePage=new HomePage();
		boolean homePageLoad =homePage.verifyHomePageVisible();
		Assert.assertTrue(homePageLoad);
		Log.info("Verified home page is visible");
		productsPage=homePage.clickOnProducts();
		Log.info("clicked on products");
		searchResultPage=productsPage.searchProduct("top");
		Log.info("Product is searched");
		int productsCount=searchResultPage.noOfProductsInSearchResult();
		Log.info("no of products found: "+productsCount);
		if(productsCount>0) {
			boolean resultLoad=searchResultPage.verifySearchProductsVisible();
			Log.info("Verifying if products are visible");
			Assert.assertTrue(resultLoad);
		}else {
			Log.error("No products available for search value");
		}
		
		Log.endTestCase("searchProduct");
	}

}
