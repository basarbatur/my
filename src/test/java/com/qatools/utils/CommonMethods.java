package com.qatools.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {
	
	/**
	 * This method will select a specified value from a drop down menu
	 * 
	 * @param Select element
	 * @param String text
	 */
	public static void selectValueFromDD (WebElement element, String text) {
		Select select=new Select(element);
		List<WebElement> options= select.getOptions();
		boolean isSelected= false;
		for (WebElement option: options) {
			String optionText= option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("option with text "+text+" has been selected");
				isSelected= true;
				break;
			}
		}
		if (!isSelected) {
			System.out.println("Option with text " + text + " is not available");
		}
	}
	
	/**
	 * This method will select a specified value from a drop down menu by its index
	 * 
	 * @param Select element
	 * @param        int index
	 */
	public static void selectValueFromDD(WebElement element, int index) {
		Select select=new Select(element);
		List<WebElement> options=select.getOptions();
		if (options.size()>index) {
			select.selectByIndex(index);
		} else {
			System.out.println("Invalid index has been passed");
		}
	}
		
	/**
	 * 
	 * @param Webelement element
	 * @param String     value
	 */	
	public static void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	/**
	 * This method will accept alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void acceptAlert() {
		try {
			Alert alert= driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}	
	
	/**
	 * This method will dismiss alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void dismissAlert() {
		try {
			Alert alert= driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}
	
	/**
	 * This method will get text of an alert
	 * 
	 * @return String text
	 */
	public static String getAlertText() {
		try {
			Alert alert= driver.switchTo().alert();
			return alert.getText();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
			return null;
		}
	}
	
	/**
	 * This method will switch control to the specific frame
	 * 
	 * @param nameOrId frame id or frame name
	 */
	public static void switchToFrame(String nameOrId) {
		try {
		driver.switchTo().frame(nameOrId);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	
	/**
	 * This method will switch control to the specified frame
	 * 
	 * @param frame element
	 */
	public static void switchToFrame (WebElement element) {
		try {
		driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
		System.out.println("Frame is not present");
		}
		}
	
	/**
	 * This method will switch control to the specified frame
	 * 
	 * @param frame index
	 */
	public static void switchToFrame (int index) {
		try {
		driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
		System.out.println("Frame is not present");
		}
		}
	
	/**
	 * This method will wait for element to be visible
	 * 
	 * @param WebElement element
	 * @param            int time
	 */
	public static void waitForElementBeVisible(WebElement element, int time) {
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForElementBeVisible(By locator, int time) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public static void waitForElementBeClickable(WebElement element, int time) {
		WebDriverWait wait =new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForElementBeClickable(By locator, int time) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	


	/**
	 * This method performs takes screenshot of the screen
	 * 
	 * Move image file to new destination
	 * File DestFile=new File(fileWithPath);
	 * Copy file at destination
	 * FileUtils.copyFile(SrcFile, DestFile);
	
	 * */ 
	public static byte[] takeScreenshot() {
		TakesScreenshot ts=(TakesScreenshot) driver;
		byte[] screen=ts.getScreenshotAs(OutputType.BYTES);
		return screen;
	}
	//	public static String takeScreenshot(String fileName) {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File scr = ts.getScreenshotAs(OutputType.FILE);
//
//		String dest=System.getProperty("user.dir")+"/target/screenshots/"+ fileName + ".png";
//		
//		try {
//			FileUtils.copyFile(scr, new File(dest));
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Unable to take screesnhot");
//		}
//		return dest;
//	}
	
	/**
	 * This method performs Scroll Down on application
	 * 
	 * i.e. js.executeScript("window.scrollBy(0,250)");
	 * */ 
	public static void scrollDown(int pixels) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," +pixels+ ")");
	}
	
	/**
	 * This method performs Scroll UP on application
	 * 
	 * i.e. js.executeScript("window.scrollBy(0, -400)");
	 * */ 
	public static void scrollUp(int pixels) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("windows.scrollBy(0,-"+pixels+")");
	}
	
	/**
	 * This method performs an application to specified elements
	 * scrolling to the element
	 * */ 
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * This method handles hidden elements, clicks hidden links
	 * */
	public static void jsClick(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * This method clicks element
	 * */
	public static void click(WebElement element) {
		element.click();
	}
	
	/**
	 * This method selects lists
	 * */
	public static void selectList(WebElement element, String text) {
		List<WebElement> listLocations= element.findElements(By.tagName("li"));
		for (WebElement li:listLocations) {
			String liText=li.getAttribute("innerHTML");
			
			if(liText.contains(text)) {
				li.click();
				break;
		}
		}
	}
	
	/**
	 * This method shows broken links on the page
	 * */
	public static void brokenLinksVerification() {
		//1. grab all links
		List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println("Total number of links= "+links.size());
		
		//2.get href attribute and try links
		for (WebElement link:links) {
			String linkURL=link.getAttribute("href");
						
			try {
				URL url=new URL(linkURL);
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				int code=conn.getResponseCode();
				if(code==200) {
					System.out.println("Link is valid "+link.getText());
				}else {
					System.out.println("Link is NOT valid "+link.getText());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
