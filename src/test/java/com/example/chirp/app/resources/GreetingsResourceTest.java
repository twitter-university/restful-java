package com.example.chirp.app.resources;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

public class GreetingsResourceTest extends ResourceTestSupport {

	@Test
	public void testSayHello() {
		Response response = target("/greetings").request().get();
		Assert.assertEquals(200, response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Hello dude!", content);
	}

	@Test
	public void testSayHelloWithQueryParam() {
		Response response = target("/greetings").queryParam("name", "Tom").request().get();
		Assert.assertEquals(200, response.getStatus());

		String content = response.readEntity(String.class);
		Assert.assertEquals("Hello Tom!", content);
	}
}







