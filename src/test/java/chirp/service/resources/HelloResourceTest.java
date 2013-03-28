package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class HelloResourceTest extends ResourceTest {

	private final WebResource helloResource = resource().path("hello");

	@Test
	public void getHelloResourceMustSayHello() {
		String hello = helloResource.queryParam("name", "REST").get(String.class);
		assertEquals("Hello, REST!", hello);
	}

	@Test
	public void getHelloSubresourceMustSayHello() {
		String hello = helloResource.path("REST").get(String.class);
		assertEquals("Hello, REST!", hello);
	}

	@Test
	public void postHelloResourceMustSayHello() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("name", "REST");
		String hello = helloResource.post(String.class, form);
		assertEquals("Hello, REST!", hello);
	}

}
