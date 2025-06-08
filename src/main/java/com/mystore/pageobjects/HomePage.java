/**
 * 
 */
package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class HomePage extends BaseClass {

	@FindBy(xpath = "//img[@src='/static/images/home/logo.png']")
	WebElement AutomationExerciseLogo;

	@FindBy(xpath = "//a[@href='/products']")
	WebElement productsButton;

	@FindBy(css = "a[href='/view_cart']")
	WebElement cartButton;

	@FindBy(css = "a[href='/login']")
	WebElement signUpLoginButton;

	@FindBy(xpath = "//a[@href='/contact_us']")
	WebElement contactUsButton;

	@FindBy(xpath = "//img[@src='/static/images/home/girl2.jpg']")
	WebElement girlImgResponsive;

	@FindBy(xpath = "//a[@href='#Women']/span")
	WebElement womenCategory;

	@FindBy(xpath = "//a[@href='#Men']/span")
	WebElement menCategory;

	@FindBy(xpath = "//a[@href='#Kids']/span")
	WebElement kidsCategory;

	@FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b")
	WebElement loggedInAs;

	@FindBy(css = "a[href='/delete_account']")
	WebElement deleteAccountButton;

	@FindBy(css = "a[href='/logout']")
	WebElement logOutButton;

	@FindBy(css = "a[href='/contact_us']")
	WebElement contatcUsButton;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']//child::a[@href='/test_cases']")
	WebElement testCaseButton;

	@FindBy(id = "susbscribe_email")
	WebElement subscriptionTextBox;

	@FindBy(css = "i[class='fa fa-arrow-circle-o-right']")
	WebElement submitSubscription;

	@FindBy(css = "div[class='alert-success alert']")
	WebElement subscriptionSucessMessage;

	@FindBy(xpath = "//a[contains(@href,'/product_details/')]")
	List<WebElement> viewProducts;

	public HomePage() {
		Action.implicitWait(getDriver(), 60);
		PageFactory.initElements(getDriver(), this);
	}

	public boolean verifyHomePageVisible() {
		return Action.isDisplayed(getDriver(), girlImgResponsive);

	}

	public LoginSignUpPage clickOnSignUpSingInButton() {
		Action.click(getDriver(), signUpLoginButton);
		return new LoginSignUpPage();
	}

	public boolean verfyCreatedUserIsVisible() {
		return Action.isDisplayed(getDriver(), loggedInAs);

	}

	public DeleteAccountPage clickOnDeleteAccountButton() {
		Action.click(getDriver(), deleteAccountButton);
		return new DeleteAccountPage();
	}

	public LoginSignUpPage clickOnLogoutButton() {
		Action.click(getDriver(), logOutButton);
		return new LoginSignUpPage();
	}

	public ContactUsPage clickOnContactUsButton() {
		Action.click(getDriver(), contatcUsButton);
		return new ContactUsPage();

	}

	public TestCasePage clickOnTestCase() {
		Action.click(getDriver(), testCaseButton);
		return new TestCasePage();
	}

	public ProductsPage clickOnProducts() {
		Action.click(getDriver(), productsButton);
		return new ProductsPage();
	}

	public boolean verifySubscriptionBoxIsVisible() {
		return Action.isDisplayed(getDriver(), subscriptionTextBox);
	}

	public String submitSubscriptionWithEmail(String email) {
		Action.type(subscriptionTextBox, email);
		Action.click(getDriver(), submitSubscription);

		return subscriptionSucessMessage.getText();
	}

	public CartPage clickOnCartButton() {
		Action.click(getDriver(), cartButton);
		return new CartPage();
	}

	public ProductDetailsPage clickOnViewProductForProduct(int productNo) {
		if (productNo > 0) {
			WebElement viewProd = viewProducts.get(productNo - 1);
			Action.click(getDriver(), viewProd);
			return new ProductDetailsPage();
		} else {
			System.out.println("the product number added is in valid");
			return null;
		}

	}

	public void mouseHoverAddtoCartContinueShopping(int productNo) {
		String productXpath = "//div[@class=\"features_items\"]//child::div[@class=\"col-sm-4\"][" + productNo + "]";
		WebElement product = getDriver().findElement(By.xpath(productXpath));
		// mouseHoveron product
		Actions action = new Actions(getDriver());
		action.moveToElement(product).perform();

		// click on addToCart dynamic element
		String addToCartXpath = "//div[@class='overlay-content']//child::a[@data-product-id='" + productNo + "']";
		WebElement addToCartBtn = getDriver().findElement(By.xpath(addToCartXpath));
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		try {

			wait.until(ExpectedConditions.visibilityOf(addToCartBtn)).click();
		} catch (ElementClickInterceptedException e) {

			Action.jsClick(getDriver(), addToCartBtn);

		}
		// clicking continue shopping from pop up
		WebElement continueBtn = getDriver().findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"));
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

	}

}
