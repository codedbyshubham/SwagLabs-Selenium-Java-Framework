package com.swaglabs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinishPage {
//	Create object of WebDriver
	WebDriver localDriver;
	
	public FinishPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
	@FindBy(xpath = "//h2[text()='THANK YOU FOR YOUR ORDER']")
	public static WebElement thankyouMessage;
	
	@FindBy(xpath = "//div[contains(text(),'Your order has been dispatched')]")
	public static WebElement orderDispatchMessage;
	
	public boolean orderDispatchMessageIsDisplayed() {
		return orderDispatchMessage.isDisplayed();
	}
	
	
}
