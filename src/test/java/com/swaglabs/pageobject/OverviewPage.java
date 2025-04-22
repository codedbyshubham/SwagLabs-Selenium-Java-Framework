package com.swaglabs.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {
	
//	Create object of WebDriver
	WebDriver localDriver;

	public OverviewPage(WebDriver remoteDriver) {
		localDriver=remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
//	WebElements
	@FindBy(xpath="//a[text()='FINISH']")
	WebElement finishBtn;
	
	@FindBy(xpath="//div[text()='Total: $']")
	public static WebElement summaryTotal;	
	
	
	public void clickOnFinishBtn() {
		finishBtn.click();
	}
	
    public boolean isSummaryTotalDisplayed() {
        return summaryTotal.isDisplayed();
    }

}
