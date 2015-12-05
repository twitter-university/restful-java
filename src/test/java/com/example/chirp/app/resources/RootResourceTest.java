package com.example.chirp.app.resources;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

public class RootResourceTest extends ResourceTestSupport {

	@Test
	public void testExampleDotCom() {
  	Client client = ClientBuilder.newClient();
  	Response response = client.target("https://example.com/").request().get();
    Assert.assertEquals(200, response.getStatus());
	}
	
  @Test
  public void rootResourceTest() {
  	Response response = target("/").request().get();
    Assert.assertEquals(200, response.getStatus());
  }
}