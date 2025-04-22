package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swaglabs.pageobject.LoginPage;
import com.swaglabs.pageobject.ProductPage;
import com.swaglabs.utilities.ReadExcelFile;

public class TC_LoginPageTest extends BaseClass {

    String EXPECTED_URL = "https://www.saucedemo.com/v1/inventory.html";

	@Test(dataProvider = "LoginDataProvider")
	public void verifyLogin(String userName, String password) throws Exception {
		
		LoginPage login = new LoginPage(driver);
		login.enterUserName(userName);
        logger.info("Entered Username: "+ userName);

		login.enterPassWord(password);
        logger.info("Entered Password: "+ password);

		login.clickOnLoginBtn();
        logger.info("Clicked on Login Button");
		
		if(driver.getCurrentUrl().equals(EXPECTED_URL)) {
			logger.info("VerifyLogin - Passed");
			login.clickOnHemBurgerIcon();
			login.clickOnLogout();
		}
		else {
			logger.warn("VerifyLogin - Failed");
			captureScreenshot("verifyLogin_" + userName);
			System.out.println("this method is failed");
			driver.navigate().refresh();
            Assert.fail("Login failed for credentials: " + userName + " / " + password);

		}
	}
	
	@DataProvider(name="LoginDataProvider")
	public String[][] LoginDataProvider(){
		String filePath=System.getProperty("user.dir")+"\\TestData\\SwagLabsTestData.xlsx";
        String sheetName = "LoginPageTestData";

		int totalRows = ReadExcelFile.getRowCount(filePath, sheetName);
		int totalColumns = ReadExcelFile.getColCount(filePath, sheetName);
		
		String testData[][]=new String[totalRows-1][totalColumns];
		
		for(int i=1;i<totalRows;i++) {
			for(int j=0;j<totalColumns;j++) {
				testData[i-1][j]=ReadExcelFile.getCellValue(filePath, sheetName, i, j);
				
			}
		}
		return testData;
	}
}
