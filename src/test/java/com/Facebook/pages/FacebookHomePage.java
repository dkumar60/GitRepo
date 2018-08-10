package com.Facebook.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookHomePage {
WebDriver driver;
	
	public FacebookHomePage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='fb_stories_card_root\']")
	private List<WebElement> storiesSection;
	
	@FindBy(xpath="//div[@id='fb_stories_card_root']/div/div/div[2]/a")
	private List<WebElement> seeMoreStorySec;
	
	@FindBy(xpath="//div[@id='fb_stories_card_root']/div/div/div[2]/div/div")
	private List<WebElement> latestStory;
	
	@FindBy(xpath="//span[@class='fbRemindersTitle']")
	private List<WebElement> bdaySection;
	

	public String validateStoriesSection() {
		int [] StoryTimeLine;
		if(seeMoreStorySec.size()>0) {
			Actions action = new Actions(driver);
			action.moveToElement(seeMoreStorySec.get(0)).click().build().perform();
		}
		if(latestStory.size()>1) {
			StoryTimeLine = new int[latestStory.size()-1];
			for(int i=1; i<latestStory.size(); i++) {
				String timeString =driver.findElement(By.xpath("//*[@id='fb_stories_card_root']/div/div/div[2]/div/div["+(i+1)+"]/div[2]/div[2]/abbr/span")).getText();
				String []timeline = timeString.split(" ");
				String actualtime=timeline[0];
				if(actualtime.equalsIgnoreCase("about")) {
					return "Story Section exist about an hour ago";
				}
				else {
				StoryTimeLine[i-1]= Integer.parseInt(actualtime);
				}
			}
			int temp;
			for(int i=0; i<StoryTimeLine.length; i++) {
				for(int j=i+1; j<StoryTimeLine.length; j++ ) {
					if(StoryTimeLine[i]>StoryTimeLine[j]) {
						temp=StoryTimeLine[i];
						StoryTimeLine[i] = StoryTimeLine[j];
						StoryTimeLine[j] = temp;	
					}
				}
			}
			return "Story Section exist "+ StoryTimeLine[0] + " hours ago";
		}
		else
			return "No Latest Stories available on this page";
	}
	
	public String validateBdaySection()
	{
		if(bdaySection.size() >0) {
			
			return "Birthdays section exist, "+ String.valueOf(bdaySection.size()) ;
			}
			return "Birtday section dosen't exist";		
	}
	

}
