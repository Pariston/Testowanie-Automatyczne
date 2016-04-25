package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}
	public WebElement element;
	private final static String WYKOPALISKO_LINK = "//*[@id=\"nav\"]/div/ul[1]/li[3]/a";
	private final static String SPORT_LINK_TEXT = "SPORT";

	
	public void open() {
		get("http://www.wykop.pl");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void clickWykopaliskoLink() {
		findElement(By.xpath(WYKOPALISKO_LINK)).click();
	}

	public void clickOpenSearchButton() {
		findElement(By.id("openNaturalSearch")).click();
	}

	public void searchSomething() {
		findElement(By.xpath("//input[@name='nsQ']")).sendKeys("Pooffy");
		findElement(By.xpath("//input[@name='nsQ']")).sendKeys(Keys.RETURN);

	}

}
