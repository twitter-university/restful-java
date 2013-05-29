package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UsersResourceTest extends ResourceTest {

	private WebResource users = resource().path("users");

	@Test
	public void postUsersMustCreateUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("username", "testuser");
		form.add("realname", "TestUser");
		users.post(form);
		assertEquals("TestUser", super.getUserRepository().getUser("testuser")
				.getRealname());
	}

}
