package com.tests;

import static com.webDriver.GlobalDriver.getGlobalDriver;
import static com.webDriver.GlobalDriver.selectBrowser;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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

	HashMap<Integer,ResponseFromPage> responseFromPage;
	HashMap<Integer,UnicornHomePage> unicornHomePage;
	HashMap<Integer,UnicornSignupAgencyPage> signupAgencyPage;
	HashMap<Integer,UnicornSignupSelfPage> signupSelfPage;
	HashMap<Integer,PageTemplate> pageTemplate;
	public HashMap<Integer,String> logs;
	public HashMap<Integer,WebDriver> drivers;
	public String browser;
	public String parallelMode;
	public String xmlTestName;
	
	@BeforeClass
	@Parameters("browser")
	public void boxAppLoginPageSteps(String browser, ITestContext context) {
		int threadCount = context.getSuite().getXmlSuite().getDataProviderThreadCount();
		System.out.println("threadcount is "+threadCount);
		drivers = new HashMap<>();
		responseFromPage = new HashMap<>();
		unicornHomePage = new HashMap<>();
		signupAgencyPage = new HashMap<>();
		signupSelfPage = new HashMap<>();
		pageTemplate = new HashMap<>();
		logs = new HashMap<>();
		this.browser = browser;
		parallelMode = context.getSuite().getXmlSuite().getParallel().toString();
		xmlTestName = context.getName();
	}
	@Test(priority = 1,dataProvider="unicornSignupD", dataProviderClass = DataAccess.class)
	public void signUp(String thread,String browser,String agencyName,String agencyAddress,String city,String state,String zipCode,
			String businessType,String dealsType,String businessLocation,String dealingStates,
			String noOfEmployes,String firstName,String lastName,String emailAddress )throws Throwable {
		Integer t = Integer.parseInt(thread)-1;
		selectBrowser(this.browser,parallelMode,xmlTestName);
		drivers.put(t,getGlobalDriver().getDriver());
		super.driver = drivers.get(t);
		pageTemplate.put(t, new PageTemplate(drivers.get(t)));	
		unicornHomePage.put(t, new UnicornHomePage(drivers.get(t)));		
		signupAgencyPage.put(t, new UnicornSignupAgencyPage(drivers.get(t)));	
		signupSelfPage.put(t, new UnicornSignupSelfPage(drivers.get(t)));
		responseFromPage.put(t, unicornHomePage.get(t).navigateToApps(Constants.UNICORN_APP_URL));
		log(t);
		responseFromPage.put(t,unicornHomePage.get(t).verifyTitle("UNICORN"));
		log(t);
		responseFromPage.put(t, unicornHomePage.get(t).clickButton("Signup",unicornHomePage.get(t).getByWithKey("Signup")));
		log(t);
		responseFromPage.put(t,unicornHomePage.get(t).verifyTitle("UNICORN"));
		log(t);
		responseFromPage.put(t, signupAgencyPage.get(t).enterDetails(agencyName,"Agency Name",signupAgencyPage.get(t).getByWithKey("Agency Name")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).enterDetails(agencyAddress,"Agency Address",signupAgencyPage.get(t).getByWithKey("Agency Address")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).enterDetails(city,"City",signupAgencyPage.get(t).getByWithKey("City")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("State",signupAgencyPage.get(t).getByWithKey("State")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(state,signupAgencyPage.get(t).getByWithKey(state,"Range")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).enterDetails(zipCode,"Zip Code",signupAgencyPage.get(t).getByWithKey("Zip Code")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Business Type",signupAgencyPage.get(t).getByWithKey("Business Type")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(businessType,signupAgencyPage.get(t).getByWithKey(businessType,"Text")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Deals Type",signupAgencyPage.get(t).getByWithKey("Deals Type")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(dealsType,signupAgencyPage.get(t).getByWithKey(dealsType,"Text")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Business Location",signupAgencyPage.get(t).getByWithKey("Business Location")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(businessLocation,signupAgencyPage.get(t).getByWithKey(businessLocation,"Text")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Dealing States",signupAgencyPage.get(t).getByWithKey("Dealing States")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(dealingStates,signupAgencyPage.get(t).getByWithKey(dealingStates,"Text")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Number Of Employes",signupAgencyPage.get(t).getByWithKey("Number Of Employes")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).select(noOfEmployes,signupAgencyPage.get(t).getByWithKey(noOfEmployes,"Range")));
		log(t);
		responseFromPage.put(t,signupAgencyPage.get(t).clickButton("Next",signupAgencyPage.get(t).getByWithKey("Next")));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).verifyTitle("UNICORN"));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).enterDetails(firstName,"First Name",signupSelfPage.get(t).getByWithKey("First Name")));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).enterDetails(lastName,"Last Name",signupSelfPage.get(t).getByWithKey("Last Name")));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).enterDetails(emailAddress,"Email Id",signupSelfPage.get(t).getByWithKey("Email Id")));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).clickButton("Submit",signupSelfPage.get(t).getByWithKey("Submit")));
		log(t);
		responseFromPage.put(t,signupSelfPage.get(t).clickButton("Congrats",signupSelfPage.get(t).getByWithKey("Congrats")));
		log(t);
		responseFromPage.put(t,unicornHomePage.get(t).verifyTitle("UNICORN"));
		log(t);
		Thread.sleep(1000);
		}
	public void log(int t) {
		assertTrue(responseFromPage.get(t).isTrue(),responseFromPage.get(t).getMessage());
		logs.put(t, logExtent(logs.get(t),responseFromPage.get(t).getMessage()));
	}
}
