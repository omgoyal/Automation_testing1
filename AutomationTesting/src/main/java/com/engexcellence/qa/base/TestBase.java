package com.engexcellence.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.engexcellence.qa.config.PropertiesFileReader;
import com.engexcellence.qa.util.TestUtil;
import com.engexcellence.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static PropertiesFileReader propertyfile;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListner;
	
	public TestBase() {
		
		try {
		propertyfile =new  PropertiesFileReader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\engexcellence\\qa\\config\\config.properties");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method name
	 */
	public static void initialization(){
		
		String browserName = propertyfile.getPropertyfileValue("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\omgoyal\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			driver = new ChromeDriver(options);
			
			//driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "/driver/firefoxdriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Create a object of EventListnerHandler to register it with EventfiringWebdriver
		eventListner = new WebEventListener();
		e_driver.register(eventListner);
		driver =e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
	} 

}
