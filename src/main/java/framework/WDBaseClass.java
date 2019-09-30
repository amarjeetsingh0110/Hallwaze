package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class WDBaseClass {

	public static WebDriver driver=null;

	public Logger logs;
	public static Properties config;
	public static Properties locator;
	public FileInputStream fis;
	public FileInputStream fis2;
	public static WebDriverWait wait=null;
	
	//Base Class 
	//CONSTRUCTOR which is initializing and loading Configuration, logs, and properties files And also initializing WebDriver as well.
	
	 public WDBaseClass() {
		
	
	{
		if(driver==null)
		{
		//load & Configure log File
		try 
		{
			logs= Logger.getLogger(WDBaseClass.class.getName());
			PropertyConfigurator.configure("./src/main/resources/configurations/log4j.properties");
			logs.info("======>>> LOGGER File Loaded Successfully...!!!");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logs.error("======>>> LOGGER FILE NOT LOADED ...!!!!");
			exit();
		}
		
		//load & Configure Config Property File
		try
		{
			config= new Properties();
			fis=new FileInputStream("./src/main/resources/configurations/config.properties");
			config.load(fis);
			logs.info("======>>> CONFIGURATION File Loaded Successfully...!!!");
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			logs.error("======>>> CONFIGURATION FILE NOT LOADED ...!!!!");
			exit();
		}
		
		//load & Configure locator file
		try 
		{
			locator= new Properties();
			fis= new FileInputStream("./src/main/resources/locators/locators.properties");
			locator.load(fis);
			logs.info("======>>> LOCATOR File Loaded Successfully...!!!");
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			logs.error("======>>> LOCATOR FILE NOT LOADED ...!!!!");
			exit();
		}
		
		

		
		//if(driverName.equalsIgnoreCase("Chrome"))
		
		if(driver==null)
		{
			if(config.getProperty("browser").equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();

				//Disabling Notifications, infobars, password save popups 
				Map<String, Object> prefs= new HashedMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications",2);
				prefs.put("credentials_enable_service", 2);
				prefs.put("profile.password_manager_enabled", 2);
				ChromeOptions options= new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver=new ChromeDriver(options);
				logs.info("Chrome Browser Launched Successfully...!!!!");
				
			}
			else if(config.getProperty("browser").equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				logs.info("FireFox  Browser Launched Successfully...!!!!");
				
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(config.getProperty("url"));
			logs.info("Navigated to : " +config.getProperty("url"));
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
			wait= new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicitWait")));
			System.out.println(config.getProperty("implicitWait"));
			System.out.println(config.getProperty("explicitWait"));
			//topNav= new TopNavigation(driver);
			System.out.println(driver);
			
		}
		}
		
	}
}
	
	public static void main(String[] args) {
		new WDBaseClass();
	}
	
	public void exit()
	{
		
		System.exit(0);
	}
}
