package api;

import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_Part_II {
	private WebDriver driver;
	
	//Tai su dung lap lai
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");
	By loginButton = By.id("send2");
	
	
	String email = "auto_test" + randomNumber() + "@hotmail.com";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void runForEachTestMethod() {
		driver.get("http://live.demoguru99.com/");
		//Click on My account link (footer)
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click(); 
	}
	
	
	 @Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.demoguru99.com/");
		
		// Check matching node //div[@class='footer']//a[text()='My Account']
		
		//Click on My account link (footer)
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click(); 
		
		//Check on xpath
				//<input id="email" class="input-text required-entry validate-email" type="email" title="Email Address" 
				//value="" name="login[username]" spellcheck="false" autocorrect="off" autocapitalize="off"/>
		
		// Input vao Email Textbox
		// 1: driver.findElement(By.id("email")).sendKeys("");
		//sudung ham lap lai khoi tao emailTextbox
		driver.findElement(emailTextbox).sendKeys("");
		//Check on xpath
				//<input id="pass" class="input-text required-entry validate-password" type="password" title="Password" name="login[password]"/>
		
		// Input vao Passwrod Textbox
		// 1: driver.findElement(By.id("pass")).sendKeys("");
		//sudung ham lap lai khoi tao passwordTextbox
		
		driver.findElement(passwordTextbox).sendKeys("");
		// xpath //<button id="send2" class="button" name="send" title="Login" type="submit">
		// Click login button
		//1. driver.findElement(By.id("send2")).click();
		//sudung ham lap lai khoi tao loginButton
		driver.findElement(loginButton).click();
		
		//xpath //<div id="advice-required-entry-email" class="validation-advice" style="">This is a required field.</div>
		
		//verify email/password error message displayed
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
		// 1: Assert True (dieu kien)
		//boolean status = driver.findElement(By.id("advice-required-entry-email")).getText().equals("This is a required field.");
		//System.out.println("Status = " + status);
		//Assert.assertTrue(status);	
		//Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).getText().equals("This is a required field."));
		
		// 2: Asser Equal (dk thuc te / mong doi)
		//Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		
		// Input vao Email Textbox
		driver.findElement(emailTextbox).sendKeys("1234234@12312.123123");
						
		// Input vao Password Textbox
		driver.findElement(passwordTextbox).sendKeys("");
				
		// Click login button
		driver.findElement(loginButton).click();
		
		//xpath <div id="advice-validate-email-email" class="validation-advice" 
		//style="">Please enter a valid email address. For example johndoe@domain.com.</div
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		// Input vao Email Textbox
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
								
		// Input vao Password Textbox
		driver.findElement(passwordTextbox).sendKeys("123");
					
		// Click login button
		driver.findElement(loginButton).click();
		
		//<div id="advice-validate-password-pass" class="validation-advice" 
		//style="">Please enter 6 or more characters without leading or trailing spaces.</div>
		// verify password error mess displayed
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		
		// Input vao Email Textbox
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");

		// Input vao Password Textbox
		driver.findElement(passwordTextbox).sendKeys("dawdawdawdawdawd");

		// Click login button
		driver.findElement(loginButton).click();

		//xpath <span>Invalid login or password.</span> nam trong the <li> //li[@class='error-msg']//span
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_LoginWithValidEmailAndPassword() {
		
		// Input vao Email Textbox
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");

		// Input vao Password Textbox
		driver.findElement(passwordTextbox).sendKeys("123123");

		// Click login button
		driver.findElement(loginButton).click();
		
		// verify the infor in Dashboard page
		// //div[@class='page-title']/h1   <h1>My Dashboard</h1>
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
		// //div[@class='welcome-msg']//strong   <strong>Hello, Automation Testing!</strong>
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, Automation Testing!");
		
		//  //h3[text()='Contact Information']/parent::div/following-sibling::div/p
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println("Contact = " + contactInfo);
		Assert.assertTrue(contactInfo.contains("Automation Testing"));
		Assert.assertTrue(contactInfo.contains("automation@gmail.com"));
		
		
	}
	
	@Test
	public void TC_06_CreateNewUser() {
		
		//driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click(); 
		
		// verify Create account page
		
		// //div[@class='page-title']/h1   <h1>My Dashboard</h1>
		// Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText(), "CREATE AN ACCOUNT");

		// driver.findElement(By.id("email")).sendKeys("");
		// xpath <input id="firstname" class="input-text required-entry" 
			//type="text" maxlength="255" title="First Name" value="" name="firstname"/>
		driver.findElement(By.id("firstname")).sendKeys("Dang");
		driver.findElement(By.id("middlename")).sendKeys("Tuan");
		driver.findElement(By.id("lastname")).sendKeys("Vi");
		driver.findElement(By.id("email_address")).sendKeys(email);
		
		driver.findElement(By.id("password")).sendKeys("1234567");
		driver.findElement(By.id("confirmation")).sendKeys("1234567");
		
		//driver.findElement(By.xpath("//div[@class='buttons-set']//button[@class='button']")).click(); 
		//div[@class='buttons-set']//button[@title='Register']
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		//xpath //div[@class='account-cart-wrapper']//span[text()='Account']
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		
		//xpath //a[@title='Log Out']
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		
		//xpath index //div[@class='page-title']//h2[contains(text(),'This is demo site')]
		String indexInfo = driver.findElement(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site')]")).getText();
		Assert.assertTrue(indexInfo.contains("THIS IS DEMO SITE"));
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/");
		
	}
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	//Test
}
