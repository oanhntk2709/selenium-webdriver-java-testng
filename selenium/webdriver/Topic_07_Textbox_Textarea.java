package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl;
	String userID, passsword;	
	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='m']");
	By dateOfBirthTextboxBy = By.name("dob");
	By addressTextboxBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("http://demo.guru99.com/v4/");
	}
	
	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.xpath("//td/input[@name='emailid']")).sendKeys("test@yopmail.com");
		driver.findElement(By.xpath("//td/input[@name='btnLogin']")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passsword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();				
	}
	
	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passsword);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	}
	
	@Test
	public void TC_03_Edit() {
		driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
