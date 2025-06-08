/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * 
 */
public class AccountCreatedPage extends BaseClass {
	
	@FindBy(xpath ="//h2[@class='title text-center']/b")
	WebElement accountCreatedMsg;
	@FindBy(xpath = "//a[@data-qa='continue-button']")
	WebElement continueButton;
	
	public AccountCreatedPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateAccountCreated() {
		return Action.isDisplayed(getDriver(), accountCreatedMsg);
		
	}
	public HomePage clickOnContinueButton() {
		Action.click(getDriver(), continueButton);
		return new HomePage();
	}

}
