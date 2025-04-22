package com.swaglabs.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCartPage {

	//	Create object of WebDriver
	WebDriver localDriver;

	public YourCartPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
	//	WebElements
	@FindBy(xpath="(//div[text()='Sauce Labs Fleece Jacket']/following::button)[1]")
	WebElement fleeceJacketRemoveBtn;
	
	@FindBy(xpath="//a[text()='CHECKOUT']")
	WebElement checkoutBtn;
	
	@FindBy(xpath = "//a[text()='Continue Shopping']")
	WebElement continueShoppingBtn;
	
	@FindBy(xpath = "//button[text()='REMOVE']")
	List<WebElement> removeProductBtns;
	
	@FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
	WebElement cartItemCount;
	
	//	Actions
	public void clickOnFleeceJacketRemoveBtn() {
		fleeceJacketRemoveBtn.click();
	}

	public void clickOnCheckoutBtn() {
		checkoutBtn.click();
	}
	
	public void clickOnContinueShoppingBtn() {
		continueShoppingBtn.click();
	}
	
	public void removeAllProductsFromCart() {
		for(WebElement removeProductBtn :removeProductBtns) {
			removeProductBtn.click();
		}
	}
	
	public String getCartItemCount() {
		return cartItemCount.getText();
	}
}
