package com.Facebook.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Facebook.pages.FacebookHomePage;
import com.Facebook.pages.FacebookLoginPage;

public class Facebook {
	static WebDriver driver;
	FacebookLoginPage fbloginpage;
	FacebookHomePage fbHomePage; 
	
	@BeforeTest(enabled=true)
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C:\\Deepak\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		fbloginpage= new FacebookLoginPage(driver);
		fbHomePage= new FacebookHomePage(driver);
	}
	
	@Test(enabled=true, dataProvider="credential")
	public void FBLogin(String uName, String pwd) {
		driver.get("https://www.facebook.com/");
		fbloginpage.userLogin(uName,pwd);
	}
	
	@Test(dependsOnMethods="FBLogin", enabled=true)
	public void ValidateStoriesSection() {
		System.out.println(fbHomePage.validateStoriesSection());
	}
	
	@Test(dependsOnMethods="FBLogin", enabled=true)
	public void ValidateBdaySection() {
		System.out.println(fbHomePage.validateBdaySection());				
	}
	
	@Test(dependsOnMethods="FBLogin", enabled=true)
	public void PrintName() {
		System.out.println("My Name is Lakhan");				
	}
	
	@DataProvider
	public Object[][] credential(){
		Object [][] data = new Object[1][2];
		data[0][0] = "username1";
		data[0][1] = "password1";
	
		return data;
	}
	
	@AfterTest
	public void closeConnection() {
		driver.quit();
	}
}
