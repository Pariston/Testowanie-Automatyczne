package com.example.restservicedemo;


import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.parsing.Parser.JSON;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hsqldb.lib.StringUtil.getList;

import com.example.restservicedemo.service.CarManager;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.jayway.restassured.RestAssured;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarServiceTest {
	
	@BeforeClass
	public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}

	CarManager cm = new CarManager();

//	@Test
//	public void getCar(){
//		get("/car/0").then().assertThat().body("model", equalTo("Corsa"));
//
//		Car aCar = get("/car/0").as(Car.class);
//		assertThat(aCar.getMake(), equalToIgnoringCase("Opel"));
//	}
	
	@Test
	public void addCar(){

		delete("/car/").then().assertThat().statusCode(200);

		Car aCar = new Car(0L, "Ford", "Fiesta", 2011, 0);
		given().
		       contentType(MediaType.APPLICATION_JSON).
		       body(aCar).
		when().
		post("/car/add").then().assertThat().statusCode(201);

		get("/car/0").then().assertThat().body("model", equalTo("Fiesta"));
	}

//	@Test
//	public void removeCar(){
//		Car carToRemove = get("/car/0").as(Car.class);
//		cm.removeCar(carToRemove);
//		get("/car/0").then().assertThat().body("model", equalTo(null));
//	}

	@Test
	public void getAllCars() {
/*		String carsList = get("/car/all").andReturn().body();
		System.out.println((carsList));
		List<String> caro = from(carsList).get("cars.car");
		System.out.println(caro);*/
		//delete("/car/").then().assertThat().statusCode(200);

		Car aCar = new Car(0L, "Ford", "Fiesta", 2011, 0);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(aCar).
				when().
				post("/car/add").then().assertThat().statusCode(201);

		String cars = get("/car/all").asString();
		List<Car> returnedCars = from(cars).get("car");
		assertNotNull(returnedCars);
	}
}
