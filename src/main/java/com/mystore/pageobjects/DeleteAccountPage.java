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
public class DeleteAccountPage extends BaseClass {
	
	@FindBy(xpath ="//a[@data-qa='continue-button']")
	WebElement continueButton;
	
	public HomePage clickOnContinueButton() {
		Action.click(getDriver(), continueButton);
		return new HomePage();
	}
	public DeleteAccountPage() {
		PageFactory.initElements(getDriver(), this);
	}
	

}
