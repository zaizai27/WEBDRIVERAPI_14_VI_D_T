package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_API {
	
	//user Thao tac
	// 1- Mo browser len
	// 2- Nhap vao url : tiki.vn
	// 3- Se tai lai trang
	// 4- Click 1 link (WEB ELEMENT)
	// 5- Back lai trang truoc
	// 6- Forward toi trang truoc do
	// 7- Get title cua trang
	// 8- Get ra URL cua trang
	// 9- Get source code cua trang
	// 10- Dong appli/browser
	
	// Khai bao bien (declare)
	// instance dai dien cho 1 thu vien / class/ interface nao do
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		// 1
		driver = new FirefoxDriver();
		// 2
		driver.get("https://tiki.vn/");
		// 7
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		//8
		//driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
		
		//9
		//driver.getPageSource();
		//tuong doi
		Assert.assertTrue(driver.getPageSource().contains("phù hợp với nhu cầu tìm kiếm sản phẩm của bạn"));
		
		// get ra ID cua tab/ window hien tai (*)
		driver.getWindowHandle();
		
		// Dung de cho cho element duoc xuat hien (thao tac duoc) - WebDriverWait (3 buoi)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// cho cho page duoc load xong de thao tac
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// cho cho script dc execute xong  (Java script executor)
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		//
		driver.manage().window().fullscreen();
		// phong to trinh duyet
		driver.manage().window().maximize();
		// test GUI (font/size/color)
		driver.manage().window().getSize();
		//5
		driver.navigate().back();
		//3
		driver.navigate().refresh();
		//6
		driver.navigate().forward();
		
		// Alert/Window (tab)/ Iframe (frame)
		driver.switchTo().alert();
		driver.switchTo().window("");
		driver.switchTo().frame("");
		
		// dong trinh duyet trong truong hop co 1 tab
		// dong trinh duuyet tab dang dung neu co nhieu tab
		driver.close();
		// dong trinh duyet ko quan tam bao nhieu tab
		driver.quit();
	}
	
	@Test
	public void TC_01_() {
		driver.get("");
	}
	
	@Test
	public void TC_02_() {
		
	}
	
	@Test
	public void TC_03_() {
		
	}
	@Test
	public void TC_04_() {
		
	}
	
	@Test
	public void TC_05_() {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
