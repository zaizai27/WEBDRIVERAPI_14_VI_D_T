 package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_03_Xpath_Css_Path_I_Locator {
	private WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		// 1- mo trinh duyet
		driver = new FirefoxDriver(); 
		// Tra ve 1 cai GUID : xxxx-xxxx-xxxx-xxxx-xxxx
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// 2- Mo app can test
		// 3- toi page can thao tac
		driver.get("https://www.facebook.com/");
		
		// Application (AUT)
		// Pages /Modules
		// 1page/form: element
		// Facebook login : Email textbox/ password textbox/ login button/...
		// HTML Code : <input id="email" class="inputtext login_form_input_box" type="email" data-testid="royal_email" name="email"/> 
		// HTML Format:
		// 1 - tagname
		// 2 - attribute name : id class type ..
		// 3 - attribute value : email / inputtext login_form_input_box/ ...
		
		
		//4 - can thao tac vs element nao
	}
	
	public void TC_01_Id() throws Exception {
		// Tim single element : findElement
		// Thao tac voi element nhieu hon 1 lan
		WebElement emailTextbox = driver.findElement(By.id("email"));
		// truoc khi send key clear text box
		emailTextbox.clear();
		// input du lieu vao email
		emailTextbox.sendKeys("automationfc.vn@gmail.com");
		emailTextbox.isDisplayed();
		Thread.sleep(3000);
		
		// cach dung 2 ko khai bao bien , chi thao tac 1 lan dung truc tiep
		// driver.findElement(By.id("email")).clear();
		// driver.findElement(By.id("email")).sendKeys("automationfc.vn@gmail.com");
		// driver.findElement(By.id("email")).isDisplayed();
		
		
		// Tim nhieu hon 1 element : findElements
		List<WebElement> links = driver.findElements(By.tagName("a"));
	}
	
	public void TC_02_Class() throws Exception {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		//<input id="pass" class="input-text required-entry validate-password" type="password" title="Password" name="login[password]"/>
		// className truyen 1 trong 3 trong class input-text / required-entry / validate-password
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		Thread.sleep(3000);
	}
	
	public void TC_03_Name() throws Exception {
		
		//<input id="email" class="input-text required-entry validate-email" 
		//type="email" title="Email Address" value="" name="login[username]" 
		//spellcheck="false" autocorrect="off" autocapitalize="off"/>
		driver.findElement(By.name("login[username]")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
	}
	public void TC_04_Tagname() {
		List <WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Link number = " + links.size());
		
	}
	
	public void TC_05_LinkText() {
		
		//<a title="My Account" href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// doi voi LINK TEXT string theo display tren web viet hoa MY ACCOUNT
		// Check tuyet doi khac voi partial tuong doi
		driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed();
		
	}
	
	public void TC_06_PartialLinkText() {
		// <a title="My Account" href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
		// check tuong doi co the tim duoc su dung My or Account  content chua text can tim
		driver.findElement(By.partialLinkText("ACCOUNT")).isDisplayed();
		driver.findElement(By.partialLinkText("MY")).isDisplayed();
		driver.findElement(By.partialLinkText("Y ACCOUNT")).isDisplayed();

	}
	
	public void TC_07_Css() {
		//<button id="send2" class="button" name="send" title="Login" type="submit">
		driver.findElement(By.cssSelector("#send2")).isDisplayed();
		driver.findElement(By.cssSelector("button[name='send']")).isDisplayed();
		driver.findElement(By.cssSelector("button[title='Login']")).isDisplayed();
		
	}
	public void TC_08_Xpath() {
		//<button id="send2" class="button" name="send" title="Login" type="submit">
		driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed();
		driver.findElement(By.xpath("//button[@name='send']")).isDisplayed();
		driver.findElement(By.xpath("//button[@title='Login']")).isDisplayed();

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
