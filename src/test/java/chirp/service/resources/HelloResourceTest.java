package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class HelloResourceTest extends ResourceTest {

	@Test
	public void helloResourceMustSayHello() {
		String hello = resource().path("hello").get(String.class);
		assertEquals("Hello!", hello);
	}

}
