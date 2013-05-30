package chirp.service.representations;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;

public class PostRepresentation {

	private final String content;
	private final String timestamp;
	
	public PostRepresentation(Post post) {
		this.content = post.getContent();
		this.timestamp = post.getTimestamp().toString();
	}

	@JsonCreator
	public PostRepresentation(
			@JsonProperty("content") String content,
			@JsonProperty("timestamp") String timestamp) {
		this.content = content;
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public String getTimestamp() {
		return timestamp;
	}

}
