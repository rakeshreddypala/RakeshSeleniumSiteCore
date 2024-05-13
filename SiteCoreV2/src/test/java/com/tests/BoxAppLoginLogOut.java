package com.tests;

import static com.webDriver.GlobalDriver.getGlobalDriver;
import static com.webDriver.GlobalDriver.selectBrowser;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.BoxAppHomePage;
import com.pages.BoxAppLoginPage;
import com.utils.Constants;
import com.utils.Print;
import com.utils.ResponseFromPage;

public class BoxAppLoginLogOut extends Print {
	public WebDriver driver;
	 BoxAppLoginPage boxAppLoginPage;
	 ResponseFromPage responseFromPage;
	 BoxAppHomePage boxAppHomePage;
	 //PageTemplate pageTemplate;
	 public String log="";

	 	
	@BeforeClass
	@Parameters("browser")
	public void boxAppLoginPageSteps(String browser, ITestContext context) {
		selectBrowser(browser,context.getSuite().getXmlSuite().getParallel().toString(),context.getName());
		driver = getGlobalDriver().getDriver();
		super.driver = driver;
		boxAppLoginPage = new BoxAppLoginPage(driver);
		boxAppHomePage = new BoxAppHomePage(driver);
		//pageTemplate = new PageTemplate(driver);
	}
	@BeforeMethod
	public void refreshLog() {
		log="";	
	}
	@Test(priority = 1)
	public void navigateToBoxLogin()throws Throwable {
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
