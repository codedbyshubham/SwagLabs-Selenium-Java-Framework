package com.swaglabs.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	//	Create object of WebDriver
	WebDriver localDriver;

	public ProductPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}


	//	WebElements
	@FindBy(xpath="//select[@class='product_sort_container']")
	WebElement dropDown;

	@FindBy(xpath="(//div[text()='Sauce Labs Fleece Jacket']/following::button)[1]")
	WebElement fleeceJacketBtn;

	@FindBy(xpath="(//div[text()='Sauce Labs Backpack']/following::button)[1]")
	WebElement backPackBtn;
	
	@FindBy(xpath="//div[@id='shopping_cart_container']")
	WebElement shoppingCartIcon;
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	List<WebElement> productNameElements;
	
	@FindBy(xpath="//button[@class='btn_primary btn_inventory']")
	List<WebElement> addToCartButtons;
	
	@FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
	WebElement numberOfItemsAdded;
	

	//	Actions
	public void selectDropDown(String dropDownValue) {
		Select select = new Select(dropDown) ;
		select.selectByVisibleText(dropDownValue);
	}

	public void addFleeceJacketToCart() {
		fleeceJacketBtn.click();	
		}

	public void addBackpackToCart() {
		backPackBtn.click();
	}

	public void goToShoppingCart() {
		shoppingCartIcon.click();
	}
	
	public boolean areAllProductsDisplayed() {
		for(WebElement product: productNameElements) {

            if (!product.isDisplayed()) {
                return false;
            }
        }
        return true;		
	}
	
	public void addAllProductsToCart() {
		for(WebElement addProduct: addToCartButtons) {
			addProduct.click();
		}
	}
	
	public int getTotalProductCount() {
		return productNameElements.size();
	}
	
	public String getCartItemCount() {
		return numberOfItemsAdded.getText();
	}
	

}
