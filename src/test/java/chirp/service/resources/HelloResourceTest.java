package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

public class HelloResourceTest extends ResourceTest {

	private final WebResource helloResource = resource().path("hello");

	@Test
	public void helloResourceMustSayHello() {
		String hello = helloResource.queryParam("name", "REST").get(String.class);
		assertEquals("Hello, REST!", hello);
	}

	@Test
	public void helloSubresourceMustSayHello() {
		String hello = helloResource.path("REST").get(String.class);
		assertEquals("Hello, REST!", hello);
	}

}
