package com.swaglabs.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	public void configureReport() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/Reports/SwagLabsTestReport-"+timestamp+".html";
		
		htmlReporter = new ExtentSparkReporter(reportPath);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		ReadConfig readConfig = new ReadConfig();
//		add system/environment info to report
		reports.setSystemInfo("Machine", "TestPC");
		reports.setSystemInfo("OS", "Windows 11");
		reports.setSystemInfo("Browser", readConfig.getBrowser());
		reports.setSystemInfo("User Name", "Shubham Verma");

//		Configuration to change the name & theme of report
		htmlReporter.config().setDocumentTitle("SwagLabs Test Report");
		htmlReporter.config().setReportName("SwagLabs Test Execution");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
//	onStart method is called when any Test starts
    @Override
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("On Start menthod invoked....");
	}
	
//	onFinish method is called after all Tests are executed
    @Override
    public void onFinish(ITestContext context) {
		System.out.println("On Finish menthod invoked....");
		reports.flush(); // it is mandatory to call flush method to ensure information is written to the started reporter
	}
	
//	When TC get failed, onTestFailure methos is called
    @Override
    public void onTestFailure(ITestResult Result) {
		System.out.println("Name of Test method failed: " + Result.getName());
		test = reports.createTest(Result.getName()); //create entry in html report
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of failed TC is:"+ Result.getName(), ExtentColor.RED));
	
		String screenShotPath = System.getProperty("user.dir")+"/ScreenShots/"+Result.getName()+".png";
		
		File screenShotFile = new File(screenShotPath);
			
		if(screenShotFile.exists()) {
			
			 try {
					test.fail("Captured Screenshot is below: "+ test.addScreenCaptureFromPath(screenShotPath));
			 } catch (Exception e) {
	                test.fail("Failed to attach screenshot: " + e.getMessage());
	            }
		}
	}
	
//	When TC get skipped, onTestSkipped method is called
    @Override
    public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of Test method skipped: " + Result.getName());
		test = reports.createTest(Result.getName()); 
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of skipped TC is:"+ Result.getName(), ExtentColor.YELLOW));
	}
	
//	When TC get started, onTestStart method is called
    @Override
    public void onTestStart(ITestResult Result) {
		System.out.println("Name of Test method started: " + Result.getName());	
	}
	
//	When TC get passed, onTestSuccess method is called
    @Override
    public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of Test method Passed: " + Result.getName());
		test = reports.createTest(Result.getName()); 
		test.log(Status.PASS, MarkupHelper.createLabel("Name of passed TC is: "+ Result.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		
	}
}
