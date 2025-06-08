package com.mystore.actiondriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;


import com.mystore.base.BaseClass;

public class Action extends BaseClass {
	// how log webdriver should waits for webelements to load
	public static void implicitWait(WebDriver driver, int timeOutinSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOutinSeconds));
	}
	public static void explicitWait(WebDriver driver, WebElement element, int timeOutinSeconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutinSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOutInseconds) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOutInseconds));
	}

	// the below click method forst move to webelement hover mouse on it and then
	// click is by creating a build and then perform steps in sequence
	public static void click(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();

	}

//My own method test it when you run scripts
//	public boolean findElement(WebDriver driver,WebElement ele) {
//		boolean flag=false;
//		try {
//			if(ele.isDisplayed()==true) {
//				System.out.println("Element found");
//				flag=true;
//			}
//		}
//		catch(Exception e){
//			System.out.println("Exception thrown by isdisplay element not found");
//			flag=false;
//		}
//		return flag;
//		
//	}
	// this method return true if the webelement is found in the page
	public static boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}
// using the find element method of above we are writing isdisplayed method to verify if and element is displayed or not in page like logo
//by using findElements() in isDisplayed we are making sure first elemnt is present the we are checking displayed or not
	public static boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}
	public static boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Location Not found");
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully entered value");
			} else {
				System.out.println("Unable to enter value");
			}

		}
		return flag;
	}
	
	public static void selectValueFromDropDown(WebElement ele,String value) {
		Select select = new Select(ele);
		select.selectByValue(value);
		 			
	}
	public static void selectCheckBoxValue(WebElement ele) {
		//first check if box is selected if not select it
		if(!ele.isSelected()) {
			ele.click();
		}	
	}
	public static void uploadAFile(WebElement ele, String filePath) {
		ele.sendKeys(filePath);
	}
	public static boolean switchToFrameByIndex(WebDriver driver,int frameId) {
		boolean flag = false;
		try {
		driver.switchTo().frame(frameId);
		flag= true;
		return flag;
		}catch(Exception e) {
			e.printStackTrace();
			flag=false;
			return flag;
		}finally {
			if(flag) {
				System.out.println("switch to " +frameId+ " frame id");
			}else {
				System.out.println(frameId+" not selected");
				
			}
		}
		
	}
	public static boolean alertAccept(WebDriver driver) {
		
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	//the bewlow method scroll until the WebElement is in view
	public static void scrollByVisibilityOfElement(WebDriver driver,WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor)driver;//type casting driver to javaScriptExecutor type
		js.executeScript("arguments[0].scrollIntoView();",ele);
	}
	//the below method scroll till bottom of web page
	public static void scrollDownToFotterOfPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	public static void jsClick(WebDriver driver,WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	

}
