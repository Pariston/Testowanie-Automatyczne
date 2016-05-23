package com.example.restservicedemo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.CarManager;

import java.util.List;

@Path("car")
public class CarRESTService {
	private CarManager cm = new CarManager();

	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("carId") Long id) {
		Car car = cm.getCar(id);
		return car;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Car car) {
 		cm.addCar(car);
		return Response.status(201).entity("Car").build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewCar(Car car) {
		Car carToAdd = new Car(car.getId(), car.getMake(), car.getModel(), car.getYop(), car.getOwnerId());
		cm.addCar(carToAdd);
		return Response.status(201).entity("success").build();
	}

	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeCar(Car car) {
		Car carToRemove = cm.getCar(car.getId());
		cm.removeCar(carToRemove);
		return Response.status(201).entity("success").build();
	}

	@GET
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getAllCars() {
		return cm.getAllCars();
	}

	@GET
	@Path("/allWithOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Car> getAllCarsWithOwner() {
		return cm.getCarsWithOwner();
	}

	@POST
	@Path("/byOwner")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Car> gettOwnerCars(Person person) {
		return cm.getCarsByOwnerId(person.getId());
	}

	@DELETE
	public Response clearCars(){
		cm.clearCars();
		return Response.status(200).build();
	}
}
