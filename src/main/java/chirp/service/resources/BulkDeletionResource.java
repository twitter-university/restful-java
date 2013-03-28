package chirp.service.resources;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.CONFLICT;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.UserRepository;

@Path("/bulk-deletion")
public class BulkDeletionResource {

	private final UserRepository repository;

	@Inject
	public BulkDeletionResource(UserRepository repository) {
		this.repository = repository;
	}

	@POST
	public Response createBulkDeletion() {
		int id = repository.createBulkDeletion();
		URI uri = UriBuilder.fromPath(String.valueOf(id)).build();
		return Response.created(uri).build();
	}

	@PUT
	@Path("{id}/{username}")
	public Response addUserToBulkDeletion(@PathParam("id") int id, @PathParam("username") String username) {
		repository.addToBulkDeletion(id, username);
		return Response.status(ACCEPTED).build();
	}

	@POST
	@Path("{id}")
	public Response commitBulkDeletion(@PathParam("id") int id) {
		if (repository.commitBulkDeletion(id))
			return Response.noContent().build();
		return Response.status(CONFLICT).build();
	}

	@DELETE
	@Path("{id}")
	public void cancelBulkDeletion(@PathParam("id") int id) {
		repository.cancelBulkDeletion(id);
	}

}