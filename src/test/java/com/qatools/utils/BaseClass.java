package com.qatools.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {
		public static WebDriver driver;
		public static void setUp() {
			ConfigsReader.readProperties(Constants.CREDENTIALS_FILEPATH);    // "src/test/resources/configs/credentials.properties";
			String browser=ConfigsReader.getProperty("browser");
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
				driver=new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
				driver=new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.getProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			} else if (browser.equalsIgnoreCase("edge")) {
				System.getProperty("webdriver.edge.driver", "C:/Windows/System32/MicrosoftWebDriver.exe");
				driver=new EdgeDriver();
			}
			else {System.out.println("browser given is incorrect");
			}
		
			driver.get(ConfigsReader.getProperty("url"));
			driver.manage().window().fullscreen();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("a[data-cli_action='accept']")).click();
		}
}
