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

import com.pages.UnicornHomePage;
import com.pages.UnicornSignupAgencyPage;
import com.pages.UnicornSignupSelfPage;
import com.utils.Constants;
import com.utils.DataAccess;
import com.utils.Print;
import com.utils.ResponseFromPage;

public class UnicornSignUp extends Print {
	public WebDriver driver;
	 ResponseFromPage responseFromPage;
	 UnicornHomePage unicornHomePage;
	 UnicornSignupAgencyPage signupAgencyPage;
	 UnicornSignupSelfPage signupSelfPage;
	 //PageTemplate pageTemplate;
	 public String log="";

	 	
	@BeforeClass
	@Parameters("browser")
	public void boxAppLoginPageSteps(String browser, ITestContext context) {
		selectBrowser(browser,context.getSuite().getXmlSuite().getParallel().toString(),context.getName());
		driver = getGlobalDriver().getDriver();
		super.driver = driver;
		unicornHomePage = new UnicornHomePage(driver);
		signupAgencyPage = new UnicornSignupAgencyPage(driver);
		signupSelfPage = new UnicornSignupSelfPage(driver);
		//pageTemplate = new PageTemplate(driver);
	}
	@BeforeMethod
	public void refreshLog() {
		log="";	
	}
	@Test(priority = 1)
	public void navigateToUnicornLogin()throws Throwable {
		responseFromPage=unicornHomePage.navigateToApps(Constants.UNICORN_APP_URL);
		log();
		}
	@Test(priority = 2)
	public void navigateToSignupPage()throws Throwable {
		responseFromPage=unicornHomePage.verifyTitle("UNICORN");
		log();
		responseFromPage=unicornHomePage.clickButton("Signup",unicornHomePage.getByWithKey("Signup"));
		log();
		responseFromPage=unicornHomePage.verifyTitle("UNICORN");
		log();
		}

	@Test(priority = 3,dataProvider="unicornSignup", dataProviderClass = DataAccess.class)
	public void signUp(String agencyName,String agencyAddress,String city,String state,String zipCode,
			String businessType,String dealsType,String businessLocation,String dealingStates,
			String noOfEmployes,String firstName,String lastName,String emailAddress )throws Throwable {
		responseFromPage=unicornHomePage.refreshPage("UNICORN");
		log();
		responseFromPage=signupAgencyPage.enterDetails(agencyName,"Agency Name",signupAgencyPage.getByWithKey("Agency Name"));
		log();
		responseFromPage=signupAgencyPage.enterDetails(agencyAddress,"Agency Address",signupAgencyPage.getByWithKey("Agency Address"));
		log();
		responseFromPage=signupAgencyPage.enterDetails(city,"City",signupAgencyPage.getByWithKey("City"));
		log();
		responseFromPage=signupAgencyPage.clickButton("State",signupAgencyPage.getByWithKey("State"));
		log();
		responseFromPage=signupAgencyPage.select(state,signupAgencyPage.getByWithKey(state,"Range"));
		log();
		responseFromPage=signupAgencyPage.enterDetails(zipCode,"Zip Code",signupAgencyPage.getByWithKey("Zip Code"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Business Type",signupAgencyPage.getByWithKey("Business Type"));
		log();
		responseFromPage=signupAgencyPage.select(businessType,signupAgencyPage.getByWithKey(businessType,"Text"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Deals Type",signupAgencyPage.getByWithKey("Deals Type"));
		log();
		responseFromPage=signupAgencyPage.select(dealsType,signupAgencyPage.getByWithKey(dealsType,"Text"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Business Location",signupAgencyPage.getByWithKey("Business Location"));
		log();
		responseFromPage=signupAgencyPage.select(businessLocation,signupAgencyPage.getByWithKey(businessLocation,"Text"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Dealing States",signupAgencyPage.getByWithKey("Dealing States"));
		log();
		responseFromPage=signupAgencyPage.select(dealingStates,signupAgencyPage.getByWithKey(dealingStates,"Text"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Number Of Employes",signupAgencyPage.getByWithKey("Number Of Employes"));
		log();
		responseFromPage=signupAgencyPage.select(noOfEmployes,signupAgencyPage.getByWithKey(noOfEmployes,"Range"));
		log();
		responseFromPage=signupAgencyPage.clickButton("Next",signupAgencyPage.getByWithKey("Next"));
		log();
		responseFromPage=signupSelfPage.verifyTitle("UNICORN");
		log();
		responseFromPage=signupSelfPage.enterDetails(firstName,"First Name",signupSelfPage.getByWithKey("First Name"));
		log();
		responseFromPage=signupSelfPage.enterDetails(lastName,"Last Name",signupSelfPage.getByWithKey("Last Name"));
		log();
		responseFromPage=signupSelfPage.enterDetails(emailAddress,"Email Id",signupSelfPage.getByWithKey("Email Id"));
		log();
		responseFromPage=signupSelfPage.clickButton("Submit",signupSelfPage.getByWithKey("Submit"));
		log();
		responseFromPage=signupSelfPage.clickButton("Congrats",signupSelfPage.getByWithKey("Congrats"));
		log();
		responseFromPage=unicornHomePage.verifyTitle("UNICORN");
		log();
		responseFromPage=unicornHomePage.refreshPage("UNICORN");
		log();
		Thread.sleep(1000);
		}
	public void log() {
		assertTrue(responseFromPage.isTrue(),responseFromPage.getMessage());
		log = logExtent(log,responseFromPage.getMessage());
	}
}
