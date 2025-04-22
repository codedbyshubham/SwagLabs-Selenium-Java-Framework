package com.swaglabs.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.swaglabs.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	String baseUrl = readConfig.getBaseUrl();
	String browser = readConfig.getBrowser();

	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		
//		for logging
		logger = LogManager.getLogger("SwagLabs");
		
		initializeBrowser(browser);
		configureBrowser();

	    logger.info("Navigating to URL: {}", baseUrl);
	    driver.get(baseUrl);
	}
	
	

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
       logger.info("Browser closed");

	}
	
	public void initializeBrowser(String browserName) {
		switch(browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
            logger.info("Chrome browser launched");
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
            logger.info("Edge browser launched");
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
            logger.info("Firefox browser launched");
			break;
			
		default:
			logger.error("Unsupported browser: {}", browserName);
            throw new RuntimeException("Unsupported browser: " + browserName);
		}
	}
	
	 public void configureBrowser() {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        logger.info("Browser configured with implicit wait and maximized window");
	    }
	
//	public void captureScreenShot(WebDriver driver, String testName) throws Exception {
//		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"//Screenshots//"+testName+".png"));
//	}
	 
	    public void captureScreenshot(String testName) {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
	        try {
	            FileUtils.copyFile(screenshot, new File(screenshotPath));
	            logger.info("Screenshot captured: {}", screenshotPath);
	        } catch (IOException e) {
	            logger.error("Failed to capture screenshot: {}", e.getMessage());
	        }
	    }
	
	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "border : 2px solid red; background : yellow;");
	}

}
