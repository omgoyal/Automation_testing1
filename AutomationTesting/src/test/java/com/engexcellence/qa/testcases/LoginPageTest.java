package com.engexcellence.qa.testcases;



import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.engexcellence.qa.base.TestBase;
import com.engexcellence.qa.extentreportlistener.ExtentReport;
import com.engexcellence.qa.util.TestUtil;
import com.engexcelllence.qa.pages.LoginPage;
import com.engexcelllence.qa.pages.SignInPage;
import com.relevantcodes.extentreports.LogStatus;


@Listeners(com.engexcellence.qa.extentreportlistener.ExtentReport.class)
public class LoginPageTest extends ExtentReport  {
	
	SignInPage signinpage;
	LoginPage loginpage;
	TestUtil testutil;
	
	String sheetName="User_Credentials";
	
	
	public LoginPageTest(){
		super();//Call parent class constructor --all properties have been defined 
	}

	@BeforeMethod
	public void setup(){
		initialization();
		 signinpage = new SignInPage();
		 loginpage = new LoginPage();
		 testutil = new TestUtil();
		 
	}
	@Test(priority=1)
	public void signInPageTitleTest(){
		test =reports.startTest("signInPageTitleTest");/*Launch the report testRevenueApplicationLogin*/
		test.log(LogStatus.INFO,"Navigate to Gmail Application Sign In pgw title test" );/*Log Revenue Application URL in report*/
		String actualSignInPageTitle = signinpage.getSignInPageTitle();
		
		Assert.assertEquals(actualSignInPageTitle, "Gmail: Private and secure email at no cost | Google Workspace");
	}
	
	@DataProvider (name = "gmailLoginTest")
	public Object[][] getUserCredentialsTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	

	@Test(priority=2,dataProvider = "gmailLoginTest")
	public void gmailLoginTest(String emailAddress, String password){
		test =reports.startTest("gmailLoginTest");/*Launch the report testRevenueApplicationLogin*/
		test.log(LogStatus.INFO,"Navigate to Gmail Application login" );/*Log Revenue Application URL in report*/
		signinpage.clickSignInButton();
		loginpage.enterEmailAddress(emailAddress);
		loginpage.clickNextButton();
		String attributevalue =testutil.getAttributeElement(loginpage.txtPasswordbox,"name");
		testutil.explicitWaitElementPresence(attributevalue);
		Assert.assertTrue(loginpage.isElementDisplayed());
		loginpage.enterPassword(password);
		loginpage.clickNextButton();
		System.out.println("this is gmail login test case1");
	}
	
	
	

	@AfterMethod
	public void tearDown(){
	 driver.quit();
}
	
}
