package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

import java.util.List;

@Path("person")
public class PersonRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("personId") Long id){
		Person p = pm.getPerson(id);
		return p;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		Person personToAdd = new Person(person.getFirstName(), person.getYob());
		pm.addPerson(personToAdd);
		return Response.status(201).entity("Person").build(); 
	}

	@GET
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Person> getAllPersons() {
		return pm.getAllPersons();
	}

	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public String removePerson(Person person) {
		Person personToRemove = pm.getPerson(person.getId());
		pm.removePerson(personToRemove);
		return "Usunieto";
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String test(){
		return "REST API /person is running";
	}
	
	@DELETE
	public Response clearPersons(){
		pm.clearPersons();
		return Response.status(200).build();
	}

}
