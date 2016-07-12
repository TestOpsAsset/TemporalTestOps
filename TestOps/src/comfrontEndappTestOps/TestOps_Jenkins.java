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

public class TestOps_Jenkins {
	
	public static WebDriver driver;
		
		@BeforeTest
		@Parameters("browser")
		
	
		//----  Below lines will set the driver based on the browser being passed in "browser" variable ----
		
		public void setBrowser(String browser) throws Exception{
		if (browser.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			System.out.println("Firefox is being used");
		}
		else if (browser.equalsIgnoreCase("Chrome")){
			System.setProperty(testData.Chrome1, testData.Chrome2);
			driver = new ChromeDriver();
			System.out.println("Chrome is being used");
		}
		else if (browser.equalsIgnoreCase("Internet Explorer")){
			System.setProperty("webdriver.chrome.driver", "C://Users//l.enriquez.rodriguez//Documents//Eclipse Projects//IEDriverServer.exe");
			driver = new ChromeDriver();
			System.out.println("IE is being used");
		}
				
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(testData.appURL);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("this is the beforeMethod");
		}
		
		@Test (priority=1)
		public void loginTest(){	
			System.out.println("loginTest method has started");
			System.out.println("-------------------------------------");
			driver.findElement(By.xpath("//*[@id='account-menu']")).click();
			driver.findElement(By.xpath("//*[@id='login']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys(testData.User);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testData.pwd);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[3]/form/button")).click();
			String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).getText();
			Assert.assertEquals(actualResult, "You are logged in as user \"admin\".");
		}
		
		/*@Test (priority=2)
		public void newUser(){
			System.out.println("newUser method has started");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.id("admin-menu")).click();
			driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[4]/ul/li[1]/a")).click(); //User management option
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/button")).click(); //Create a new user button
			int serial = (int)(Math.random()*500+1);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[2]/input")).sendKeys(testData.new_User + serial);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[3]/input")).sendKeys(testData.new_Name+ serial);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[4]/input")).sendKeys(testData.new_LastName + serial);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[5]/input")).sendKeys(testData.new_Name+"."+testData.new_LastName+ serial +"@accenture.com");
			driver.findElement(By.id("activated")).click();
									//	"/html/body/div[1]/div/div/form/div[2]/div[2]/input"
			// The role of the user will be selected based on the generated serial #
			if (serial%2 != 0){
				// Selecting USER ROLE
				driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[7]/select/option[1]")).click(); 
			}else{
				// Selecting ADMIN ROLE
				driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[7]/select/option[2]")).click(); 
			}	
			driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[3]/button[2]")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			System.out.println("newUser method has been completed");
			System.out.println("-------------------------------------");
		}
		
		*/
		@Test (priority=3)
		public void listofUsers(){
			System.out.println("list of users process has started");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.id("admin-menu")).click();
			driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[4]/ul/li[1]/a")).click();
			
			System.out.println("listofUsers method has started");
			
			int i=1;
			String span1= null, login="", email="";
			System.out.println(" This is the list of User and their information");
			try{				 
				do{	
					span1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[4]/span[1]")).getAttribute("aria-hidden");
					login = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[2]")).getText();
					email = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[3]")).getText();
					if (span1.equals("true")){
						System.out.println("User: " + login + " is Acvitated -> email: " + email);
					}else{
						System.out.println("User: "+ login + " is Deactivated");
					}
					i++;
				}while (span1 != "");
			}
			catch (Exception e){
				System.out.println("List of users completed");
			}
					
			System.out.println("listofUsers method has been completed");
			System.out.println("-------------------------------------");
		}
	
	/*	
		@Test (priority=4)
		public void changePwd(){
			System.out.println("changePwd method has started");
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.id("account-menu")).click();
			driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[3]/ul/li[2]/a/span[1]")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("password")).sendKeys(testData.newpwd);
			driver.findElement(By.id("confirmPassword")).sendKeys(testData.newpwd);
			driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/form/button")).click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[1]/strong")).getText();
			System.out.println(actualResult);
			Assert.assertEquals(actualResult, "Password changed!");
			
			System.out.println("changePwd method has been completed");
			System.out.println("-------------------------------------");
		}
	
		*/
		
		@Test (priority=5)
		public void logOut(){
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("logOut process started");
			System.out.println("-------------------------------------");
			driver.findElement(By.id("account-menu")).click();
			driver.findElement(By.id("logout")).click();
			String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]")).getText();
			Assert.assertNotEquals(actualResult, "You are logged in as user \"admin\"");	
		}
		
		/*
		
		@AfterTest
		public void closingTest() throws Exception {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Closing Window with method closingTest");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.quit();
		}*/ 		
	}