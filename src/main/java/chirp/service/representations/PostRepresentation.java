package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.service.resources.PostResource;

public class PostRepresentation {

	private final URI self;
	private final String content;
	private final String timestamp;

	public PostRepresentation(Post post) {
		this.content = post.getContent();
		this.timestamp = post.getTimestamp().toString();
		this.self = UriBuilder.fromResource(PostResource.class)
				.path(this.timestamp).build(post.getUser().getUsername());
	}

	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
			@JsonProperty("content") String content,
			@JsonProperty("timestamp") String timestamp) {
		this.content = content;
		this.timestamp = timestamp;
		this.self = self;
	}

	public String getContent() {
		return content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public URI getSelf() {
		return self;
	}

}
