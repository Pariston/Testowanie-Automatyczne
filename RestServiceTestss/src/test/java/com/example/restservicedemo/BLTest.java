package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import com.example.restservicedemo.service.CarManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {

	static PersonManager pm = new PersonManager();
	static CarManager cm = new CarManager();

//	@BeforeClass
//	static public void dropTables() throws SQLException {
//		cm.dropTable();
//		pm.dropTable();
//	}

	@Before
	public void clearAll() {
//		pm.clearPersons();
//		cm.clearCars();
	}

	@Test
	public void checkCarAdding() {

		Car c = new Car();
		c.setModel("Syrena");
		c.setYop(1973);

		assertEquals(1, cm.addCar(c));
	}

	@Test
	public void checkSell() {

		Car c1 = new Car();
		c1.setModel("Syrena");
		c1.setYop(1973);

		Car c2 = new Car();
		c2.setModel("Fiat Punto");
		c2.setYop(1999);

		assertEquals(1, cm.addCar(c1));
		assertEquals(1, cm.addCar(c2));

		List<Car> cars = cm.getAllCars();

		assertTrue(cars.size() > 0);

		Car carToSell = cars.get(1);

		Person p1 = new Person();
		p1.setFirstName("Zieliński");
		p1.setYob(1978);

		Person p2 = new Person();
		p2.setFirstName("Kowalski");
		p2.setYob(1978);

		assertEquals(1, pm.addPerson(p1));
		assertEquals(1, pm.addPerson(p2));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() > 1);

		Person owner = persons.get(1);

		cm.sellCar(carToSell, owner);

		Car rCar = cm.getCarWithOwner(carToSell);

		//assertEquals(owner.getFirstName(), rCar.getOwner().getFirstName());

	}

	@Test
	public void checkClearingCars() {
		Car car = new Car();
		car.setModel("Opel");
		car.setYop(2008);
		cm.addCar(car);

		assertTrue(cm.getAllCars().size() > 0);
		cm.clearCars();
		assertTrue(cm.getAllCars().size() == 0);
	}

	@Test
	public void checkClearingPersons() {
		cm.clearCars();	//dla pozbycia sie problemu zewnetrznego klucza person - car w bazie danych

		Person person = new Person();
		person.setFirstName("Daniel");
		person.setYob(1994);
		pm.addPerson(person);

		assertTrue(pm.getAllPersons().size() > 0);
		pm.clearPersons();
		assertTrue(pm.getAllPersons().size() == 0);
	}


}
