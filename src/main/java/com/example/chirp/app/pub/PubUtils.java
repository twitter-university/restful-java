package com.example.chirp.app.pub;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.example.chirp.app.kernel.Chirp;
import com.example.chirp.app.kernel.User;

public class PubUtils {
 
  public static PubChirp toPubChirp(UriInfo uriInfo, Chirp chirp) {
    User user = chirp.getUser();
    Map<String,URI> links = new LinkedHashMap<>();

    links.put("self", uriInfo.getBaseUriBuilder()
      //.path("users").path(user.getUsername())
      .path("chirps").path(chirp.getId().getId()).build());
   
    links.put("chirps", uriInfo.getBaseUriBuilder()
      .path("users").path(user.getUsername()).path("chirps").build());
    
    links.put("user", uriInfo.getBaseUriBuilder()
      .path("users").path(user.getUsername()).build());

    return new PubChirp(links, chirp.getId().getId(), chirp.getContent());
  }
  
  public static ResponseBuilder addLinks(ResponseBuilder builder, Map<String, URI> links) {
    for (Map.Entry<String, URI> entry : links.entrySet()) {
      builder.link(entry.getValue(), entry.getKey());
    }
    return builder;
  }
  
  public static PubUser toPubUser(UriInfo uriInfo, User user) {
    Map<String,URI> links = new LinkedHashMap<>();

    URI selfLink = uriInfo.getBaseUriBuilder().path("users").path(user.getUsername()).build();
    links.put("self", selfLink);

    links.put("chirps", uriInfo.getBaseUriBuilder()
      .path("users").path(user.getUsername()).path("chirps").build());

    return new PubUser(links, user.getUsername(), user.getRealName());
  }
}