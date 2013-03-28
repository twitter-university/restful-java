package chirp.service.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.PostRepresentation;

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


	@GET
	@Path("{timestamp}")
	@Produces(APPLICATION_JSON)
	public PostRepresentation getPost(@PathParam("username") String username, @PathParam("timestamp") String timestamp) {
		User user = repository.getUser(username);
		return new PostRepresentation(user.getPost(new Timestamp(timestamp)), false);
	}

	@GET
	@Produces(APPLICATION_JSON)
	public Collection<PostRepresentation> getPosts(@PathParam("username") String username) {
		User user = repository.getUser(username);
		Collection<PostRepresentation> rep = new LinkedList<PostRepresentation>();
		for (Post post : user.getPosts()) {
			rep.add(new PostRepresentation(post, true));
		}
		return rep;
	}
	
}
