package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;



public class TestCasePage extends BaseClass {
	
	@FindBy(xpath="//b[text()='Test Cases']")
	WebElement testCaseHeading;
	
	public TestCasePage() {
		PageFactory.initElements(getDriver(), this);
	}
	public boolean verifyTestCasePageVisible() {
		return Action.isDisplayed(getDriver(), testCaseHeading);
		
	}

}
