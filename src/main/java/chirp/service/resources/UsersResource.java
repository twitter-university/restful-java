package chirp.service.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.UserRepresentation;

import com.google.inject.Inject;

@Path("/users")
public class UsersResource {

	private UserRepository userRepository;

	@Inject
	public UsersResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@POST
	public Response createUser(@FormParam("username") String username,
			@FormParam("realname") String realname) {

			userRepository.createUser(username, realname);
			URI location = UriBuilder.fromPath(username).build();
			return Response.created(location).build();
	}
	
	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRepresentation getUser(@PathParam("username") String username) {
		return new UserRepresentation(userRepository.getUser(username));
	}
	
	@GET
	// @Path("all") -- optional, could be the root resource all
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserRepresentation> getUsers() {
		Collection<UserRepresentation> ur = new ArrayList<UserRepresentation>();
		for (User u: userRepository.getUsers())
			ur.add(new UserRepresentation(u));
		return Collections.unmodifiableCollection(ur);
	}

}
