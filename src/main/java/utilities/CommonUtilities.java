package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import framework.WDBaseClass;


public class CommonUtilities extends WDBaseClass{
	//public static ExcelReader reader= new ExcelReader("./src/test/resources/excelData/amarjeetSingh.xlsx");
	
		public static By pathFinder(String path)
		{
			if(path.contains("XPATH~"))
			{
				path=path.split("~")[1];
				return By.xpath(path);
			}
			else if(path.contains("CSS~"))
			{
				path=path.split("~")[1];
				return By.cssSelector(path);
			}
			
			return null;
			
		}
		

		//Utility for Text
		public static void type(String locator, String value)
		{
			driver.findElement(pathFinder(locator)).clear();
			driver.findElement(pathFinder(locator)).sendKeys(value);;
		}
		
		public static void click(String locator)
		{
			driver.findElement(pathFinder(locator)).click();
		}
		
		
		public static void selectElement(String path, String value)
		{
			Select select = new Select(driver.findElement(pathFinder(path)));
			select.selectByVisibleText(value);
		}
		
		
		public static void listSelect(String path, String value)
		{
			Select oSelect= new Select(driver.findElement(pathFinder(path)));
			oSelect.selectByVisibleText(value);
			
		}
		
		
		public void mouseHover(String target)
		{
			Actions action= new Actions(driver);
			action.moveToElement(driver.findElement(pathFinder(config.getProperty(target)))).perform();
			action.moveToElement(driver.findElement(pathFinder(config.getProperty(target)))).perform();
			
		}
		

		public boolean isElementPresent(String locator)
		{
			try {
				//Thread.sleep(4000);
			driver.findElement(pathFinder(config.getProperty("addCustomer")));
			return true;
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage());
				return false;
			}
			
		}
		

		public void nextWindow()
		{
			Set<String> winHandler= driver.getWindowHandles();
			Iterator<String> it= winHandler.iterator();
			
			for(String child: winHandler)
			{
				driver.switchTo().window(it.next());
			}
			
			
		}
		

}
