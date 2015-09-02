package com.example.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.example.chirp.app.ChirpApplication;
import com.example.chirp.app.kernel.User;
import com.example.chirp.app.pub.PubUser;
import com.example.chirp.app.pub.PubUtils;
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
  public String getUserPlain(@PathParam("username") String username) {
    User user = userStore.getUser(username);
    String realName = user.getRealName();
    return realName;
  }

  @GET
  @Path("/{username}")
  @Produces(MediaType.APPLICATION_JSON)
  public PubUser getUserJson(@PathParam("username") String username,
                             @Context UriInfo uriInfo) {

    User user = userStore.getUser(username);
    return PubUtils.toPubUser(uriInfo, user);
  }
}



















