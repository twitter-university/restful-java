package chirp.service.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.Post;
import chirp.model.UserRepository;

@Path("post/{username}")
public class PostResource {

	private final UserRepository repository;

	@Inject
	public PostResource(UserRepository repository) {
		this.repository = repository;
	}

	@POST
	public Response createPost(@PathParam("username") String username, @FormParam("content") String content) {
		Post post = repository.getUser(username).createPost(content);
		URI uri = UriBuilder.fromPath(post.getTimestamp().toString()).build();
		return Response.created(uri).build();
	}

}
