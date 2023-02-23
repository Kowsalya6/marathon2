package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


import io.github.sukgu.Shadow;

public class ServiceNow {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://dev62925.service-now.com/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("Kowsi");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		Shadow dom = new Shadow(driver);
		dom.setImplicitWait(20);
		dom.findElementByXPath("//div[text()='All']").click();
		dom.setImplicitWait(10);
		dom.findElementByXPath("//span[text()='Service Catalog'] ").click();
		WebElement findElementByXPath = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(findElementByXPath);
		driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
		driver.findElement(By.xpath("(//label[@class='radio-label'])[2]")).click();
		WebElement drop = driver.findElement(By.xpath("//select[@class='form-control cat_item_option '] "));
	    Select select = new Select(drop);
	    select.selectByValue("Unlimited");
	    driver.findElement(By.xpath("(//label[@class='radio-label'])[5]")).click();
	    driver.findElement(By.xpath("(//label[@class='radio-label'])[10]")).click();
	    driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
	    String text = driver.findElement(By.xpath("//span[text()='Thank you, your request has been submitted']")).getText();
	    System.out.println(text);
	    String requestnumber = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
	    System.out.println(requestnumber);
	    File source = driver.getScreenshotAs(OutputType.FILE);
	    File dest = new File("snap/mobileconfirmation");	
	    FileUtils.copyFile(source, dest);
	    driver.close();
	    
	}

}

/*Testcase:3
Url: https://dev62925.service-now.com/
Username: admin
Password: GAhMak34tH-^
Ordering mobile
============
1. Launch ServiceNow application
2. Login with valid credentials
3. Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
4. Click on  mobiles
5. Select Apple iPhone 13
6. Click as No in Is this a replacement for a lost or broken iPhone?
7. Select Unlimited in  Monthly data allowance
8. Choose color field as Blue and storage field as 256 GB
9. Click  Order now option
10. Verify order is placed and get the request number"
11. Take a Snapshot
12. Close the browser*/