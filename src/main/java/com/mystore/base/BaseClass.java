package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import com.mystore.actiondriver.Action;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	
	//creating instance of ThreadLocal
	public static ThreadLocal<RemoteWebDriver> driver= new ThreadLocal<RemoteWebDriver>();
	
	//return the current driver in use for thread
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	
	@BeforeSuite
	public void beforeSuite() {
		DOMConfigurator.configure("log4j.xml");
	}

	@BeforeTest
	public void loaddata() {
		try {
			
		prop = new Properties();
		System.out.println("super constructor invoked");
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\Configuration\\Config.properties");
		
		prop.load(ip);
			System.out.println("driver: "+driver);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	//launching browse based on value of browse in config.properties file
	public static void launchApp() {
		WebDriverManager.chromedriver().setup();
		
		//reading browser from config
		String browsername = prop.getProperty("browser");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options));
		}else if(browsername.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		}else if (browsername.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		}
		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(), 30);
		Action.pageLoadTimeOut(getDriver(), 60);
		String url = prop.getProperty("url");
		getDriver().get(url);
	}
	
	
}
