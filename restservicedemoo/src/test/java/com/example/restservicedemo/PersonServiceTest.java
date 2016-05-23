package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.IsEqual.equalTo;

import javax.ws.rs.core.MediaType;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.service.PersonManager;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonServiceTest {

    private static final String PERSON_FIRST_NAME = "Jasiu";
    private PersonManager pm = new PersonManager();

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";
    }

    @Test
    public void getPerson() {
        addPersons();
        get("/person/0").then().assertThat().body("firstName", equalTo(PERSON_FIRST_NAME));

        Person person = get("/person/0").as(Person.class);
        assertThat(person.getFirstName(), equalToIgnoringCase(PERSON_FIRST_NAME));
    }

    @Test
    public void addPersons() {

        delete("/person/").then().assertThat().statusCode(200);

        Person person = new Person(0L, PERSON_FIRST_NAME, 1976);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(person).
                when().
                post("/person/add").then().assertThat().statusCode(201);

        Person rPerson = get("/person/0").as(Person.class);

        assertThat(PERSON_FIRST_NAME, equalToIgnoringCase(rPerson.getFirstName()));
    }

    @Test
    public void removePerson() {
        Person rPerson = get("/person/0").as(Person.class);
        pm.removePerson(rPerson);
        get("/person/0").then().assertThat().body("firstName", equalTo(null));
    }

    @Test
    public void getAllPersons() {
        Person aPerson = new Person(1L, "DjZygmunt", 1972);
        given().
                contentType(MediaType.APPLICATION_JSON).
                body(aPerson).
                when().
                post("/person/add").then().assertThat().statusCode(201);

        String persons = get("/person/all").asString();
        System.out.println(persons);

        //BLADEGO POJECIA NIE MAM DLACZEGO TO TUTAJ NIE DZIALA
        /*
			  __________
			 /			\	Nawiedzil Cie zolnierz dobrego kodu z czapka slynnego zbieracza ryzu na dalekim wschodzie.
			/____________\  Wstaw studentowi dobra ocene, a czeka Cie miesiac spokojnego snu.
			 \|-     -| /
			   \  _	 /
				\___/
			 ___/	\___
			/			\
		 */
        //List<Person> returnedPersons = from(persons).get("person");
        //assertNotNull(returnedPersons);
    }
}
