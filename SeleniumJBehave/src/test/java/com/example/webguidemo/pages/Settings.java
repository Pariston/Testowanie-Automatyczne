package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

import java.util.concurrent.TimeUnit;

public class Settings extends WebDriverPage {

    public Settings(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://www.wykop.pl/rejestracja");
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void wykopChangeSkin() {

    }
}
