package chirp.service.representations;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;

@Links({ @Link(value = @Ref("/posts/{username}"), rel = "related"),
		@Link(value = @Ref("/users"), rel = "up"),
		@Link(value = @Ref("/users/{username}"), rel = "self") })
public class UserRepresentation {

	@Ref("/users/{username}")
	private URI self;
	private final String username;
	private final String realname;

	public UserRepresentation(User user, boolean summary) {
		String tmpUsername = user.getUsername();
		this.username = tmpUsername;
		this.realname = summary ? null : user.getRealname();
	}

	@JsonCreator
	public UserRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("username") String username,
			@JsonProperty("realname") String realname) {
		this.self = self;
		this.username = username;
		this.realname = realname;
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

	public URI getSelf() {
		return self;
	}

}
