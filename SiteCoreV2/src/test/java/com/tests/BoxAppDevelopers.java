package com.tests;

import static com.webDriver.GlobalDriver.getGlobalDriver;
import static com.webDriver.GlobalDriver.selectBrowser;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.BoxAppDevelopersPage;
import com.pages.BoxAppHomePage;
import com.pages.BoxAppLoginPage;
import com.utils.Constants;
import com.utils.Print;
import com.utils.ResponseFromPage;

public class BoxAppDevelopers extends Print {
	 WebDriver driver;
	 BoxAppLoginPage boxAppLoginPage;
	 BoxAppDevelopersPage boxAppDevelopersPage;
	 BoxAppHomePage boxAppHomePage;
	 //PageTemplate pageTemplate;
	 ResponseFromPage responseFromPage;
	 boolean isDeveloperTokenAvailable;
	 public String log="";
	 public String xmlTestName;
	
	@BeforeClass 
	@Parameters("browser")  
	public void boxAppDevelopersSteps(String browser,ITestContext context) {
		selectBrowser(browser,context.getSuite().getXmlSuite().getParallel().toString(),context.getName());
		driver = getGlobalDriver().getDriver();
		super.driver = driver;
		boxAppLoginPage = new BoxAppLoginPage(driver);
		boxAppDevelopersPage = new BoxAppDevelopersPage(driver);
		boxAppHomePage = new BoxAppHomePage(driver);
		//pageTemplate = new PageTemplate(driver);
		isDeveloperTokenAvailable=false;
		}
	@BeforeMethod
	public void refreshLog() {
		log="";	
	}

	@Test(priority = 1)
	public void navigateToBox()throws Throwable {
		responseFromPage=boxAppLoginPage.navigateToApps(Constants.BOX_APP_URL);
		log();
		}
	@Parameters({"emailAddress","password"})
	@Test(priority = 2)
	public void login(String emailAddress, String password)throws Throwable {
		responseFromPage=boxAppLoginPage.verifyTitle("Box | Login");
		log();
		responseFromPage=boxAppLoginPage.enterDetails(emailAddress,"Email Id",boxAppLoginPage.getByWithKey("Email Id"));
		log();
		responseFromPage=boxAppLoginPage.clickButton("Next",boxAppLoginPage.getByWithKey("Next"));
		log();
		responseFromPage=boxAppLoginPage.enterDetails(password,"Password",boxAppLoginPage.getByWithKey("Password"));
		log();
		responseFromPage=boxAppLoginPage.clickButton("Login",boxAppLoginPage.getByWithKey("Login"));
		log();
		}
	
	@Test(priority = 3)
	public void clickDevConsoleButton()throws Throwable {
		responseFromPage=boxAppHomePage.verifyTitle("All Files | Powered by Box");
		log();
		responseFromPage=boxAppHomePage.clickButton("Dev Console",boxAppHomePage.getByWithKey("Dev Console"));
		log();
		responseFromPage=boxAppHomePage.verifyTitleOfActiveWindow("Box Developers");
		log();
		responseFromPage=boxAppHomePage.clickButton("App",boxAppDevelopersPage.getByWithKey("App"));
		log();
		responseFromPage=boxAppDevelopersPage.verifyTitle("FirstBoxDevApp | Box Developers");
		log();
		responseFromPage=boxAppDevelopersPage.clickButton("Configuration",boxAppDevelopersPage.getByWithKey("Configuration"));
		log();
		}
	@Test(priority = 4)
	public void isDeveloperTokenAvailable()throws Throwable {
		responseFromPage=boxAppDevelopersPage.isDeveloperTokenAvailable("Developer Token");
		assertTrue(true,responseFromPage.getMessage());
		isDeveloperTokenAvailable=responseFromPage.isTrue();
		log = logExtent(log,responseFromPage.getMessage());		
		}
	@Test(priority = 5)
	public void clickGenerateDeveloperTokenButton()throws Throwable {
		if(isDeveloperTokenAvailable==false) {
			responseFromPage=boxAppDevelopersPage.clickButton("Generate Developer Token",boxAppDevelopersPage.getByWithKey("Generate Developer Token"));
			Thread.sleep(2000);
			log();
		}
		else {
			Assert.assertTrue(true, "Developer token is available");
			log = logExtent(log,"Developer token is available");
		}
		}
	@Test(priority = 6)
	public void copyDeveloperToken() throws Throwable {
		responseFromPage=boxAppDevelopersPage.copyDeveloperToken("Developer Token");
		log();
		log = logExtent(log,"developer token is "+responseFromPage.getExtentMessage());
		Thread.sleep(1000);
		responseFromPage=boxAppHomePage.verifyTitleOfOriginalWindow("All Files | Powered by Box");
		log();
		Thread.sleep(1000);
		}
	@Test(priority = 7)
	public void logOut()throws Throwable {
		responseFromPage=boxAppHomePage.clickButton("Profile",boxAppHomePage.getByWithKey("Profile"));
		log();
		Thread.sleep(1000);
		responseFromPage=boxAppHomePage.clickButton("LogOut",boxAppHomePage.getByWithKey("LogOut"));
		log();
		Thread.sleep(1000);
		}
	public void log() {
		assertTrue(responseFromPage.isTrue(),responseFromPage.getMessage());
		log = logExtent(log,responseFromPage.getMessage());
	}
}
