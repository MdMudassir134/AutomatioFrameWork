package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {
		
	@FindBy(xpath="//a[contains(@href,'/product_details/')]")
	List<WebElement> productsList;
	@FindBy(css="h2[class=\"title text-center\"]")
	WebElement searchProductsHeading;
	
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean verifySearchProductTextisVisible() {
		return Action.isDisplayed(getDriver(), searchProductsHeading);
		
	}
	public boolean verifySearchProductsVisible() {
		for(WebElement product : productsList) {
			if(!Action.isDisplayed(getDriver(), product))return false;
		}
		return true;	
	}
	
	public int noOfProductsInSearchResult() {
		int count = productsList.size();
		return count;
	}
	
}
