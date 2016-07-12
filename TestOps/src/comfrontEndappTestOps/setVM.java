package comfrontEndappTestOps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class setVM {

	public static void main(String[] args) throws Exception{
		
	
			System.setProperty(testData.Chrome1, testData.Chrome2);
			WebDriver driver = new ChromeDriver();
			System.out.println("Checking for VM status");
			System.out.println("--------------------------------");
			driver.get(testData.skytap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);	
			//getting the status of the VM -----------------------
			
			String cStatus = driver.findElement(By.className("vm-status")).getText();
			System.out.println("Getting status of the VM 1: " +  cStatus);
			System.out.println("--------------------------------");
			
			if (cStatus.equals("Running")){ // CHECK IF VM IS RUNNING
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				System.out.println("Redirecting to FrontEndd App");
				System.out.println("--------------------------------");
				driver.get(testData.appURL);	
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}
			else if (cStatus.equals("Suspended")){ //CHECK IF VM IS IN SUSPENDED STATE
				System.out.println(cStatus);
				System.out.println("--------------------------------");
				//driver.manage().timeouts().implicitlyWait(1,  TimeUnit.MINUTES);
				driver.findElement(By.xpath("//*[@id=\"vm5362718\"]/div[1]/div[2]/button[1]")).click();
				do {
					cStatus = driver.findElement(By.className("vm-status")).getText();
					System.out.println("-- " + cStatus);
				}while (cStatus.equals("Running"));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(testData.appURL);	
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				
			}else{
				System.out.println("> " + cStatus);
				do {
					cStatus = driver.findElement(By.className("vm-status")).getText();
				} while (cStatus.equals("Busy")!= false);
				driver.manage().timeouts().implicitlyWait(1,  TimeUnit.MINUTES);
				cStatus = driver.findElement(By.className("vm-status")).getText();
				System.out.println("Status has changed to: " + cStatus);
				if (cStatus.equals("Suspended")){
					System.out.println("Confirmed status: " + cStatus);
					System.out.println("----------------------------------------");
					driver.manage().timeouts().implicitlyWait(1,  TimeUnit.MINUTES);
					driver.findElement(By.xpath("//*[@id=\"vm5362718\"]/div[1]/div[2]/button[1]")).click();
					System.out.println("Status has changed to :" + (cStatus = driver.findElement(By.className("vm-status")).getText()));
					do {
						cStatus = driver.findElement(By.className("vm-status")).getText();
											
					}while (cStatus.equals("Running"));
					System.out.println(driver.findElement(By.className("vm-status")).getText());
					System.out.println("Redirecting to FrontEndd App");
					System.out.println("--------------------------------");
					driver.manage().timeouts().implicitlyWait(1,  TimeUnit.MINUTES);
					driver.get(testData.appURL);	
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					driver.manage().window().maximize();			
				}
				}
				
			}
	}
