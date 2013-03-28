package chirp.service.representations;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.User;

import com.sun.jersey.server.linking.Ref;

public class UserRepresentation {

	@Ref("user/{username}")
	private URI self;

	private final String username;
	private final String realname;

	public UserRepresentation(User user, boolean summary) {
		username = user.getUsername();
		realname = summary ? null : user.getRealname();
	}

	@JsonCreator
	public UserRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("realname") String realname) {
		this.self = self;
		this.username = null;
		this.realname = realname;
	}

	public URI getSelf() {
		return self;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

}
