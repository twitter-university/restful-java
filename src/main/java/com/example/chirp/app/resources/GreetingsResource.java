package com.example.chirp.app.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/greetings")
public class GreetingsResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@DefaultValue("dude") @QueryParam("name") String name) {
		return "Hello "+name+"!";
	}

	@GET
	@Path("/{someName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloWithPathParam(@PathParam("someName") String name) {
		return "Hello "+name+"!";
	}
}








