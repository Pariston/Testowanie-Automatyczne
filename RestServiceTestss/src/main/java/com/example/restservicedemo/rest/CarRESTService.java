package com.example.restservicedemo.rest;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.service.CarManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("car")
public class CarRESTService {

    private CarManager cm = new CarManager();

    @GET
    @Path("/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCar(@PathParam("carId") Long id){
        Car c = cm.getCar(id);
        return c;
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getAllCars() {
        return cm.getAllCars();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCar(Car car) {
        Car carToAdd = new Car(car.getModel(), car.getYop());
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

    @DELETE
    public Response clearPersons(){
        cm.clearCars();
        return Response.status(200).build();
    }
}
