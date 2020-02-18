package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	
	
	WebDriver driver;
	// bien toan cuc
	WebElement element;
	
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		// 1
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void TC_01_() {
		
		//bien cuc bo
		//WebElement element;
		// Neu dung 1 lan thi tuong tac truc tiep
		driver.findElement(By.xpath("")).click();
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		
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
