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
public class SignUpPage extends BaseClass {
	@FindBy(xpath = "//input[@value='Mr']")
	WebElement titleMrOrMrs;
	
	@FindBy(id="name")
	WebElement name;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="days")
	WebElement days;
	
	@FindBy(id="months")
	WebElement months;
	
	@FindBy(id="years")
	WebElement years;
	
	@FindBy(id="newsletter")
	WebElement SignupNewsletterCheckBox;
	
	@FindBy(xpath = "//input[@id='optin']")
	WebElement specialOfferCheckBox;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="last_name")
	WebElement lastName;
	
	@FindBy(id="company")
	WebElement company;
	
	@FindBy(id="address1")
	WebElement address1;
	
	@FindBy(id="address2")
	WebElement address2;
	
	@FindBy(id="country")
	WebElement countryDropDown;
	
	@FindBy(id="state")
	WebElement state;
	
	@FindBy(id="city")
	WebElement city;
	
	@FindBy(id="zipcode")
	WebElement zipCode;
	
	@FindBy(id="mobile_number")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//button[@data-qa='create-account']")
	WebElement createAccountButton;
	public SignUpPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public boolean verifyEmailNamePopulatedInSignUpForm() {
		String nameValue = name.getDomAttribute("Value");
		String emailValue=email.getDomAttribute("Value");
		if(nameValue!=null && emailValue!= null) {
			return true;
		}else {
			return false;
		}			
		
	}
	
	public AccountCreatedPage signUp() {
		Action.type(password, "Test1234");
		Action.selectValueFromDropDown(days, "13");
		Action.selectValueFromDropDown(months, "4");
		Action.selectValueFromDropDown(years, "1997");
		Action.selectCheckBoxValue(SignupNewsletterCheckBox);
		Action.selectCheckBoxValue(specialOfferCheckBox);
		Action.type(firstName, "Test FirstName");
		Action.type(lastName, "TestLatname");
		Action.type(company, "Logistic LTD");
		Action.type(address1,"Hyderabad");
		Action.type(address2, "Telangana");
		Action.selectValueFromDropDown(countryDropDown, "India");
		Action.type(state, "Telangana");
		Action.type(city, "HYD");
		Action.type(zipCode, "200020");
		Action.type(mobileNumber, "999999999");
		Action.click(getDriver(), createAccountButton);
		return new AccountCreatedPage();
	}
	
	
	
			
	
	

}
