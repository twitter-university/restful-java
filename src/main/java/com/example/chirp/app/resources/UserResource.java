package com.example.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.chirp.app.ChirpApplication;
import com.example.chirp.app.kernel.User;
import com.example.chirp.app.stores.UserStore;

@Path("/users")
public class UserResource {

	private UserStore userStore = ChirpApplication.USER_STORE;
	
	@PUT
	@Path("/{username}")
	public Response createUser(@BeanParam CreateUserRequest request) {

		request.validate();
		
		userStore.createUser(request.username, request.realName);
		URI location = request.uriInfo.getBaseUriBuilder()
				.path("users").path(request.username).build();
		
		return Response.created(location).build();
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("username") String username) {
		User user = userStore.getUser(username);
		String realName = user.getRealName();
		return realName;
	}
}









