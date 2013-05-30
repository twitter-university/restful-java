package chirp.service.resources;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.junit.Test;

import chirp.model.Post;
import chirp.model.User;
import chirp.service.representations.PostRepresentation;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class PostResourceTest extends ResourceTest {

	private WebResource posts = resource().path("posts");

	@Test
	public void createPostForExistingUserMustPass() {
		super.getUserRepository().createUser("testuser", "Test User");

		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("content", "test case content");

		ClientResponse response = posts.path("testuser").post(
				ClientResponse.class, form);

		assertEquals(Response.Status.CREATED.getStatusCode(),
				response.getStatus());

		assertEquals("test case content",
				super.getUserRepository().getUser("testuser").getPosts()
						.iterator().next().getContent());
	}

	@Test
	public void createPostForNonExistingUserMustFail() {
		// assertEquals(201,response.getStatus());
		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("content", "test case content");

		ClientResponse response = posts.path("foo").post(ClientResponse.class,
				form);

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				response.getStatus());

	}

	@Test
	public void getPost() {
		User user = super.getUserRepository().createUser("testuser",
				"Test User");
		Post post = user.createPost("test message");

		ClientResponse response = posts.path(post.getUser().getUsername())
				.path(post.getTimestamp().toString()).get(ClientResponse.class);
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		
		PostRepresentation pr = response.getEntity(PostRepresentation.class);
		assertEquals("test message", pr.getContent());
		
	}

}
