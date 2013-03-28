package chirp.service.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.PostCollectionRepresentation;
import chirp.service.representations.PostRepresentation;

@Path("post/{username}")
public class PostResource {

	private final UserRepository repository;

	@Inject
	public PostResource(UserRepository repository) {
		this.repository = repository;
	}

	@POST
	public Response createPost(@PathParam("username") String username, @FormParam("content") String content, @Context Request request) {
		User user = repository.getUser(username);
		EntityTag eTag = getEntityTag(user);

		// if "If-Match" precondition fails, return 412 PRECONDITION FAILED
		ResponseBuilder response = request.evaluatePreconditions(eTag);
		if (response != null)
			return response.build();

		// if "If-Match" precondition succeeds, return 201 CREATED with the new eTag
		Post post = repository.getUser(username).createPost(content);
		URI uri = UriBuilder.fromPath(post.getTimestamp().toString()).build();
		return Response.created(uri).tag(eTag).build();
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
	public Response getPosts(@PathParam("username") String username, @Context Request request) {
		User user = repository.getUser(username);
		EntityTag eTag = getEntityTag(user);

		// if "If-None-Match" precondition fails, return 304 NOT MODIFIED
		ResponseBuilder response = request.evaluatePreconditions(eTag);
		if (response != null)
			return response.build();

		// if "If-None-Match" precondition succeeds, return 200 with the new eTag
		return Response.ok(new PostCollectionRepresentation(user, user.getPosts())).tag(eTag).build();
	}

	private EntityTag getEntityTag(User user) {
		Collection<Post> posts = user.getPosts();
		return new EntityTag(String.valueOf(posts.hashCode()));
	}

}
