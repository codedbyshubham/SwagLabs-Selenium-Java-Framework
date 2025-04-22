package com.swaglabs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

//	Create object of WebDriver
	WebDriver localDriver;
	
	public LoginPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
//	WebElements
	@FindBy(id="user-name")
	WebElement usernameInput;
	
	@FindBy(id="password")
	WebElement passwordInput;
	
	@FindBy(id="login-button")
	WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='bm-burger-button']")
	WebElement hemBurgerIcon;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logoutLink;
	
//	Actions
	public void enterUserName(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void enterPassWord(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void clickOnLoginBtn() {
		loginBtn.click();
	}
	
	public void clickOnHemBurgerIcon() {
		hemBurgerIcon.click();
	}
	
	public void clickOnLogout() {
		logoutLink.click();
	}
}
