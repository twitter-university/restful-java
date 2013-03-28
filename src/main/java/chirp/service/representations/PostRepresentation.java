package chirp.service.representations;

import java.net.URI;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;

import com.sun.jersey.server.linking.Ref;

public class PostRepresentation {

	@Ref("/post/{username}/{timestamp}")
	private URI self;

	private final String username;
	private final String timestamp;
	private final String content;

	public PostRepresentation(Post post, boolean summary) {
		username = post.getUser().getUsername();
		timestamp = post.getTimestamp().toString();
		content = summary ? null : post.getContent();
	}

	@JsonCreator
	public PostRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("content") String content) {
		this.self = self;
		this.username = null;
		this.timestamp = null;
		this.content = content;
	}

	public URI getSelf() {
		return self;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public String getTimestamp() {
		return timestamp;
	}

	public String getContent() {
		return content;
	}

}
