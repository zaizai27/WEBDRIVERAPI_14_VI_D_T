package api;

import java.util.Random;
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

public class Topic_08_Textbox_TextArea {
	// toan cuc : global in class	
	private WebDriver driver;
	private String email, userID, password, loginPageUrl;
	private String name, dob, address, city, state, pin, phone, gender, customerID;
	//tao bien moi cho edit
	private String editAddress, editCity, editPin, editPhone, editEmail, editState;
	private By nameTextboxBy = By.name("name");
	//private String lastname = "Automation FC"
	private By dobTextboxBy = By.name("dob");
	private By addressTextAreBy = By.name("addr");
	private By cityTextboxBy = By.name("city");
	private By stateTextboxBy = By.name("state");
	private By pinTextboxBy = By.name("pinno");
	private By phoneTextboxBy = By.name("telephoneno");
	private By emailTextboxBy = By.name("emailid");
	private By passwordTextboxBy = By.name("password");
	private By submitButtonBy = By.name("sub");
	private By genderTextboxBy = By.name("gender");
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		email= "tom_jerry" + randomNumber() + "@hotmail.com";
		name ="TOM JERRII";
		dob ="2020-01-05";
		address="56 Le Lai";
		city="HA NOI";
		state="Hoan Kiem";
		pin="650000";
		phone="0907010210";
		gender="male";
		
		//new edit data
		editAddress="45 le loi";
		editCity="HCM";
		editPin="460000";
		editPhone="0971625161";
		editEmail="vivivi"+ randomNumber() + "@gmail.com";
		editState="Thu Duc";
		
	}
	
	@Test
	public void TC_01_Register_To_System() {
		loginPageUrl = driver.getCurrentUrl();
		// click on HERE link
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//*/input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println("User ID at Register page = " + userID);
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("PassWord at Register page = " + password);
	}
	

	@Test //(dependsOnMethods = "TC_01_Register_To_System")
	public void TC_02_LoginToSystem() {
		driver.get(loginPageUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//xu ly chuoi co USERID
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+userID+"']")).isDisplayed());
		// ko nen dua welcomeMessage khai bao o tren cung --> bien cuc bo
		String welcomeMessage = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(welcomeMessage, "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@Test
	public void TC_03_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextAreBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(submitButtonBy).click();
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()= 'Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID= driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}
	
	@Test
	public void TC_04_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		
		//clear data
		driver.findElement(addressTextAreBy).clear();
		driver.findElement(addressTextAreBy).sendKeys(editAddress);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		
		driver.findElement(submitButtonBy).click();
		
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()= 'Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
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
