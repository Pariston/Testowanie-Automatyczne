package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertSame;

import javax.ws.rs.core.MediaType;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.service.CarManager;
import com.example.restservicedemo.service.PersonManager;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonServiceTest {
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	private PersonManager pm = new PersonManager();
	private CarManager cm = new CarManager();

	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	public void addPersons() throws JSONException {
		/*
			Robie tutaj czarodziejski myk. Id jest generowane automatycznie, wiec na nic zda mi sie wchodzenie
			na adres /person/1, gdzie 1 to recznie podany identyfikator. Baza danych pamieta historia Id nawet po jej
			wyczyszczeniu, wiec testy zadzialaja tylko raz. Zrobimy to inaczej.
		 */

		delete("/person/").then().assertThat().statusCode(200);
		// czyszcze tabele Person, wiec wiem, ze po dodaniu pierwszego, w tabeli bedzie tylko 1 wynik
		
		Person person = new Person(PERSON_FIRST_NAME, 1976);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().	     
	    post("/person/add").then().assertThat().statusCode(201);
		// dodaje uzytkownika

		String persons = get("/person/all").asString();
		// podstrona /person/all zwroci mi string jsona zawierajacy wszystkich userow (w tym przypadku ten jeden)
		HashMap<Car, Person> returnedCars = from(persons).get("person"); // <--- zapisany string w postaci jsona

		JSONObject json = new JSONObject(returnedCars); // <--- konwersja stringu jsonowego na obiekt jsonowy

		// teraz moge wyciagnac id w ten sposob: json.get("id");

		Person rPerson = get("/person/" + json.get("id")).as(Person.class);
		
		assertThat(PERSON_FIRST_NAME, equalToIgnoringCase(rPerson.getFirstName()));
	}

	@Test
	public void gettingPersons() throws JSONException {
		cm.clearCars();
		pm.clearPersons();

		Person p = new Person(PERSON_FIRST_NAME, 1976);
		pm.addPerson(p);

		String persons = get("/person/all").asString();
		HashMap<Car, Person> returnedPersons = from(persons).get("person"); // <--- zapisany string w postaci jsona
		assertNotNull(returnedPersons);

		pm.clearPersons();
		persons = get("/person/all").asString();
		assertEquals("null", persons);
	}

}
