package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, middleName, lastName, fullName, email, password;
//	
//	By emailTextboxBy = By.id("email");
//	By usernameTextboxBy = By.id("new_username");
//	By passwordTextboxBy = By.id("new_password");
//	By signupButtonBy = By.id("create-account");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	
		
		firstName = "Test";
		middleName = "Auto";
		lastName = "A";
		fullName = firstName + " " + middleName + " " + lastName;
		email = "test" + getRandomNumber() + "@yopmail.com";
		password = "123456";
	}
	
	@Test
	public void TC_01_Password_Contain_Number() {
		driver.get("https://login.mailchimp.com/signup/");		
		
		driver.findElement(By.cssSelector("#email")).sendKeys("auto@yopmail.com");
		driver.findElement(By.cssSelector("#new_username")).sendKeys("testregister");
		
		By passwordTextboxBy = By.id("new_password");
		By signupButtonBy = By.id("create-account");
		
		// lowercase
		driver.findElement(By.id("new_password")).sendKeys("test");
		
		// Verify label of lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		
		// Verify button is disable
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		
		// uppercase
		driver.findElement(By.id("new_password")).sendKeys("TEST");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
	
		// number
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys("1234");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButtonBy).isEnabled());
		
		// special chars
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys("@");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButtonBy).isEnabled());
		
		// > 8 chars		
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys("🙂🙂🙂🙂🙂🙂🙂🙂");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButtonBy).isEnabled());
		
		// full valid	
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys("Admin@123");
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertTrue(driver.findElement(signupButtonBy).isEnabled());
		driver.findElement(By.id("marketing_newsletter")).isSelected();
		
	}
	
	@Test
	public void TC_02_LiveGuru_Register() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("middlename")).sendKeys(middleName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		// cách 1
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
		
		//cách 2
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div[@class='box-content']"
				+ "/p[contains(string(), '" + firstName + " " + middleName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div[@class='box-content']"
				+ "/p[contains(string(), '" + email + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@Test
	public void TC_03_LiveGuru_Login() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(email);;	
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()= 'My Dashboard']")).isDisplayed());
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
