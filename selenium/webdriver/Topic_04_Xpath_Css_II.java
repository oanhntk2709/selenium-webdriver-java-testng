package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/");
	}
	
	@Test
	public void TC_01_Verify_Text() {
		// 1 - Get text của element đó ra -> Lưu vào 1 biến
		// Biến này để kiểm tra chứa text mong muốn hay không -> Java String (contains)
		String populationValue = driver.findElement(By.xpath("//div[@id='population']")).getText();
		System.out.println(populationValue);
		Assert.assertTrue(populationValue.contains("Mongolia: 500-1,000"));
		
		// 2- Xpath check contains text có nằm trong element đó không
		// Check displayed cái element có xpath đó (isDisplayed)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='population' and contains(., \"Mongolia: 500-1,000\")]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='population' and contains(string(), \"Mongolia: 500-1,000\")]")).isDisplayed());
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
