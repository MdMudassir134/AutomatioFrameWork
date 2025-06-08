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
public class ContactUsPage extends BaseClass {
	@FindBy(xpath = "//input[@name='name']")
	WebElement contactName;

	@FindBy(css = "input[name='email']")
	WebElement contactEmail;

	@FindBy(css = "input[data-qa='subject']")
	WebElement contactSubject;

	@FindBy(id = "message")
	WebElement contactMessageBox;

	@FindBy(css = "input[type='file']")
	WebElement chooseFileButton;

	@FindBy(css = "input[value='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "//h2[text()='Get In Touch']")
	WebElement getInTouchText;

	@FindBy(xpath = "//a[@class='btn btn-success']")
	WebElement homeBtn;

	@FindBy(xpath = "//h2[text()='Contact ']")
	WebElement contactUsText;

	public ContactUsPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void uploadFileInContactUsPage(String filePath) {
		Action.uploadAFile(chooseFileButton, filePath);

	}

	public boolean verifyGetInTouch() {
		return Action.isDisplayed(getDriver(), getInTouchText);
	}

	public HomePage ClickOnHomeButton() {
		Action.click(getDriver(), homeBtn);
		return new HomePage();

	}

	public boolean verifyContactUsIsVisible() {
		return Action.isDisplayed(getDriver(), contactUsText);
	}

	public void fillContactUsForm(String name, String email, String subject, String message) {

		Action.type(contactName, name);
		Action.type(contactEmail, email);
		Action.type(contactSubject, subject);
		Action.type(contactMessageBox, message);
	}

	public void clickOnSubmitBtn() {
		submitButton.click();
		//getDriver().switchTo().frame(3);
		//Action.switchToFrameByIndex(getDriver(), 2);
		Action.alertAccept(getDriver());

	}

}
