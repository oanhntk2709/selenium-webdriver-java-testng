package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.webkit.BackForwardList;

public class Topic_03_Selennium_Locator {
	// Khai báo 1 biến đại diên cho Selenium WebDriver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Mở application lên (AUT/SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	// @Test
	// public void TC_01() {
		// Single element: WebElement
		// driver.findElement(By.className("")).click();
		// driver.findElement(By.className("")).getText();
		
		// findElement: tìm element
		// By.xx: với locator nào đó
		// Action gì lên element đó: click/ sendkey/ getText/...
		
		// Multiple element: List<WebElement
		// List<WebElement> buttons = driver.findElements(By.className(""));
		//buttons.get(0).click();
	// }
	
	@Test
	public void TC_02_Locator() {
		// Selenium Locator
		driver.findElement(By.id("send2")).click();
		
		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}
	
	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("123456789");		
	}
	
	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();
		
		driver.findElement(By.name("send")).click();
		
		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());		
	}
	
	@Test
	public void TC_05_Tagname () {
		// Hiển thị hết tất cả các đường link tại màn hình này sau đó getText ra
		List<WebElement> loginPageLinksElements = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinksElements) {
			System.out.println(webElement.getText());
		}
		
	}
	
	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}
	
	@Test
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}
	
	@Test
	public void TC_08_CSS() {
		driver.findElement(By.cssSelector("#email")).sendKeys("test@yopmail.com");
		//driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
		driver.findElement(By.cssSelector("input[ name='login[password]']")).sendKeys("123456789");
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("test_1@yopmail.com");
		driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input")).sendKeys("123456");
	}
	
	
	public void TC_10() {
		
	}
	 
	
	public void TC_11() {
		
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
