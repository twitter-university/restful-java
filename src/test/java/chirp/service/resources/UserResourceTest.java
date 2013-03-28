package chirp.service.resources;

import static com.sun.jersey.api.client.ClientResponse.Status.CREATED;
import static com.sun.jersey.api.client.ClientResponse.Status.FORBIDDEN;
import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import chirp.model.User;
import chirp.service.representations.UserCollectionRepresentation;
import chirp.service.representations.UserRepresentation;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserResourceTest extends ResourceTest {

	private final WebResource userResource = resource().path("user");

	@Test
	public void createUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("realname", "Test User");
		ClientResponse response = userResource.path("testuser").put(ClientResponse.class, form);

		// status must equal CREATED
		assertEquals(CREATED.getStatusCode(), response.getStatus());

		// location must equal "/user/testuser"
		assertEquals(userResource.path("testuser").getURI(), response.getLocation());

		// user must exist in repository
		assertEquals("Test User", getUserRepository().getUser("testuser").getRealname());
	}

	@Test
	public void createDuplicateUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("realname", "Test User");
		
		// put the same user twice, ignoring the first response
		userResource.path("testuser").put(form);
		ClientResponse response = userResource.path("testuser").put(ClientResponse.class, form);

		// status must equal FORBIDDEN
		assertEquals(FORBIDDEN.getStatusCode(), response.getStatus());
	}

	@Test
	public void getUser() {
		User user = getUserRepository().createUser("testuser", "Test User");
		WebResource resource = userResource.path("testuser");
		UserRepresentation rep = resource.get(UserRepresentation.class);

		// self-link and realname must survive
		assertEquals(resource.getURI().getPath(), rep.getSelf().getPath());
		assertEquals(user.getRealname(), rep.getRealname());
	}

	@Test
	public void getUsers() {
		getUserRepository().createUser("testuser", "Test User");
		UserCollectionRepresentation rep = userResource.get(UserCollectionRepresentation.class);

		// self-links must survive
		assertEquals(userResource.getURI().getPath(), rep.getSelf().getPath());
		assertEquals(userResource.path("testuser").getURI().getPath(), rep.getUsers().iterator().next().getSelf().getPath());
	}

}
