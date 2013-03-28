package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserResourceTest extends ResourceTest {

	private final WebResource userResource = resource().path("user");

	@Test
	public void createUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("realname", "Test User");
		userResource.path("testuser").put(form);
		assertEquals("Test User", getUserRepository().getUser("testuser").getRealname());
	}

}
