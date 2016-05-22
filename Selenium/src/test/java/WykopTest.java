import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Daniel on 12.04.2016.
 */
public class WykopTest {
    private static WebDriver driver;
    WebElement element;
    WebElement element2;

    @BeforeClass
    public static void driverSetup() {
        // ChromeDrirver, FireforxDriver, ...
        System.setProperty("webdriver.chrome.driver", "C:/Users/Daniel/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void wykopChangeSkin() {
        // Zalogowanie się z niepoprawnym hasłem
        driver.get("http://wykop.pl/zaloguj");

        element = driver.findElement(By.id("text1"));
        element.sendKeys("Pooffy");
        assertNotNull(element);

        element = driver.findElement(By.id("text2"));
        element.sendKeys("tajnehaslo"); // niepoprawne hasło
        assertNotNull(element);

        // Po naciśnięciu submita, formularz wyświetla błędy
        driver.findElement(By.className("submit")).click();

        assertEquals("Niepoprawny login lub hasło", driver.findElement(By.className("wblock")).getText());

        // Wykonanie zdjęcia obrazującego treść błędu po zalogowaniu się przy użyciu niepoprawnych danych
        screenshot("loginError");

        // Zalogowanie się z niepoprawnym hasłem
        element = driver.findElement(By.id("text1"));
        element.sendKeys("Pooffy");

        element = driver.findElement(By.id("text2"));
        element.sendKeys("dlatestu"); // niepoprawne hasło

        driver.findElement(By.className("submit")).click();

        // Wykonanie zdjęcia obrazującego stronę po zalogowaniu się przy użyciu poprawnych danych
        screenshot("loginSuccess");

        /*
         Zmiana wersji kolorystycznej strony
        */

        // Wykonanie zdjęcia obrazującego wygląd strony przed zmianie wersji kolorystycznej
        screenshot("colorChange1");

        driver.get("http://www.wykop.pl/ustawienia/");

        element = driver.findElement(By.xpath("//select[@name='user[night_mode]']"));
        assertNotNull(element);
        element.click();

        element = driver.findElement(By.xpath("//option[@value='0']"));
        assertNotNull(element);

        if(element.isSelected()) {
            driver.findElement(By.xpath("//option[@value='1']")).click();
        } else {
            element.click();
        }

        element = driver.findElement(By.className("submit"));
        assertNotNull(element);
        element.click();

        driver.get("http://wykop.pl");

        // Wykonanie zdjęcia obrazującego wygląd strony po zmianie wersji kolorystycznej
        screenshot("colorChange2");
    }

    @Test
    public void wykopSearch() {
        driver.get("http://wykop.pl");
        // przed naciśnięciem przycisku otwierającego szukajkę, input szukajki jest ukryty
        // szukajka nie ma na początku ustalonego stylu, ale kiedy naciśniemy przycisk pokazujący dwa razy
        // to jest - pokażemy i ukryjemy input, wtedy szukajce nadawany jest styl display: none;
        element = driver.findElement(By.id("naturalSearch"));
        assertThat(element.getAttribute("style"), either(equalTo("")).or(equalTo("display: none;")));

        // sprawdzam czy przycisk pokazujący input do szukajki działa
        element = driver.findElement(By.id("openNaturalSearch"));
        element.click();
        assertNotNull(element);

        // Wykonanie zdjęcia obrazującego input wyszukiwarki
        screenshot("search1");

        // jeżeli przycisk pokazujący input został naciśnięty, to input jest widoczny
        element = driver.findElement(By.id("naturalSearch"));
        assertEquals("display: block;", element.getAttribute("style"));

        // po pokazaniu pola szukajki, wyszukuję istniejącego użytkownika "Pooffy" i sprwadzam czy został wyświetlony
        element = driver.findElement(By.xpath("//input[@name='nsQ']"));
        element.click();
        element.sendKeys("Pooffy");

        // Wykonanie zdjęcia obrazującego wypełnianie inputa wyszukiwarki
        screenshot("search2");

        element.sendKeys(Keys.RETURN);
        // istnienie bloku o klasie "usercard" jest dowodem na otrzymanie przynajmniej jednego wyniku wyszukiwarki
        assertNotNull(driver.findElement(By.className("usercard")));

        // Wykonanie zdjęcia obrazującego przedstawienie wyników wyszukiwarki
        screenshot("search3");
    }

    @Test
    public void wykopCookies() {
        driver.get("http://wykop.pl");
        // element = driver.findElement(By.className("cookie"));
        element = driver.findElement(By.cssSelector(".annotation, .type-alert, .type-permanent, .lspace, .m-reset-position, .closableContainer, .cookie"));
        assertNotNull(element);

        // Wykonanie zdjęcia obrazującego stronę przed zamknięciem okna z powiadomieniem o ciasteczkach
        screenshot("cookies1");

        // zamykam okno usuwając je z drzewa DOM i sprawdzam ponownie istnienie elementu
        driver.findElement(By.className("close")).click();

        // Wykonanie zdjęcia obrazującego stronę po zamknięciem okna z powiadomieniem o ciasteczkach
        screenshot("cookies2");
    }

    @Test
    public void wykopRegistration() {
        // Sprawdzam czy jestem zalogowany
        Boolean isLogged = driver.findElements(By.className("logged-user")).size() > 0;
        System.out.println(isLogged);

        if(isLogged) {
            driver.get("http://www.wykop.pl/i/");
            driver.findElement(By.xpath("//img[@src='http://s1.cdn03.imgwykop.pl/static/wykoppl//img/touch/i-ico-logout.png']")).click();
        }
        driver.get("http://wykop.pl/rejestracja");
        // Pole nazwy użytkownika wypełniam używanym loginem
        // Po wyjściu z pola tekstowego, inputowi powinna zostać nadana klasa .error
        element = driver.findElement(By.id("loginField"));
        element.sendKeys("Pooffy");
        assertNotNull(element);

        element = driver.findElement(By.id("emailField"));
        element.sendKeys("superemail@gmail.com");
        assertNotNull(element);

        element = driver.findElement(By.id("passwordField"));
        element.sendKeys("tajnehaslo");
        assertNotNull(element);

        driver.findElement(By.id("ok")).click();
        driver.findElement(By.id("company")).click();

        // Po naciśnięciu submita, formularz wyświetla błędy
        driver.findElement(By.xpath("//input[@value='Załóż konto']")).click();
        assertEquals("error", driver.findElement(By.id("loginField")).getAttribute("class"));
        assertEquals("Wybrany login jest zajęty", driver.findElement(By.className("wblock")).getText());

        // Wykonanie zdjęcia
        screenshot("register");
    }

    @AfterClass
    public static void cleanp() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void screenshot(String title) {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        assertNotNull(screenshot);

        try {
            FileUtils.copyFile(screenshot, new File("./screenshots/" + title + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /*
    @Test
    public void polsatPage(){
        driver.get("http://www.teleman.pl/");
        driver.findElement(By.linkText("Polsat")).click();
        element = driver.findElement(By.linkText("Polsat"));
    }
*/
}
