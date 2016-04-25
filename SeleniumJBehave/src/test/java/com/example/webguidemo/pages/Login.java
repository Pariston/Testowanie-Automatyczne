package com.example.webguidemo.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * Created by Daniel on 19.04.2016.
 */
public class Login extends WebDriverPage {

    public Login(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

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

    public void loginInvalid() {
        findElement(By.id("text1")).sendKeys("Pooffy");
        findElement(By.id("text2")).sendKeys("tajnehaslo");

        // Po naciśnięciu submita, formularz wyświetla błędy
        findElement(By.className("submit")).click();
    }

    public void loginValid() {
        isLogged();

        findElement(By.id("text1")).sendKeys("Pooffy");
        findElement(By.id("text2")).sendKeys("dlatestu");

        // Po naciśnięciu submita, przechodzę zalogowany na stronę główną
        findElement(By.className("submit")).click();
    }
}
