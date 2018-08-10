package com.Facebook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookLoginPage {
	WebDriver driver;
	
	public FacebookLoginPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	private WebElement username;
	
	@FindBy(id="pass")
	private WebElement passwd;
	
	@FindBy(xpath="//input[@value='Log In']")
	private WebElement LoginBtn;
	
	public void userLogin(String userName, String password) {
		username.sendKeys(userName);
		passwd.sendKeys(password);
		LoginBtn.click();
	}
}
