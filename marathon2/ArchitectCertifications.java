package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Leaf@123");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lst.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		Shadow dom = new Shadow(driver);
		dom.findElementByXPath("//span[text()='Learning']").click();
		Actions builder = new Actions(driver);
		WebElement Trailhead = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		builder.moveToElement(Trailhead).perform();
		WebElement certificate = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		driver.executeScript("arguments[0].click();", certificate);
		driver.findElement(By.xpath("(//div[@class='roleMenu-item_text'])[2]")).click();
		String text = driver.findElement(By.xpath("//div[contains(@class,'cert-site_text slds-text-align--center')]")).getText();
		System.out.println(text);
		List<WebElement> Certifications = driver.findElements(By.xpath("//div[@class='credentials-card_title']")); 
		for (int i = 0; i < Certifications.size(); i++) {
			System.out.println(Certifications.get(i).getText());
		}
		driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
		List<WebElement> Certifications2 = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		for (int i = 0; i <Certifications2.size(); i++) {
			System.out.println(Certifications2.get(i).getText());
			}
		WebElement findElement = driver.findElement(By.xpath("//a[text()='Platform App Builder']"));
		builder.scrollToElement(findElement);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap/Certifications2.png");
		FileUtils.copyFile(source, dest);
	   driver.close();
	}

}

