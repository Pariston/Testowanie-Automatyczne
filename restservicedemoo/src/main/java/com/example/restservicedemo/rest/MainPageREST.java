package com.example.restservicedemo.rest;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by elzoy on 5/22/2016.
 */

@Path("/")
public class MainPageREST {
    private PersonManager pm = new PersonManager();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll() {
        return pm.getAllPersons();
    }
}
