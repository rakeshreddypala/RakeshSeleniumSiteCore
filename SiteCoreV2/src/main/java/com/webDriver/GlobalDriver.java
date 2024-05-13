package com.webDriver;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utils.LimitBrowser;

public class GlobalDriver {
	private static GlobalDriver globalDriver;
	private static WebDriver driver;
	private static Set<WebDriver> driverSet = new HashSet<WebDriver>();
	private static Set<LimitBrowser> limitBrowserSet = new HashSet<LimitBrowser>();

	private GlobalDriver(String selectedBrowser) {
		switch(selectedBrowser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;	
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	public synchronized static GlobalDriver getGlobalDriver() {
		return globalDriver;
	}
	public static void setGlobalDriver(String selectedBrowser) {
		globalDriver = new GlobalDriver(selectedBrowser);
	}
	public  WebDriver getDriver() {
		return driver;
	}
	public synchronized static void selectBrowser(String browserFromFeature,String parallelMode,String xmlTestName)  {
		boolean isPresent = false;
		if (parallelMode.equals("tests")) {
			for (LimitBrowser l:limitBrowserSet) {
				if(l.getTestName().equals(xmlTestName)) {
					driver = l.getDriver();
					isPresent = true;
				}			
			}
			if(isPresent==false) {
				setGlobalDriver(browserFromFeature);
				driverSet.add(driver);
				limitBrowserSet.add(new LimitBrowser(xmlTestName,getGlobalDriver().getDriver()));
			}	
		}
		else {
			setGlobalDriver(browserFromFeature);
			driverSet.add(driver);
		}
		System.out.println("driver is "+GlobalDriver.getGlobalDriver().getDriver().toString());
	}
	public static void qutitBrowser() {
		System.out.println("quit all the browsers.... ");	
		for(WebDriver driver:driverSet)
			driver.quit();
		}
}
