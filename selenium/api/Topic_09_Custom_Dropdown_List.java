package api;
// video topic 11
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown_List {
	// toan cuc : global in class	
	private WebDriver driver;
	private WebDriverWait explicitWait;
	//private FluentWait fluentWait;
	private JavascriptExecutor jsExecutor;
	
	
	
	@BeforeClass
	public void beforeClass() {
		// khoi tao trinh duyet firefox
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
		
		
	}
	
	@Test
	public void TC_01_JQuery() {
				
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// selectItemInDropdownList("//span[@id='speed-button']");
		
		// selectItemInDropdownList("//span[@id='number-button']");
		selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']"));
		selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='3']"));
		selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']"));
		selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='16']"));
	}
	

	@Test 
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Elliot Fu");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Elliot Fu']"));
		
		selectItemInDropdownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Christian");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Christian']"));
		
		selectItemInDropdownList("//i[@class='dropdown icon']", "//div[@role='option']/span", "Justen Kitsune");
		Assert.assertTrue(isElementDisplayed("//div[@role='listbox']/div[@class='text' and text()='Justen Kitsune']"));
			
		
	}
	
	@Test
	public void TC_03_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		// 
		selectItemInDropdownList("//*[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Football");
		
		// ko dung cach nay de check display cho angular vi no ko luu trong DOM (nen su dung javascript)
		//Assert.assertTrue(isElementDisplayed("//select[@name='games']/option[text()='Fooball']"));
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Football");
		selectItemInDropdownList("//*[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Hockey");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Hockey");
		selectItemInDropdownList("//*[@id='games']//span[contains(@class, 'e-search-icon')]", "//ul[@id='games_options']/li", "Tennis");
		Assert.assertEquals(getTextByJS("select[name='games'] option"), "Tennis");
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
	public void selectItemInDropdownList(String parentXpath, String childXpath, String expectedItem) {
		// click vao the cha de no xo ra tat ca item con ben trong
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(2);
		
		// Lay het tat ca item gan vao 1 cai list <Webelement> (findElements)
		List <WebElement> allItems = driver.findElements(By.xpath(childXpath));
		System.out.println("Item numder =" + allItems.size());
		
		// wait cho tat ca cac item dc load len thanh cong
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		
		// Dung vong lap de duyet qua cac item 
			/*	for (int i = 0; i < allItems.size(); i++) {
					System.out.println("Duyet lan thu = " + i);
					// get text cua item ra luu vao 1 bieng
					String itemText = allItems.get(i).getText();
					
					// Ktra co bang vs item minh can chon
					if (itemText.equals(expectedItem)) {
						// Neu nhu thoa man thi click vao item do
						allItems.get(i).click();
						// thoat khoi vong lap
						break;
					} 
					
					// kiem tra la getText cua item do roi so sanh voi expected text
					
				}
			*/	
		for (WebElement webElement : allItems) {
			// get text cua item ra
			String itemText = webElement.getText();
			
			//Ktra co bang vs item minh can chon ko
			if(itemText.equals(expectedItem)) {
				// neu nhu thoa man thi click vao item do
				webElement.click();
				sleepInSecond(2);
				break;
			}
			
		}
		
		
		
	}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isElementDisplayed(String xpathLocator) {
		if ( driver.findElement(By.xpath(xpathLocator)).isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	// dung JS de getText cho angualar Testcase
	public String getTextByJS(String cssLocator) {
		// return (String) jsExecutor.executeScript("return $(\"" + cssLocator +"\").textContent");
		String text = (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator +"\").textContent");
		System.out.println("Text = " + text);
		return text;
		
	}
	
}
