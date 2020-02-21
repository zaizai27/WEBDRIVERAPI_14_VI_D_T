package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Exercise {
		
	WebDriver driver;
	By emailTextboxBy = By.id("mail");
	By educationTextAreaBy = By.id("edu");
	By ageUnder18RadioBy = By.id("under_18");
	By passwordTextboxBy = By.id("password");
	By developmentCheckboxBy =  By.id("development");
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		driver = new FirefoxDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	}
	
	@Test
	public void TC_01_Check_Displayed() {
		
		/* cach 1 chua su dung chung mail - edu -under de xai lai --> chuyen sang cach 2 su dung BY
		// email textbox
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
		}
		//Education 
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("Info Edu Testing");
		}
		// Afe under 18 radio button
		if(driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).click();
		}
		*/
		/*
		// cach 2 su dung BY khai bao va xai chung
		// email textbox
				if(driver.findElement(emailTextboxBy).isDisplayed()) {
					driver.findElement(emailTextboxBy).sendKeys("Automation Testing");
				}
				//Education 
				if(driver.findElement(educationTextAreaBy).isDisplayed()) {
					driver.findElement(educationTextAreaBy).sendKeys("Info Edu Testing");
				}
				// Afe under 18 radio button
				if(driver.findElement(ageUnder18RadioBy).isDisplayed()) {
					driver.findElement(ageUnder18RadioBy).click();
				}
		*/
		//cach 3 
		if(isElementDisplay(emailTextboxBy)) {
			sendkeyToElement (emailTextboxBy, "Automation Testing");
		}
		
		if(isElementDisplay(educationTextAreaBy)) {
			sendkeyToElement (educationTextAreaBy, "Info Edu Testing");
		}
		
		if(isElementDisplay(ageUnder18RadioBy)) {
			clickToElement(ageUnder18RadioBy);
		}
		
		
	}
	
	
	
	

	@Test
	public void TC_02_Check_Enabled() {
		//enabled
		Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));
		
		//disabled
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
	}
	
	@Test
	public void TC_03_Check_Selected() {
		// click to select
		clickToElement(developmentCheckboxBy);
		// development checkbox is selected
		Assert.assertTrue(isElementSelected(developmentCheckboxBy));
		// click to deselect
		clickToElement(developmentCheckboxBy);
		// development checkbox is de-selected
		Assert.assertFalse(isElementSelected(developmentCheckboxBy));
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isElementDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element---- "+ by + "--- is displayed");
			return true;
		} else {
			System.out.println("Element---- "+ by + "--- is undisplayed");
			return false;
			
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element---- "+ by + "--- is Enabled");
			return true;
		} else {
			System.out.println("Element---- "+ by + "--- is unEnabled");
			return false;
			
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element---- "+ by + "--- is Selected");
			return true;
		} else {
			System.out.println("Element---- "+ by + "--- is deSelected");
			return false;
			
		}
	}
	
	public void sendkeyToElement(By by, String value) {
		
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		
		WebElement element = driver.findElement(by);
		element.click();
	}
}
