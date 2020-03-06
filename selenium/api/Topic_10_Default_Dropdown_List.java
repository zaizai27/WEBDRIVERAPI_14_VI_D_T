package api;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Dropdown_List {
	// toan cuc : global in class	
	private WebDriver driver;
	private Select select;
	private WebDriverWait wait;
	private JavascriptExecutor javascriptEx;
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		driver = new FirefoxDriver();
		
		wait = new WebDriverWait(driver,15);
		javascriptEx = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
		
		
	}
	
	@Test
	public void TC_01_Default_Dropdown() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//thao tac voi dropdown (job01)
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		//ktra ko multiple select
		boolean status = select.isMultiple();
		System.out.println("Dropdown =" + status);
		
		//expected dropdown ko phai multiple // neu multiple thi asserttrue
		Assert.assertFalse(status);
		
		/*
		 <select id="job1" name="user_job1">
		0	<option value="automation">Automation Testing</option>
		1	<option value="manual">Manual Testing</option>
		2	<option value="website">Adhoc Testing</option>
		3	<option value="mobile">Mobile Testing</option>
		4	<option value="desktop">Desktop Testing</option>
		5	<option value="security">Security Testing</option>
		6	<option value="perfomance">Perfomance Testing</option>
		7	<option value="intergration">Intergration Testing</option>
		8	<option value="unit">Unit Testing</option>
		9	<option value="function">Functional UI Testing</option>
			</select>
		*/
		
		//3 cach lay -- nen dung visibletext
		select.selectByIndex(3);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		
		select.selectByVisibleText("Functional UI Testing");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		// ktra total gia tri trong dropdown
		int jobDropdownItem = select.getOptions().size();
		Assert.assertEquals(jobDropdownItem, 10);
		
		// thao tac voi dropdown job 02
		
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
				
				
		//expected dropdown ko phai multiple // neu multiple thi asserttrue
		Assert.assertTrue(select.isMultiple());
		
		select.selectByVisibleText("Automation");
		Thread.sleep(1000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(1000);
		select.selectByVisibleText("Desktop");
		Thread.sleep(1000);
		
		List<WebElement> jobRole02Selected = select.getAllSelectedOptions();
		for (WebElement select : jobRole02Selected) {
			System.out.println(select.getText());
		}
		Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
		
		
	}
	

	@Test 
	public void TC_02_DefaultDropDown() {
		
		driver.get("https://demo.nopcommerce.com");
		
		driver.findElement(By.cssSelector(".ico-register")).click();
		driver.findElement(By.cssSelector("#gender-male")).click();
		
		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Cristina");
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Ronal");
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("8");
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("August");
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1988");
		
		driver.findElement(By.cssSelector("#Email")).sendKeys("cristi_ronaldo_" + randomNumber() + "@hotmail.com");
		driver.findElement(By.cssSelector("#Company")).sendKeys("CR7");
		driver.findElement(By.cssSelector("#Password")).sendKeys("123123");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123123");
		
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".result")).getText().equals("Your registration completed"));
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".ico-account")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".ico-logout")).isDisplayed());
		
	}
	
	@Test
	public void TC_03_NewCustomer() {
		
	}
	
	@Test
	public void TC_04_EditCustomer() {
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int randomNumber () {
		Random rand = new Random();
		return rand.nextInt(999999);
		
	}
	
}
