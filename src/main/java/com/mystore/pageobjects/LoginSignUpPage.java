package com.mystore.pageobjects;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginSignUpPage extends BaseClass {
	
	@FindBy(xpath = "//input[@data-qa=\'login-email\']")
	WebElement loginEmailBox;
	@FindBy(xpath = "//input[@data-qa='login-password']")
	WebElement loginPasswordBox;
	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement loginBtn;
	@FindBy(xpath = "//input[@data-qa='signup-name']")
	WebElement signUpName;
	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement signUpEmail;
	@FindBy(xpath = "//button[@data-qa='signup-button']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//h2[text()='New User Signup!']")
	WebElement newUserSignupHeading;
	
	@FindBy(xpath="//h2[text()='Login to your account']")
	WebElement loginAccountHeading;
	
	@FindBy(xpath="//*[@id=\"form\"]/div/div/div[1]/div/form/p")
	WebElement inValidEmailPasswordErrorMsg;
	
	@FindBy(xpath = "//*[@id='form']/div/div/div[3]/div/form/p")
	WebElement emailExistErrorMsg;
	
	public LoginSignUpPage() {
		PageFactory.initElements(getDriver(), this);
	}
	public boolean verifyNewUserSignUpIsVisible() {
		return Action.isDisplayed(getDriver(), newUserSignupHeading);
	}
	public boolean verifyLoginAccountIsVisible() {
		return Action.isDisplayed(getDriver(), loginAccountHeading);
	}
	public SignUpPage signUp(String userName,String email ) {
		Action.type(signUpName, userName);
		Action.type(signUpEmail, email);
		Action.click(getDriver(), signUpBtn);
		
		return new SignUpPage();
				
	}
	public HomePage signIn(String Email,String Password) {
		Action.type(loginEmailBox,Email);
		Action.type(loginPasswordBox, Password);
		Action.click(getDriver(), loginBtn);
		return new HomePage();
		
		
	}
	public WebElement invalidSignIn(String invalidEmail,String inavlidPassword) {
		Action.type(loginEmailBox, invalidEmail);
		Action.type(loginPasswordBox, inavlidPassword);
		Action.click(getDriver(), loginBtn);
		return inValidEmailPasswordErrorMsg;
			
				
	}
	public WebElement registerExistingUser(String existingName,String existingEmail) {
		Action.type(signUpName, existingName);
		Action.type(signUpEmail, existingEmail);
		Action.click(getDriver(), signUpBtn);
		return emailExistErrorMsg;
	}
	
}
