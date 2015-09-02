package com.example.chirp.app.resources;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.example.chirp.app.pub.PubChirp;

public class ChirpResourceTest extends ResourceTestSupport {
  
  @Test
  public void testGetChirp() {
    Response response = target("/chirps/wars01").request()
        .accept(MediaType.APPLICATION_JSON).get();
    
    Assert.assertEquals(200, response.getStatus());
    
    PubChirp pubChirp = response.readEntity(PubChirp.class);
    Assert.assertEquals(pubChirp.getId(), "wars01");
    Assert.assertEquals(pubChirp.getContent(), "Do or do not. There is no try.");

    URI selfLink = URI.create("http://localhost:9998/chirps/wars01");
    Assert.assertEquals(selfLink, pubChirp.get_links().get("self"));
    Assert.assertEquals(selfLink, response.getLink("self").getUri());

    URI chirpsLink = URI.create("http://localhost:9998/users/yoda/chirps");
    Assert.assertEquals(chirpsLink, pubChirp.get_links().get("chirps"));
    Assert.assertEquals(chirpsLink, response.getLink("chirps").getUri());

    URI userLink = URI.create("http://localhost:9998/users/yoda");
    Assert.assertEquals(userLink, pubChirp.get_links().get("user"));
    Assert.assertEquals(userLink, response.getLink("user").getUri());
  }
}


