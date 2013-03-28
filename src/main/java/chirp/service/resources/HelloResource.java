package chirp.service.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("hello")
public class HelloResource {

	@GET
	public String getHello(@QueryParam("name") String name) {
		return String.format("Hello, %s!", name);
	}

	@GET
	@Path("{name}")
	public String getHelloSubresource(@PathParam("name") String name) {
		return String.format("Hello, %s!", name);
	}

	@POST
	public String postHello(@FormParam("name") String name) {
		return String.format("Hello, %s!", name);
	}

}
