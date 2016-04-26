package com.example.webguidemo;

import com.example.webguidemo.pages.*;
import org.apache.commons.io.FileUtils;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class Pages {

	private WebDriverProvider driverProvider;

	//Pages
	private Home home;
	private Wykopalisko wykopalisko;
	private Search search;
	private Login login;
	private Register register;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Wykopalisko wykopalisko(){
		if (wykopalisko == null) {
			wykopalisko = new Wykopalisko(driverProvider);
		}
		return wykopalisko;
	}
	
	public Search search() {
		if (search == null) {
			search = new Search(driverProvider);
		}
		return search;		
	}

	public Login login() {
		if (login == null) {
			login = new Login(driverProvider);
		}
		return login;
	}

	public Register register() {
		if (register == null) {
			register = new Register(driverProvider);
		}
		return register;
	}

	public void screenshot(String title) {
		File screenshot = ((TakesScreenshot) driverProvider.get()).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("./screenshots/" + title + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
