package testCases;

import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginFunctionality {
	
	@Test
	public void verifyLoginFunctionality()
	{
		new LoginPage().login();
	}

}
