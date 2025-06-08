package com.mystore.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mystore.actiondriver.Action;

public class ProductsPage extends Action {

	@FindBy(id = "search_product")
	WebElement searchBox;
	@FindBy(id = "submit_search")
	WebElement searchBtn;
	@FindBy(xpath = "//img[@id='sale_image']")
	WebElement SaleLogo;
	@FindBy(xpath = "//h2[text()='All Products']")
	WebElement allProductsText;
	@FindBy(xpath = "//div[@class='features_items']//child::div[@class ='col-sm-4']")
	List<WebElement> productsList;

	WebElement viewDetailsBtn;

	public ProductsPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public boolean verifySaleLogo() {
		return Action.isDisplayed(getDriver(), SaleLogo);

	}

	public boolean VerfyAllProductsVisible() {
		for (WebElement product : productsList) {
			if (!Action.isDisplayed(getDriver(), product)) {
				return false;
			}
		}
		return true;
	}

	public void clickOnFirstProduct() {

	}

	public WebElement selectViewDetailsForaProduct(int productNo) {
		String s = "//a[contains(@href,'/product_details/" + productNo + "')]";
		viewDetailsBtn = getDriver().findElement(By.xpath(s));
		return viewDetailsBtn;
	}

	public ProductDetailsPage clickOnViewDetails(WebElement we) {
		Action.click(getDriver(), we);
		return new ProductDetailsPage();

	}

	public SearchResultPage searchProduct(String productName) {
		Action.type(searchBox, productName);
		Action.click(getDriver(), searchBtn);
		return new SearchResultPage();

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
		WebElement continueBtn = getDriver()
				.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block']"));
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

	}

	public CartPage mouseHoverAddtoCartViewCart(int productNo) {
		String productXpath = "//div[@class=\"features_items\"]//child::div[@class=\"col-sm-4\"][" + productNo + "]";
		WebElement product = getDriver().findElement(By.xpath(productXpath));
		// mouseHoveron product
		Actions action = new Actions(getDriver());
		action.moveToElement(product).perform();

		// click on addToCart dynamic element
		String addToCartXpath = "//div[@class='overlay-content']//child::a[@data-product-id='" + productNo + "']";
		WebElement addToCartBtn = getDriver().findElement(By.xpath(addToCartXpath));
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOf(addToCartBtn)).click();
		} catch (ElementClickInterceptedException e) {
			Action.jsClick(getDriver(), addToCartBtn);
		}

		// clicking on dynamic viewCart button
		WebElement viewCart = getDriver().findElement(By.xpath("//u[text()='View Cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();

		return new CartPage();

	}

}
