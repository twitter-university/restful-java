package com.example.chirp.app.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response sayHelloWithPathParam(@PathParam("someName") String name,
			                                  @HeaderParam("X-NewCircle-Echo") String headerValue) {

		// return "Hello "+name+"!";

//		Response.ok()
//		.entity(Entity.entity("Hello "+name+"!", MediaType.TEXT_PLAIN))
//				.header("X-NewCircle-Echo-Response", headerValue)
//				.build();
//
//		Response.status(200)
//		.entity(Entity.entity("Hello "+name+"!", MediaType.TEXT_PLAIN))
//				.header("X-NewCircle-Echo-Response", headerValue)
//				.build();

		return Response.ok("Hello "+name+"!")
				.header("X-NewCircle-Echo-Response", headerValue)
				.build();
	}
}








