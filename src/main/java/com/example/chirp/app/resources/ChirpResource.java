package com.example.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.example.chirp.app.kernel.Chirp;
import com.example.chirp.app.kernel.ChirpId;
import com.example.chirp.app.kernel.User;
import com.example.chirp.app.pub.PubChirp;
import com.example.chirp.app.pub.PubChirps;
import com.example.chirp.app.pub.PubUtils;
import com.example.chirp.app.stores.UserStore;

@Path("/")
public class ChirpResource {

  private UserStore userStore = ChirpApplication.USER_STORE;
  
//  public ChirpResource(@Context Application application) {
//    userStore = (UserStore)application.getProperties().get("user.store");
//  }
  
  
  @GET
  @Path("/chirps/{chirpId}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response getChirp(@PathParam("chirpId") String chirpId,
                           @Context UriInfo uriInfo) {

    Chirp chirp = userStore.getChirp(chirpId);
    PubChirp pubChirp = PubUtils.toPubChirp(uriInfo, chirp);
    
    ResponseBuilder builder = Response.ok(pubChirp);
    PubUtils.addLinks(builder, pubChirp.get_links());
    return builder.build();
  }
  
  @POST
  @Path("/users/{username}/chirps")
  public Response createChirp(@PathParam("username") String username,
                              @Context UriInfo uriInfo,
                              String message) {
    
    User user = userStore.getUser(username);
    Chirp chirp = new Chirp(new ChirpId(), message, user);
    user.addChirp(chirp);
    userStore.updateUser(user);

    URI location = uriInfo.getBaseUriBuilder().path("chirps").path(chirp.getId().getId()).build();
    
    return Response.created(location).build();
  }
  
  @GET
  @Path("/users/{username}/chirps")
  public Response getChirps(@PathParam("username") String username,
                            @Context UriInfo uriInfo,
                            @QueryParam("limit") String limitString,
                            @QueryParam("offset") String offsetString) {

    User user = userStore.getUser(username);
    PubChirps pubChirps = PubUtils.toPubChirps(uriInfo, user, limitString, offsetString);
    
    return Response.ok(pubChirps).build();
  }
}





















