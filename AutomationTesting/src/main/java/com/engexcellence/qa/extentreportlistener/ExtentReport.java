package com.engexcellence.qa.extentreportlistener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.engexcellence.qa.base.TestBase;
import com.engexcellence.qa.util.TestUtil;
import com.engexcellence.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;

import com.relevantcodes.extentreports.model.Test;

public class ExtentReport extends TestBase implements ITestListener {
	
	
	 protected static ExtentReports reports;
	 protected static ExtentReports reportsJenkins;
	 protected static ExtentTest test;
	 protected static ExtentTest testjenkins;
	 public String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
	/**
	public void start(ExtentReports extent) {
		 System.out.println("Test Case execution started....");
			
		}
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory){
		
		extent = new ExtentReports(outputDirectory + File.separator + "\\extentreports\\"+ "\\Gmail Application Report"+TestUtil.simpleDateFormat()+".html", true);
		
		for(ISuite suite: suites){
			Map<String, ISuiteResult> result = suite.getResults();
			
			for(ISuiteResult r : result.values()){
				ITestContext context = r.getTestContext();
				try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getFailedTests(),LogStatus.FAIL);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				
			}
		}
		
		extent.flush();
		extent.close();
	}**/

	/**
	private void buildTestNodes(IResultMap tests, LogStatus status) throws IOException {
		
		ExtentTest test;
		
		
		if(tests.size()>0){
			
			for(ITestResult result : tests.getAllResults()){
				test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
				for(String group : result.getMethod().getGroups()){
					test.assignCategory(group);
				}
				
				if(result.getThrowable()!=null){
					//String screenshotPath = TestUtil.captureScreenshot(driver,System.getProperty("user.dir")+"\\screenshots\\"+TestUtil.simpleDateFormat()+".png");
					
					//test.log(status, test.addScreenCapture(WebEventListener.screenshotDestination));
					test.log(status, result.getMethod().getMethodName() + " test case is failed", test.addScreenCapture(WebEventListener.screenshotDestination));
					 test.log(status, result.getMethod().getMethodName() + " test failure description", result.getThrowable().fillInStackTrace());	
				}else if(status==LogStatus.SKIP){
					test.log(status, result.getMethod().getMethodName() + " test case is skipped", result.getName());
				}else if(status==LogStatus.PASS) {
					test.log(status, result.getMethod().getMethodName() + " test case is passed", result.getName());
				}
				extent.endTest(test);
			}
			
		
		}
		
	}**/
	
	private Date getTime(long millis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
		
	}

	public void addTest(Test arg0) {
		// TODO Auto-generated method stub
		
	}

	public void flush() {
		// TODO Auto-generated method stub
		
	}

	public void setTestRunnerLogs() {
		// TODO Auto-generated method stub
		
	}

	
	public void stop() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("on test start...");
		
	}




	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("on test success");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");	
		testjenkins.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	}




	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("on test failure");
		  //test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
		  try{
			  //ScreenShot.takeScreenshotCapture(driver, "D:\\Nagarro_Automation_Project\\COCQE\\COCQE-master-83292b8b36d18a7554f04bad407193a5fc70874c\\web_automation_workspace\\web-solution-acceptance-test\\screenshot\\"+ result.getMethod().getMethodName() + ".png");
//String file = test.addScreenCapture("D:\\Nagarro_Automation_Project\\COCQE\\COCQE-master-83292b8b36d18a7554f04bad407193a5fc70874c\\web_automation_workspace\\web-solution-acceptance-test\\screenshot\\" + result.getMethod().getMethodName() + ".png");
//ScreenShot.takeScreenshotCapture(driver, System.getProperty("user.dir")+"\\screenshot\\"+ result.getMethod().getMethodName() + ".png");
TestUtil.captureScreenshot(driver, System.getProperty("user.dir")+"\\screenshot\\"+result.getMethod().getMethodName()+timestamp+".png");
String file = test.addScreenCapture(System.getProperty("user.dir")+"\\screenshot\\"+result.getMethod().getMethodName()+timestamp+".png");
String filejenkins = testjenkins.addScreenCapture(System.getProperty("user.dir")+"\\screenshot\\"+result.getMethod().getMethodName()+timestamp+".png");


//test1.addScreenCapture(System.getProperty("user.dir")+"\\screenshot\\" + result.getMethod().getMethodName() + ".png");

test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", file);
testjenkins.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", filejenkins);
test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test failure description", result.getThrowable().fillInStackTrace());	
testjenkins.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test failure description", result.getThrowable().fillInStackTrace());	
	} catch(Exception e){
	e.printStackTrace();
	}	  
		  
	}
		
	




	@Override
	public void onTestSkipped(ITestResult result) {
		 System.out.println("on test skipped");
		  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
		  testjenkins.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
		 
		
	}




	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on test sucess within percentage");
		
	}




	@Override
	public void onStart(ITestContext context) {
		System.out.println("on start");
		//reports = new ExtentReports(System.getProperty("D:\\Nagarro_Automation_Project\\COCQE\\COCQE-master-83292b8b36d18a7554f04bad407193a5fc70874c\\web_automation_workspace\\web-solution-acceptance-test\null")+"\\STMExtentReport.html", true);
		//reports.addSystemInfo("Host Name", "Selenium Automation").addSystemInfo("Enviornment", "Automation Testing").addSystemInfo("Username", "osgoyal");
		
		reports = new ExtentReports(System.getProperty("user.dir")+"\\Report\\"+"\\STMExtentReport"+TestUtil.simpleDateFormat()+".html", true);
		reports.addSystemInfo("Host Name", "Selenium Automation").addSystemInfo("Enviornment", "Automation Testing").addSystemInfo("Username", "osgoyal");
		reports.loadConfig(new File(System.getProperty("user.dir")+"\\dev"+"\\extent-config.xml"));
	
		reportsJenkins = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\extentreports\\"+"\\STMExtentReport.html", true);
		reportsJenkins.addSystemInfo("Host Name", "Selenium Automation").addSystemInfo("Enviornment", "Automation Testing").addSystemInfo("Username", "osgoyal");
		reportsJenkins.loadConfig(new File(System.getProperty("user.dir")+"\\dev"+"\\extent-config.xml"));
	
	
	}




	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on finish");
		  reports.endTest(test);
		  reports.flush();
		  reportsJenkins.endTest(test);
		  reportsJenkins.flush();
		  
		
	}

	

	

	

	

	

	

}
