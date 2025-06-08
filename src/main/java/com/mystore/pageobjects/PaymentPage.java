/**
 * 
 */
package com.mystore.pageobjects;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * 
 */
public class PaymentPage extends BaseClass{
	@FindBy(className = "form-control")
	private WebElement nameOnCard;
	@FindBy(name = "card_number")
	private WebElement cardNumber;
	@FindBy(name = "cvc")
	private WebElement cvc;
	@FindBy (className = "form-control card-expiry-month")
	private WebElement expireMonth;
	@FindBy(name="expiry_year")
	private WebElement expireYear;
	@FindBy(className = "form-control btn btn-primary submit-button")
	private WebElement payAndConfirmOrderBtn;
	@FindBy(xpath ="//button[text()='Pay and Confirm Order']")
	WebElement sucessfullMessage;
	
	 PaymentPage(){
		 PageFactory.initElements(getDriver(),this);
	 }
	
	
	
	
	public void fillPaymentDetails() {
		Action.type(nameOnCard, prop.getProperty("nameOnCard"));
		Action.type(cardNumber, prop.getProperty("cardNumber"));
		Action.type(cvc, prop.getProperty("cvc"));
		Action.type(expireMonth, prop.getProperty("expirationMonth"));
		Action.type(expireYear,prop.getProperty("expiratinYear"));
			
	}
	public PaymentSuccessfullPage clickOnPayAndConfirmButton() {
		Action.click(getDriver(), payAndConfirmOrderBtn);
		return new PaymentSuccessfullPage();
	}
	public String verifySucessfullMsg() {
		return sucessfullMessage.getText();
		
	}


}
