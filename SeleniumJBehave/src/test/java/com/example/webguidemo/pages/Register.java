package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daniel on 19.04.2016.
 */
public class Register extends WebDriverPage {

    public Register(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://www.wykop.pl/rejestracja");
        manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void invalidRegister() {
        // Sprawdzam czy jestem zalogowany (jeżeli tak, to się wylogowuję)
        Boolean isLogged = findElements(By.className("logged-user")).size() > 0;
        System.out.println(isLogged);

        if(isLogged) {
            get("http://www.wykop.pl/i/");
            findElement(By.xpath("//img[@src='http://s1.cdn03.imgwykop.pl/static/wykoppl//img/touch/i-ico-logout.png']")).click();
        }

        // Pole nazwy użytkownika wypełniam używanym loginem
        // Po wyjściu z pola tekstowego, inputowi powinna zostać nadana klasa .error
        findElement(By.id("loginField")).sendKeys("Pooffy");
        findElement(By.id("emailField")).sendKeys("superemail@gmail.com");
        findElement(By.id("passwordField")).sendKeys("tajnehaslo");
        findElement(By.id("ok")).click();
        findElement(By.id("company")).click();

        // Po naciśnięciu submita, formularz wyświetla błędy
        findElement(By.xpath("//input[@value='Załóż konto']")).click();
    }

}
