package seleniumpackage;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class SeleniumDemo {
	
		WebDriver driver;
		WebDriverWait wait;

		@Before
		public void setUp() throws Exception {
			
			System.setProperty("webdriver.chrome.driver", "D:/Selenium/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
		}

		
		@SuppressWarnings("deprecation")
		@Test
		public void test() throws InterruptedException {
			//Launching the browser
			driver.get("http://www.wikipedia.org");
			
			//waiting for the browser to load
			wait = new WebDriverWait(driver, 30);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			//Entering OOP into search text box
			driver.findElement(By.name("search")).sendKeys("OOP");
			Thread.sleep(10000);
			List <WebElement> listItems = driver.findElements(By.xpath("//div[contains(@id,'typeahead-suggestions')]")); 
			wait.until(ExpectedConditions.visibilityOfAllElements(listItems));
			listItems.get(0).click(); 
			String expectedTitle = "OOP - Wikipedia";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(expectedTitle, actualTitle);
			driver.findElement(By.partialLinkText("Object-oriented programming")).click();
			Assert.assertEquals(expectedTitle, actualTitle);
			Thread.sleep(5000);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='mw-content-text']/div/div[17]/table/tbody/tr[1]/th/span/a")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='mw-content-text']/div/div[17]/table/tbody/tr[6]/td/div/ul/li[3]/a")).click();
			Thread.sleep(5000);
		}
		
		@After
		public void tearDown() throws Exception {
			driver.quit();
		}


	}


