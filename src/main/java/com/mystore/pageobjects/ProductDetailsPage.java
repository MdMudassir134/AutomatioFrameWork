package com.mystore.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class ProductDetailsPage extends BaseClass {

	@FindBy(xpath = "//div[@class=\"product-information\"]//child::h2")
	WebElement productName;
	@FindBy(xpath = "//div[@class=\"product-information\"]//child::p[1]")
	WebElement category;
	@FindBy(xpath = "//div[@class='product-information']/span/span")
	WebElement productPrice;
	@FindBy(xpath = "//b[text()='Availability:']")
	WebElement availability;
	@FindBy(xpath = "//div[@class='product-information']//child::p[3]/b")
	WebElement condition;
	@FindBy(xpath = "//div[@class='product-information']//child::p[4]/b")
	WebElement brand;
	@FindBy(id = "quantity")
	WebElement quantityBox;
	@FindBy(xpath = "//button[@class=\"btn btn-default cart\"]")
	WebElement addToCartBtn;
	@FindBy(xpath = "//u[text()='View Cart']")
	WebElement popUpViewCart;
	@FindBy(xpath = "//button[text()='Continue Shopping']")
	WebElement popUpContinueShoping;

	public ProductDetailsPage() {
		PageFactory.initElements(getDriver(), this);
	}

	// Verify that detail is visible: product name, category, price, availability,
	// condition, brand
	public boolean verifyProductDetailsVisible() {
		// verifying product Name, Category,Price,Availability,Conditions,Brand
		if (!Action.isDisplayed(getDriver(), productName))
			return false;
		if (!Action.isDisplayed(getDriver(), category))
			return false;
		if (!Action.isDisplayed(getDriver(), productPrice))
			return false;
		if (!Action.isDisplayed(getDriver(), availability))
			return false;
		if (!Action.isDisplayed(getDriver(), condition))
			return false;
		if (!Action.isDisplayed(getDriver(), brand))
			return false;

		return true;
	}

	public boolean addQuantityToProduct(int quantity) {

		if (quantity > 0) {
			String qua = Integer.toString(quantity);
			Action.type(quantityBox, qua);
			return true;
		} else {
			System.out.println("Failed to update - Zero Quantity");
			return false;
		}
	}

	public void clickOnAddToCart() {
		Action.click(getDriver(), addToCartBtn);
	}
	
	public CartPage clickOnViewCartFromPopUp() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(popUpViewCart)).click();
		return new CartPage();
	}
	public void clickOnContinueShoppingFromPopUp() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(popUpContinueShoping)).click();
		
	}

	

}
