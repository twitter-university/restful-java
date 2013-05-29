package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

public class HelloResourceTest extends ResourceTest {
	
	private WebResource greeting = resource().path("greeting");

	@Test
	public void helloResourceMustSayHello() {
		String hello = greeting.get(String.class);
		assertEquals("Hello!", hello);
	}
	
	@Test
	public void helloResourceSomebodyMustSayHelloWithName() {
		String hello = greeting.path("Cisco").get(String.class);
		assertEquals("Hello, Cisco!", hello);
	}


}
