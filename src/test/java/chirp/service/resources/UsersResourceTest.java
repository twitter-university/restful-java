package chirp.service.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.junit.Test;

import chirp.service.representations.UserRepresentation;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UsersResourceTest extends ResourceTest {

	private WebResource users = resource().path("users");
	
	private MultivaluedMap<String, String> getForm() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("username", "testuser");
		form.add("realname", "TestUser");
		return form;
	}

	@Test
	public void postUsersMustCreateUser() {
		ClientResponse response = users.post(ClientResponse.class, getForm());
		
		// assertEquals(201,response.getStatus());
		assertEquals(Response.Status.CREATED.getStatusCode(),response.getStatus());
		
		assertEquals("TestUser", super.getUserRepository().getUser("testuser")
				.getRealname());
	}
	
	@Test
	public void postDuplicateUsersMustFail() {
		// create the user and verify success
		postUsersMustCreateUser();

		ClientResponse response = users.post(ClientResponse.class, getForm());
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(),response.getStatus());
	}
	
	@Test
	public void getUserShouldGetAUser() {
		postUsersMustCreateUser();
		UserRepresentation ur = resource().path("users").path("testuser").get(UserRepresentation.class);
		assertNotNull(ur);
		assertEquals("testuser",ur.getUsername());
	}

	
}
