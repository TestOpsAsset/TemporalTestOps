package comfrontEndappTestOps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class login {
	
	public static void main(String[] args) {

	// -------------- SETTING THE BROWSER  ------------------
				System.setProperty(testData.Chrome1, testData.Chrome2);
				ChromeDriver driver = new ChromeDriver();	
				driver.get(testData.appURL);	
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			//------------------  ---------------------------------
	
		
		System.out.println("login process started");
		driver.findElement(By.xpath("//*[@id='account-menu']")).click();
		driver.findElement(By.xpath("//*[@id='login']")).click();	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(testData.User);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testData.pwd);
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[3]/form/button")).click();
		String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).getText();
		String expectedResult ="You are logged in as user \"admin\".";
		
		System.out.println("logIn method has been completed.");
		System.out.println("-------------------------------------");
		
		//Assert.assertEquals(actualResult, expectedResult);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("logOut process started");
		
		driver.findElement(By.id("account-menu")).click();
		driver.findElement(By.id("logout")).click();
		String actualResult2 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div[2]")).getText();
		Assert.assertNotEquals(actualResult2, "You are logged in as user \"admin\"");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		System.out.println("logOut method has been completed.");
		System.out.println("-------------------------------------");
		
		
		System.out.println("login process completed");
		
	
	}	
}
