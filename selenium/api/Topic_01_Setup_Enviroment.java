package api;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Setup_Enviroment {
	private WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Chay Dau Tien");
  }
  @Test
  public void TC_01_Check_Google_Title() {
  }
  public void TC_02_Check_Google_Url() {
  }
  public void TC_03_Check_Google_Logo() {
  }
  @AfterClass
  public void afterClass() {
  }

}
