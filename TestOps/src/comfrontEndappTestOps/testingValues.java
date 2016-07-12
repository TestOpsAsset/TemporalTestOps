package comfrontEndappTestOps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class testingValues {

	public static void main(String[] args) {
		
			
		// -------------- SETTING THE BROWSER  ------------------
			System.setProperty("webdriver.chrome.driver", "C://Users//l.enriquez.rodriguez//Documents//Eclipse Projects//chromedriver_win32//chromedriver.exe");
			ChromeDriver skydriver, driver;
			
			/*driver.get(testData.appURL);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();*/
		//------------------  ---------------------------------
			
			skydriver = new ChromeDriver();
			skydriver.get(testData.skytap);
			skydriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String vmStatus = skydriver.findElement(By.className("vm-status")).getText();
			System.out.println(vmStatus);
			
			if (vmStatus.equals("Running")){
					skydriver.close();
					driver = new ChromeDriver();
					driver.get(testData.appURL);	
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().window().maximize();
			}
			else{
					System.out.println("Please verify VM status, host is not reachable, Script will be closed.");
					skydriver.quit();
				}
			
			
			
			driver = new ChromeDriver();
			
			driver.findElement(By.xpath("//*[@id='account-menu']")).click();
			driver.findElement(By.xpath("//*[@id='login']")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys(testData.User);
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys(testData.pwd);
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[3]/form/button")).click();
			//String actualResult = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[2]/div/div")).getText();
			//String expectedResult ="You are logged in as user \"admin\".";
			//Assert.assertEquals(actualResult, expectedResult);
			driver.findElement(By.id("admin-menu")).click();
			driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[4]/ul/li[1]/a")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			//System.out.println("Value: ->" + driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody")).getTagName());
			
			
			/*List<WebElement> rows = driver.findElements(By.cssSelector("table#dummyTable>tbody>tr"));
			System.out.println("Total number of rows :"+ rows.size());
			*/
			
			// RECORRER LA LISTA 
			
			int i=1;
			String span1= null, login="", email="";
			try{				
				do{	
					span1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[4]/span[1]")).getAttribute("aria-hidden");
					login = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[2]")).getText();
					email = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/table/tbody/tr["+ i +"]/td[3]")).getText();
					if (span1.equals("true")){
						System.out.println("User " + login + " is Acvitated" + ". email = "+ email);
					}else{
						System.out.println("User "+ login + " is Deactivated");
					}
					System.out.println("this is Do loop");
					i++;
				}while (span1 != "");
			}
			catch (Exception e){
				System.out.println("Try & Catch process completed");
			}						
		}
}
