package com.example.restservicedemo.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.service.CarManager;

@Path("car")
public class CarRESTService {
	private CarManager cm = new CarManager();

	@GET
	@Path("/{carId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("carId") Long id){
		return cm.getCar(id);
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
	@Produces(MediaType.TEXT_HTML)
	public Response addNewCar(Car car) {
		//Car aCar = new Car(2, "Ford", "Fiesta", 2011, car);
		System.out.println(car);
		cm.addCar(car);
		return Response.status(201).entity("success").build();
	}

	@DELETE
	public Response clearCars(){
		cm.clearCars();
		return Response.status(200).build();
	}
}
