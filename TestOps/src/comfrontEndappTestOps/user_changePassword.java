package comfrontEndappTestOps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class user_changePassword {

	public static void main(String[] args) {
		
		// Setting WebDriver values for Chrome
		System.setProperty("webdriver.chrome.driver", "C://Users//l.enriquez.rodriguez//Documents//Eclipse Projects//chromedriver_win32//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		//data is being pulled from testData class in the same package 
		driver.get(testData.appURL);
		driver.manage().window().maximize();
		System.out.println("window has been maximized");
		driver.findElement(By.xpath("//*[@id='account-menu']")).click();
		driver.findElement(By.xpath("//*[@id='login']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(testData.User);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testData.pwd);
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[3]/form/button")).click();		
		
	}

}
