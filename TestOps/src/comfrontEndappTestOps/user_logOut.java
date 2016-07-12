package comfrontEndappTestOps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class user_logOut {
	
	public static WebDriver driver;
		
		@BeforeTest
		@Parameters("browser")
		public void setBrowser(String browser) throws Exception{
		if (browser.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			System.out.println("Firefox is being used");
		}
		else if (browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C://Users//l.enriquez.rodriguez//Documents//Eclipse Projects//chromedriver_win32//chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Chrome is being used");
		}
		else if (browser.equalsIgnoreCase("Internet Explorer")){
			System.setProperty("webdriver.chrome.driver", "C://Users//l.enriquez.rodriguez//Documents//Eclipse Projects//IEDriverServer.exe");
			driver = new ChromeDriver();
			System.out.println("IE is being used");
		}
		driver.get(testData.appURL);	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}

		@Test (priority=1)
		public void loginTest(){	
			driver.findElement(By.xpath("//*[@id='account-menu']")).click();
			driver.findElement(By.xpath("//*[@id='login']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys(testData.User);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testData.pwd);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[3]/form/button")).click();
			String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).getText();
			String expectedResult ="You are logged in as user \"admin\".";
			Assert.assertEquals(actualResult, expectedResult);
			
		}
		
		@Test (priority=2)
		public void logoutTest(){
			driver.findElement(By.xpath("//*[@id='account-menu']")).click();
			driver.findElement(By.xpath("//*[@id='logout']")).click();
			String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]")).getText();
			Assert.assertNotEquals(actualResult, "You are logged in as user \"admin\"");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		@AfterTest
		public void closingTest(){
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("We are closing the window, method closingTest");
			driver.quit();
		} 		
	}
