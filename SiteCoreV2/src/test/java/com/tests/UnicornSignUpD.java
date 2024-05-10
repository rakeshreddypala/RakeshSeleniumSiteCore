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

import com.pages.PageTemplate;
import com.pages.UnicornHomePage;
import com.pages.UnicornSignupAgencyPage;
import com.pages.UnicornSignupSelfPage;
import com.utils.Constants;
import com.utils.DataAccess;
import com.utils.Print;
import com.utils.ResponseFromPage;

public class UnicornSignUpD extends Print  {

	public WebDriver[] drivers;
	public WebDriver driver;
	 ResponseFromPage[] responseFromPage;
	 UnicornHomePage[] unicornHomePage;
	 UnicornSignupAgencyPage[] signupAgencyPage;
	 UnicornSignupSelfPage[] signupSelfPage;
	 PageTemplate[] pageTemplate;
	 public String[] logs;
	 public String log="";

	 	
	@BeforeClass
	@Parameters("browser")
	public void boxAppLoginPageSteps(String browser, ITestContext context) {
		int threadCount = context.getSuite().getXmlSuite().getDataProviderThreadCount();
		System.out.println("threadcount is "+threadCount);
		responseFromPage = new ResponseFromPage[threadCount];
		unicornHomePage = new UnicornHomePage[threadCount];
		signupAgencyPage = new UnicornSignupAgencyPage[threadCount];
		signupSelfPage = new UnicornSignupSelfPage[threadCount];
		pageTemplate = new PageTemplate[threadCount];
		drivers = new WebDriver[threadCount];
		logs = new String[threadCount];
		for(int i=0;i<threadCount;i++) {
			selectBrowser(browser,context.getSuite().getXmlSuite().getParallel().toString(),context.getName());
			drivers[i] = getGlobalDriver().getDriver();
		}
	}

	@BeforeMethod
	public void refreshLog() {
		log="";	
	}
	@Test(priority = 1,dataProvider="unicornSignupD", dataProviderClass = DataAccess.class)
	public void signUp(String thread,String browser,String agencyName,String agencyAddress,String city,String state,String zipCode,
			String businessType,String dealsType,String businessLocation,String dealingStates,
			String noOfEmployes,String firstName,String lastName,String emailAddress )throws Throwable {
		int t = Integer.parseInt(thread)-1;
		driver = drivers[t];
		super.driver = drivers[t];
		pageTemplate[t] = new PageTemplate(drivers[t]);
		unicornHomePage[t] = new UnicornHomePage(drivers[t]);
		signupAgencyPage[t] = new UnicornSignupAgencyPage(drivers[t]);
		signupSelfPage[t] = new UnicornSignupSelfPage(drivers[t]);
		responseFromPage[t]=pageTemplate[t].navigateToApps(Constants.UNICORN_APP_URL);
		log(t);
		responseFromPage[t]=pageTemplate[t].verifyTitle("UNICORN");
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Signup",unicornHomePage[t].getByWithKey("Signup"));
		log(t);
		responseFromPage[t]=pageTemplate[t].verifyTitle("UNICORN");
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(agencyName,"Agency Name",signupAgencyPage[t].getByWithKey("Agency Name"));
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(agencyAddress,"Agency Address",signupAgencyPage[t].getByWithKey("Agency Address"));
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(city,"City",signupAgencyPage[t].getByWithKey("City"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("State",signupAgencyPage[t].getByWithKey("State"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(state,signupAgencyPage[t].getByWithKey(state,"Range"));
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(zipCode,"Zip Code",signupAgencyPage[t].getByWithKey("Zip Code"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Business Type",signupAgencyPage[t].getByWithKey("Business Type"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(businessType,signupAgencyPage[t].getByWithKey(businessType,"Text"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Deals Type",signupAgencyPage[t].getByWithKey("Deals Type"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(dealsType,signupAgencyPage[t].getByWithKey(dealsType,"Text"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Business Location",signupAgencyPage[t].getByWithKey("Business Location"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(businessLocation,signupAgencyPage[t].getByWithKey(businessLocation,"Text"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Dealing States",signupAgencyPage[t].getByWithKey("Dealing States"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(dealingStates,signupAgencyPage[t].getByWithKey(dealingStates,"Text"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Number Of Employes",signupAgencyPage[t].getByWithKey("Number Of Employes"));
		log(t);
		responseFromPage[t]=pageTemplate[t].select(noOfEmployes,signupAgencyPage[t].getByWithKey(noOfEmployes,"Range"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Next",signupAgencyPage[t].getByWithKey("Next"));
		log(t);
		responseFromPage[t]=pageTemplate[t].verifyTitle("UNICORN");
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(firstName,"First Name",signupSelfPage[t].getByWithKey("First Name"));
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(lastName,"Last Name",signupSelfPage[t].getByWithKey("Last Name"));
		log(t);
		responseFromPage[t]=pageTemplate[t].enterDetails(emailAddress,"Email Id",signupSelfPage[t].getByWithKey("Email Id"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Submit",signupSelfPage[t].getByWithKey("Submit"));
		log(t);
		responseFromPage[t]=pageTemplate[t].clickButton("Congrats",signupSelfPage[t].getByWithKey("Congrats"));
		log(t);
		responseFromPage[t]=pageTemplate[t].verifyTitle("UNICORN");
		log(t);
		Thread.sleep(1000);
		}
	public void log(int t) {
		assertTrue(responseFromPage[t].isTrue(),responseFromPage[t].getMessage());
		logs[t] = logExtent(logs[t],responseFromPage[t].getMessage());
	}
}
