package com.example.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.example.chirp.app.ChirpApplication;
import com.example.chirp.app.kernel.User;
import com.example.chirp.app.pub.PubUser;
import com.example.chirp.app.pub.PubUtils;
import com.example.chirp.app.stores.UserStore;

@Path("/")
public class UserResource {

	private UserStore userStore = ChirpApplication.USER_STORE;
	
	@PUT
	@Path("/users/{username}")
	public Response createUser(@BeanParam CreateUserRequest request) {

		request.validate();
		
		userStore.createUser(request.username, request.realName);
		URI location = request.uriInfo.getBaseUriBuilder()
				.path("users").path(request.username).build();
		
		return Response.created(location).build();
	}

  @GET
  @Path("/users/{username}")
  @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public Response getUser(@PathParam("username") String username,
                          @Context UriInfo uriInfo,
                          @QueryParam("limitString") String limitString, 
                          @QueryParam("offsetString") String offsetString) {

    User user = userStore.getUser(username);
    PubUser pubUser = PubUtils.toPubUser(uriInfo, user, limitString, offsetString);
  
    
    
    ResponseBuilder builder = Response.ok(pubUser);
    PubUtils.addLinks(builder, pubUser.get_links());
    return builder.build();
  }
}

































