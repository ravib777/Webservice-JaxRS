package com;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.beans.*;

@Path("person")
public class MessageRestService {

	static PersonService personService= new PersonService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnPersons() {

		List<Person> persons= personService.getPersons();

		return Response.status(200).entity(persons).build();
	}
	
	@GET
	@Path("{count}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnPerson(@PathParam("count") Integer count) {

		Person person= personService.getPerson(count);

		return Response.status(200).entity(person).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerson(Person person)
	{
		personService.createPerson(person);
		return Response.status(200).entity(person).build();
	}
	
	@PUT
	@Path("{count}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("count") Integer count,Person person)
	{
		personService.updatePerson(count, person);
		return Response.status(200).entity(person).build();
	}
	
	@Path("{count}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("count") Integer count)
	{
		personService.deletePerson(count);
		return Response.status(200).build();
	}
	

}