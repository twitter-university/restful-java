package chirp.service.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.UserRepository;

@Path("user")
public class UserResource {

	private final UserRepository repository;

	@Inject
	public UserResource(UserRepository repository) {
		this.repository = repository;
	}

	@PUT
	@Path("{username}")
	public Response createUser(@PathParam("username") String username, @FormParam("realname") String realname) {
		repository.createUser(username, realname);
		URI uri = UriBuilder.fromPath("").build();
		return Response.created(uri).build();
	}

}
