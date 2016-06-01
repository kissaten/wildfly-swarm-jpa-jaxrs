package com.example.rest;

import com.example.models.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonEndpoint {
  @GET
  @Path("/get")
  @Produces(MediaType.APPLICATION_JSON)
  public Person get(){
    Person person = new Person();
    person.setFirstName("John");
    person.setLastName("Smith");
    person.setJob("Head");
    return person;
  }

  @POST
  @Path("/post")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(Person person){
    String result = "Record entered: " + person;
    return Response.status(201).entity(result).build();
  }
}
