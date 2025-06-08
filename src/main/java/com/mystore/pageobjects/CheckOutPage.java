/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * 
 */
public class CheckOutPage extends BaseClass {

	@FindBy(xpath = "//h3[text()='Your delivery address']")
	WebElement deliveryAddress;
	@FindBy(xpath = "//h3[text()='Your billing address']")
	WebElement billingAddress;
	@FindBy(xpath = "//h2[text()='Review Your Order']")
	WebElement reviewOrder;
	@FindBy(className = "form-control")
	WebElement commentBox;
	@FindBy(css = "a[href='/payment']")
	WebElement placeOrderBtn;

	public CheckOutPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public PaymentPage clickOnPlaceOrderBtn() {
		//Action.click(getDriver(), placeOrderBtn);
		Action.jsClick(getDriver(), placeOrderBtn);
		return new PaymentPage();
	}

	public void enterCommentForOrder(String message) {
		Action.type(commentBox, message);

	}

	public boolean verifyReviewOrderIsVisible() {
		return Action.isDisplayed(getDriver(), reviewOrder);
	}

	public boolean verifyDeliveryAddressIsVisible() {
		if (deliveryAddress.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyBillingAddressIsVisible() {
		if (billingAddress.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

}
