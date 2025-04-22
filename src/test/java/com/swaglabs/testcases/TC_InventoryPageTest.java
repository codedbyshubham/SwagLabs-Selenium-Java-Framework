package com.swaglabs.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.pageobject.LoginPage;
import com.swaglabs.pageobject.ProductPage;
import com.swaglabs.pageobject.YourCartPage;
import com.swaglabs.utilities.ReadConfig;

public class TC_InventoryPageTest extends BaseClass{

	@Test
	public void verifyInventoryPageFunctionality() {
		ReadConfig readConfig = new ReadConfig();

		LoginPage login = new LoginPage(driver);
		login.enterUserName(readConfig.getUserName());
		logger.info("Entered username: " + readConfig.getUserName());

		login.enterPassWord(readConfig.getPassword());
		logger.info("Entered password " + readConfig.getPassword());

		login.clickOnLoginBtn();
		logger.info("Clicked on Login Button");

		// Product actions
		ProductPage product = new ProductPage(driver);
		product.areAllProductsDisplayed();
        logger.info("Verified all products are displayed.");


		product.addAllProductsToCart();
        logger.info("Added all products to cart.");

		int totalProducts=product.getTotalProductCount();
		logger.info("Total number of products available: "+ product.getTotalProductCount());
		
		String cartItemCount = product.getCartItemCount();
        logger.info("Number of items added to cart: "+ cartItemCount);



        Assert.assertEquals(cartItemCount, String.valueOf(totalProducts), 
            "Mismatch in number of products and items added to cart");
        
		product.goToShoppingCart();;
        logger.info("Navigated to Shopping Cart.");

		YourCartPage cartPage = new YourCartPage(driver);
		cartPage.removeAllProductsFromCart();
        logger.info("Removed all products from cart.");
		

        cartPage.clickOnContinueShoppingBtn();
		logger.info("Clicked on Continue Shopping Button");

		// Add selective products
		product.addBackpackToCart();
		product.addFleeceJacketToCart();
        logger.info("Added specific products (Backpack & Fleece Jacket).");


		product.goToShoppingCart();
        logger.info("Navigated back to Shopping Cart.");

        cartPage.clickOnFleeceJacketRemoveBtn();
        logger.info("Removed Fleece Jacket from cart.");

        cartPage.clickOnCheckoutBtn();
        logger.info("Proceeded to checkout.");


	}

	


}
