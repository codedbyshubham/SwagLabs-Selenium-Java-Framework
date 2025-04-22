package com.swaglabs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	//	Create object of WebDriver
	WebDriver localDriver;

//	Constructor
	public CheckOutPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
//	WebElements
	@FindBy(id="first-name")
	WebElement firstName;
	
	@FindBy(id="last-name")
	WebElement lastName;
	
	@FindBy(id="postal-code")
	WebElement postalCode;
	
	@FindBy(xpath="//input[@class='btn_primary cart_button']")
	WebElement continueBtn;
	
//	Actions 
	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	
	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}
	
	public void enterPostalCode(String pCode) {
		postalCode.sendKeys(pCode);
	}
	
	public void clickOnContinueBtn() {
		continueBtn.click();
	}
	
}
