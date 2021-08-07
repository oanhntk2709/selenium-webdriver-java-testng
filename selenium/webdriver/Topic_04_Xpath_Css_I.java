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

public class Topic_04_Xpath_Css_I {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field." );
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field." );
	}
	
	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.navigate().refresh();
		
		driver.findElement(By.id("email")).sendKeys("123@123.123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com." );
		
	}
	
	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.navigate().refresh();
		
		driver.findElement(By.id("email")).sendKeys("test@yopmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces." );		
	}
	
	@Test
	public void TC_04_Login_Incorrect_Email() {
		driver.navigate().refresh();
		
		driver.findElement(By.id("email")).sendKeys("test@yopmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password." );		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
