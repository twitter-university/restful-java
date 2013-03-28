package chirp.service.resources;

import static com.sun.jersey.api.client.ClientResponse.Status.CREATED;
import static com.sun.jersey.api.client.ClientResponse.Status.NOT_FOUND;
import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import chirp.model.Post;
import chirp.model.User;
import chirp.service.representations.PostRepresentation;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class PostResourceTest extends ResourceTest {

	private final WebResource postResource = resource().path("post");

	@Test
	public void createPost() {
		User user = getUserRepository().createUser("testuser", "Test User");

		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("content", "Test Post");
		ClientResponse response = postResource.path("testuser").post(ClientResponse.class, form);

		// status must equal CREATED
		assertEquals(CREATED.getStatusCode(), response.getStatus());

		// user must have post
		assertEquals("Test Post", user.getPosts().iterator().next().getContent());
	}

	@Test
	public void createPostForMissingUser() {
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("content", "Test Post");
		ClientResponse response = postResource.path("testuser").post(ClientResponse.class, form);

		// status must equal NOT FOUND
		assertEquals(NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void getPost() {
		User user = getUserRepository().createUser("testuser", "Test User");
		Post post = user.createPost("Test Post");
		PostRepresentation rep = postResource.path("testuser").path(post.getTimestamp().toString()).get(PostRepresentation.class);

		// timestamp and content must survive
		assertEquals(post.getTimestamp().toString(), rep.getTimestamp());
		assertEquals(post.getContent(), rep.getContent());
	}

}
