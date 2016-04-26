package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daniel on 19.04.2016.
 */
public class Login extends WebDriverPage {

    public Login(WebDriverProvider driverProvider) {
        super(driverProvider);
    }
    WebElement element;

    public boolean isLogged() {
        // Sprawdzam czy jestem zalogowany (jeżeli tak, to się wylogowuję)
        Boolean isLogged = findElements(By.className("logged-user")).size() > 0;
        System.out.println(isLogged);

        if(isLogged) {
            get("http://www.wykop.pl/i/");
            findElement(By.xpath("//img[@src='http://s1.cdn03.imgwykop.pl/static/wykoppl//img/touch/i-ico-logout.png']")).click();
            return true;
        }
        return false;
    }

    public void open() {
        get("http://www.wykop.pl/zaloguj");
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void loginInvalid(String login, String haslo) {
        findElement(By.id("text1")).sendKeys(login);
        findElement(By.id("text2")).sendKeys(haslo);

        // Po naciśnięciu submita, formularz wyświetla błędy
        findElement(By.className("submit")).click();
    }

    public void loginValid(String login, String password) {
        isLogged();

        findElement(By.id("text1")).sendKeys(login);
        findElement(By.id("text2")).sendKeys(password);

        // Po naciśnięciu submita, przechodzę zalogowany na stronę główną
        findElement(By.className("submit")).click();
    }

    public void userGoToSettingsPage(String login, String password) {
        if(!isLogged()) loginValid(login, password);
        get("http://www.wykop.pl/ustawienia/");
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void userChangeColorVersion() {
        findElement(By.xpath("//select[@name='user[night_mode]']")).click();

        if(findElement(By.xpath("//option[@value='0']")).isSelected()) {
            findElement(By.xpath("//option[@value='1']")).click();
        } else {
            findElement(By.xpath("//option[@value='0']")).click();
        }

        findElement(By.className("submit")).click();
        open();
    }
}
