package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}
	
	@Test
	public void TC_01() {
		driver.findElement(By.name("login")).click();
		
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("auto@yopmail.com");
		emailTextbox.isDisplayed();
		
		WebElement element = driver.findElement(By.id(""));
		
		// Xóa dữ liệu trong editable field (textbox/ textarea/ dropdown)
		element.clear();
		
		// Nhập dữ liệu trong editable field (textbox/ textarea/ dropdown)
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		//Click vào button/ link/ radio/ checkbox/ image/ ...
		element.click();
		
		// Trả về dữ liệu nằm trong attribute của element: email/ SĐT
		element.getAttribute("placholder");
		element.getAttribute("value");
		
		//Lấy thuộc tính của element: font size/ color/ style/ ...: #4ab2f1
		element.getCssValue("background-color");
		
		// GUI
		element.getLocation();		
		element.getRect();
		element.getSize();
		
		// take screenshot -> attach to HTML report
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		// tên thẻ HTML: dùng By.id/ class/ css/ name hoặc Đầu ra của step này là đầu vào của step kia
		element = driver.findElement(By.cssSelector("#save-info-button"));
		String saveButtonTagname = element.getTagName();
		driver.findElement(By.xpath("//" + saveButtonTagname + "[@name='email]"));
		
		// Nối string
		String addressNameString = "123 Ly Thuong Kiet";
		String cityNameString = "Ho Chi Minh City";
		
		System.out.println(addressNameString + cityNameString);
		System.out.println(addressNameString.concat(cityNameString));
		System.out.println(addressNameString + " - " + cityNameString);
		
		// Lấy text của header/ link/ label/ error/ success mesagge
		element.getTagName();
		
		// Kiểm tra 1 element có hiển thị hay ko (hiển thị: người dùng nhìn thấy và thao tác được)
		element.isDisplayed();
		Assert.assertTrue(element.isDisplayed());
		
		// Kiểm tra 1 element có thể thao tác được hay ko (ko bị disable/ ko phải là readonly field)
		element.isEnabled();
		
		// Kiểm tra 1 element đã được chọn hay chưa (radio/ checkbox/ dropdown)
		element.isSelected();
		
		// submit vào 1 form
		element.submit();
	}
	
	@Test
	public void TC_02() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
