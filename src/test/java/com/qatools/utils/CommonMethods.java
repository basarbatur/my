package com.qatools.utils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass {

	/**
	 * This method will select a specified value from a drop down menu
	 * selectByVisibleText
	 * 
	 * @param Select element
	 * @param String text
	 */
	public static void selectValueFromDD(WebElement element, String text) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		boolean isSelected = false;
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("option with text " + text + " has been selected");
				isSelected = true;
				break;
			}
		}
		if (!isSelected) {
			System.out.println("Option with text " + text + " is not available");
		}
	}

	/**
	 * This method will select a specified value from a drop down menu by its index
	 * selectByIndex
	 * 
	 * @param Select element
	 * @param int    index
	 */
	public static void selectValueFromDD(WebElement element, int index) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		if (options.size() > index) {
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
		waitForElementBeVisible(element,30);
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
			Alert alert = driver.switchTo().alert();
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
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}

	/**
	 * This method will get text of an alert
	 * 
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	 */
	public static String getAlertText() {
		try {
			Alert alert = driver.switchTo().alert();
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
	public static void switchToFrame(WebElement element) {
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
	public static void switchToFrame(int index) {
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
	 * @param int        time
	 */
	public static void waitForElementBeVisible(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementBeVisible(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementBeClickable(WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementBeClickable(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method performs takes screenshot of the screen
	 * 
	 * Move image file to new destination File DestFile=new File(fileWithPath); Copy
	 * file at destination FileUtils.copyFile(SrcFile, DestFile);
	 * 
	 */
	public static byte[] takeScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screen = ts.getScreenshotAs(OutputType.BYTES);
		return screen;
	}

	public static byte[] takeScreenshot(String fileName) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
		String timeStamp = sdf.format(date.getTime());

		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screen= ts.getScreenshotAs(OutputType.BYTES);
		File scr = ts.getScreenshotAs(OutputType.FILE);

		String dest = System.getProperty("user.dir") + "/target/screenshots/" + fileName +timeStamp+ ".png";

		try {
			FileUtils.copyFile(scr, new File(dest));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to take screesnhot");
		}
		return screen;
	}

	/**
	 * This method performs Scroll Down on application
	 * 
	 * i.e. js.executeScript("window.scrollBy(0,250)");
	 */
	public static void scrollDown(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	/**
	 * This method performs Scroll UP on application
	 * 
	 * i.e. js.executeScript("window.scrollBy(0, -400)");
	 */
	public static void scrollUp(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("windows.scrollBy(0,-" + pixels + ")");
	}

	/**
	 * This method performs an application to specified elements scrolling to the
	 * element
	 */
	public static void scrollIntoElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method handles hidden elements, clicks hidden links
	 */
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method clicks element
	 */
	public static void click(WebElement element) {
		waitForElementBeClickable(element, 30);
		element.click();
	}

	/**
	 * This method selects lists
	 */
	public static void selectList(WebElement element, String text) {
		List<WebElement> listLocations = element.findElements(By.tagName("li"));
		for (WebElement li : listLocations) {
			String liText = li.getAttribute("innerHTML");

			if (liText.contains(text)) {
				li.click();
				break;
			}
		}
	}

	/**
	 * This method shows broken links on the page
	 * 
	 * @author Basar
	 */
	public static void brokenLinksVerification() {
		// 1. grab all links
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total number of links= " + links.size());

		// 2.get href attribute and try links
		for (WebElement link : links) {
			String linkURL = link.getAttribute("href");

			try {
				URL url = new URL(linkURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				int code = conn.getResponseCode();
				if (code == 200) {
					System.out.println("Link is valid " + link.getText());
				} else {
					System.out.println("Link is NOT valid " + link.getText());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * This method double clicks on the element
	 * 
	 * @author Basar
	 * @param element
	 */
	public static void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	/**
	 * This method Performs a context-click (right-click) on the element
	 * 
	 * @author Basar
	 * @param element
	 * 
	 *                *
	 */
	public static void contextClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}

	/**
	 * This method Clicks at the source location and moves to the location of the
	 * target element before releasing the mouse. source (element to grab, target –
	 * element to release).
	 * 
	 * @author Basar
	 * @param source element, target element
	 * 
	 * 
	 * 
	 * @additional methods dragAndDrop(source, target): Clicks at the source
	 *             location and moves to the location of the target element before
	 *             releasing the mouse. source (element to grab, target – element to
	 *             release).
	 * 
	 *             dragAndDropBy(source, xOffset, yOffset): Performs click-and-hold
	 *             at the source location, shifts by a given offset value, then
	 *             frees the mouse. (X offset – to shift horizontally, Y Offset – to
	 *             shift vertically). moveByOffset(x-offset, y-offset): Shifts the
	 *             mouse from its current position (or 0,0) by the given offset.
	 *             x-offset – Sets the horizontal offset (negative value – shifting
	 *             the mouse to the left), y-offset – Sets the vertical offset
	 *             (negative value – shifting the mouse to the up).
	 */
	public static void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).build().perform();
	}

	/**
	 * This method shifts the mouse to the center of the element.
	 * 
	 * @author Basar
	 * @param element
	 * 
	 *                *
	 */
	public static void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * This method pushes enter button
	 * 
	 * @param Webelement element
	 * @param
	 */
	public static void sendEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}



}
