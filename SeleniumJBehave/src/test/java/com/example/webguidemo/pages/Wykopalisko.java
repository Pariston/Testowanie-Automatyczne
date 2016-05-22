package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import java.util.concurrent.TimeUnit;

public class Wykopalisko extends WebDriverPage{
	
	public Wykopalisko(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.wykop.pl/wykopalisko");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
