package com.example.restservicedemo;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Created by elzoy on 5/30/2016.
 */
public class CarServiceTest {

    private static final String CAR_MODEL = "SzybkiFiat";
    private PersonManager pm = new PersonManager();
    private CarManager cm = new CarManager();

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";
    }

    @Test
    public void addCars() throws JSONException {
		/*
			Robie tutaj czarodziejski myk. Id jest generowane automatycznie, wiec na nic zda mi sie wchodzenie
			na adres /car/1, gdzie 1 to recznie podany identyfikator. Baza danych pamieta historie Id nawet po jej
			wyczyszczeniu, wiec testy zadzialaja tylko raz. Zrobimy to inaczej.
		 */

        delete("/car/").then().assertThat().statusCode(200);
        // czyszcze tabele Car, wiec wiem, ze po dodaniu pierwszego, w tabeli bedzie tylko 1 wynik

        Car car = new Car(CAR_MODEL, 2011);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(car).
                when().
                post("/car/add").then().assertThat().statusCode(201);
        // dodaje samochod

        String cars = get("/car/all").asString();
        // podstrona /car/all zwroci mi string jsona zawierajacy wszystkie samochody (w tym przypadku ten jeden)
        HashMap<Car, Person> returnedCars = from(cars).get("car"); // <--- zapisany string w postaci jsona

        JSONObject json = new JSONObject(returnedCars); // <--- konwersja stringu jsonowego na obiekt jsonowy

        // teraz moge wyciagnac id w ten sposob: json.get("id");

        Car rCar = get("/car/" + json.get("id")).as(Car.class);

        assertThat(CAR_MODEL, equalToIgnoringCase(rCar.getModel()));
    }

    @Test
    public void gettingCars() throws JSONException {
        cm.clearCars();
        pm.clearPersons();

        Car c = new Car(CAR_MODEL, 2011);
        cm.addCar(c);

        String cars = get("/car/all").asString();
        HashMap<Car, Person> returnedCars = from(cars).get("car"); // <--- zapisany string w postaci jsona
        assertNotNull(returnedCars);

        cm.clearCars();
        cars = get("/car/all").asString();
        assertEquals("null", cars);
    }

}
