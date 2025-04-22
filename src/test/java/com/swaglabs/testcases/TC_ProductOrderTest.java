package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swaglabs.pageobject.CheckOutPage;
import com.swaglabs.pageobject.FinishPage;
import com.swaglabs.pageobject.LoginPage;
import com.swaglabs.pageobject.OverviewPage;
import com.swaglabs.pageobject.ProductPage;
import com.swaglabs.pageobject.YourCartPage;
import com.swaglabs.utilities.ReadConfig;
import com.swaglabs.utilities.ReadExcelFile;

public class TC_ProductOrderTest extends BaseClass {

	@Test(dataProvider = "CheckoutInfoTestData")
	public void productOrder(String firstName, String lastName, String zipCode) {
		ReadConfig readConfig = new ReadConfig();

		LoginPage login = new LoginPage(driver);
		login.enterUserName(readConfig.getUserName());
		logger.info("Entered username: " + readConfig.getUserName());

		login.enterPassWord(readConfig.getPassword());
		logger.info("Entered password " + readConfig.getPassword());

		login.clickOnLoginBtn();
		logger.info("Clicked on Login Button");

		ProductPage product = new ProductPage(driver);
		product.areAllProductsDisplayed();
        logger.info("Displayed product list.");


		product.addBackpackToCart();
		product.addFleeceJacketToCart();
		
		System.out.println("numOfItemsAdded: "+product.getCartItemCount());
		logger.info("Number of Items Added to Cart: "+ product.getCartItemCount());

		product.goToShoppingCart();;
        logger.info("Navigated to cart.");

		YourCartPage cartPage = new YourCartPage(driver);
		cartPage.clickOnFleeceJacketRemoveBtn();
        logger.info("Removed Fleece Jacket from cart.");

        logger.info("Cart count after removal: "+ cartPage.getCartItemCount());

        cartPage.clickOnCheckoutBtn();
        logger.info("Proceeded to checkout.");

		CheckOutPage checkOut = new CheckOutPage(driver);
		checkOut.enterFirstName(firstName);
		logger.info("Entered First Name: "+firstName);

		checkOut.enterLastName(lastName);
		logger.info("Entered Last Name: "+lastName);

		checkOut.enterPostalCode(zipCode);
		logger.info("Entered ZIP Code: "+zipCode);

		checkOut.clickOnContinueBtn();
        logger.info("Clicked continue on checkout page.");

		OverviewPage overview = new OverviewPage(driver);
		highlightElement(driver, OverviewPage.summaryTotal);
		System.out.println("Summary Total of Purchase is: "+OverviewPage.summaryTotal.getText());
		
		logger.info("Summary Total: "+OverviewPage.summaryTotal.getText());
		overview.clickOnFinishBtn();
        logger.info("Finished order.");
		
		FinishPage finish = new FinishPage(driver);
		if(finish.orderDispatchMessageIsDisplayed()) {
			logger.info("Order Dispatch Message is Displayed");
		}
		
		highlightElement(driver, FinishPage.thankyouMessage);
        logger.info("Order completed successfully.");

	}

	@DataProvider(name="CheckoutInfoTestData")
	public String[][] LoginDataProvider(){
		String filePath=System.getProperty("user.dir")+"\\TestData\\SwagLabsTestData.xlsx";
        String sheet = "CheckoutInfoTestData";

		int rowCount = ReadExcelFile.getRowCount(filePath, sheet);
		int colCount = ReadExcelFile.getColCount(filePath, sheet);
		
		String data[][]=new String[rowCount-1][colCount];
		
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				data[i-1][j]=ReadExcelFile.getCellValue(filePath, sheet, i, j);
				
			}
		}
		return data;
	}





}
