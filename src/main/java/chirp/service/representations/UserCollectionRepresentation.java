package chirp.service.representations;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Ref;

@Link(value = @Ref("user"), rel = "self")
public class UserCollectionRepresentation {

	@Ref("user")
	private URI self;

	private final Collection<UserRepresentation> users;

	public UserCollectionRepresentation(Collection<User> users) {
		this.users = new LinkedList<UserRepresentation>();
		for (User user : users) {
			this.users.add(new UserRepresentation(user, true));
		}
	}

	@JsonCreator
	public UserCollectionRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("users") Collection<UserRepresentation> users) {
		this.self = self;
		this.users = users;
	}

	public URI getSelf() {
		return self;
	}

	public Collection<UserRepresentation> getUsers() {
		return users;
	}

}
