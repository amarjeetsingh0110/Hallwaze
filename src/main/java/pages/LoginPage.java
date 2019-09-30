package pages;

import framework.WDBaseClass;
import utilities.CommonUtilities;

public class LoginPage extends WDBaseClass{
	
	public void login()
	{
		CommonUtilities.type(locator.getProperty("loginText"), config.getProperty("userName"));
		System.out.println(locator.getProperty("loginText"));
		System.out.println(config.getProperty("userName"));
		CommonUtilities.click(locator.getProperty("nextButton"));
		CommonUtilities.type(locator.getProperty("passwordText"), config.getProperty("passWord"));
		CommonUtilities.click(locator.getProperty("loginButton"));
		
	}


}

