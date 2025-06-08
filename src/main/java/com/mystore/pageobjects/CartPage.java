package com.mystore.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class CartPage extends BaseClass {
	@FindBy(id = "susbscribe_email")
	WebElement subscriptionTextBox;

	@FindBy(css = "i[class='fa fa-arrow-circle-o-right']")
	WebElement submitSubscription;

	@FindBy(css = "div[class='alert-success alert']")
	WebElement subscriptionSucessMessage;
	@FindBy(css = "li[class='active']")
	WebElement shoppingEle;

	@FindBy(xpath = "//tr[starts-with(@id,'product')]")
	List<WebElement> noOfItems;
	@FindBy(xpath = "//td[@class='cart_price']/p")
	List<WebElement> priceOfEachProduct;

	@FindBy(xpath = "//tr[starts-with(@id,'product')]//button")
	List<WebElement> quantityOfEachProduct;

	@FindBy(xpath = "//td[@class='cart_total']/p")
	List<WebElement> disTotalofEachPrd;

	@FindBy(xpath = "//a[text()='Proceed To Checkout']")
	WebElement proceedToCheckOutBtn;
	
	@FindBy(xpath="//a[@href='/login']/u")
	WebElement popUpRegisterLoginBtn;

	public CartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public boolean verifySubscriptionBoxIsVisible() {
		return Action.isDisplayed(getDriver(), subscriptionTextBox);
	}

	public String submitSubscriptionWithEmail(String email) {
		Action.implicitWait(getDriver(), 0);
		Action.type(subscriptionTextBox, email);
		Action.implicitWait(getDriver(), 30);
		Action.click(getDriver(), submitSubscription);

		return subscriptionSucessMessage.getText();
	}

	public int noOfProductsInCart() {
		int count = noOfItems.size();
		return count;
	}

	public boolean validatingPriceOfProductsQuantity() {
		int totalCalculated = 0;
		for (WebElement price : priceOfEachProduct) {
			// price contain values like Rs.500 selecting only price
			String numPart = (price.getText()).replaceAll("[^0-9]", "");
			int cost = Integer.parseInt(numPart);
			System.out.println("price displayed for each prd " + cost);

			for (WebElement quantity : quantityOfEachProduct) {
				int qua = Integer.parseInt(quantity.getText());
				System.out.println("quantity of each product" + qua);
				totalCalculated = cost * qua;
				System.out.println("price calculated" + totalCalculated);
				// validating calculated total matched displayed total
				for (WebElement totalDisplay : disTotalofEachPrd) {
					String numPart1 = (totalDisplay.getText()).replaceAll("[^0-9]", "");
					System.out.println(numPart1);
					int disTotalPrice = Integer.parseInt(numPart1);
					if (disTotalPrice == totalCalculated) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public int quantityOfProductDisplay(int quantityFor) {
		WebElement product = quantityOfEachProduct.get(quantityFor - 1);
		int qua = Integer.parseInt(product.getText());
		return qua;
	}

	public void clickOnProceedToCheckOut() {
		Action.click(getDriver(), proceedToCheckOutBtn);
	}
	public LoginSignUpPage clickOnLoginRegisterOnPopUp() {
		Action.jsClick(getDriver(), popUpRegisterLoginBtn);
		return new LoginSignUpPage();
			
	}
	public CheckOutPage clickOnProceedToCheckWithUserLoggedIn() {
		Action.click(getDriver(), proceedToCheckOutBtn);
		return new CheckOutPage();
	}
}
