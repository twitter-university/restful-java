package chirp.service.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.UserCollectionRepresentation;
import chirp.service.representations.UserRepresentation;

@Path("user")
public class UserResource {

	private final UserRepository repository;

	@Inject
	public UserResource(UserRepository repository) {
		this.repository = repository;
	}

	@PUT
	@Path("{username}")
	public Response createUser(@PathParam("username") String username, @FormParam("realname") String realname, @Context Request request) {
		EntityTag eTag = getEntityTag();

		// if "If-Match" precondition fails, return 412 PRECONDITION FAILED
		ResponseBuilder response = request.evaluatePreconditions(eTag);
		if (response != null)
			return response.build();

		repository.createUser(username, realname);
		URI uri = UriBuilder.fromPath("").build();
		return Response.created(uri).tag(eTag).build();
	}

	@GET
	@Path("{username}")
	@Produces(APPLICATION_JSON)
	public UserRepresentation getUser(@PathParam("username") String username) {
		return new UserRepresentation(repository.getUser(username), false);
	}

	@GET
	@Produces(APPLICATION_JSON)
	public Response getUsers(@Context Request request) {
		EntityTag eTag = getEntityTag();

		ResponseBuilder response = request.evaluatePreconditions(eTag);
		if (response != null)
			return response.build();

		return Response.ok(new UserCollectionRepresentation(repository.getUsers())).tag(eTag).build();
	}

	private EntityTag getEntityTag() {
		Collection<User> users = repository.getUsers();
		return new EntityTag(String.valueOf(users.hashCode()));
	}

}
