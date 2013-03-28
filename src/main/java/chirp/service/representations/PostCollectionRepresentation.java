package chirp.service.representations;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.model.User;

import com.sun.jersey.server.linking.Ref;

public class PostCollectionRepresentation {

	@Ref("post/{username}")
	private URI self;

	private final String username;
	private final Collection<PostRepresentation> posts;

	public PostCollectionRepresentation(User user, Collection<Post> posts) {
		this.username = user.getUsername();
		this.posts = new LinkedList<PostRepresentation>();
		for (Post post : posts) {
			this.posts.add(new PostRepresentation(post, true));
		}
	}

	@JsonCreator
	public PostCollectionRepresentation(
			@JsonProperty("self") URI self,
			@JsonProperty("posts") Collection<PostRepresentation> posts) {
		this.self = self;
		this.username = null;
		this.posts = posts;
	}

	public URI getSelf() {
		return self;
	}

	@JsonIgnore
	public String getUsername() {
		return username;
	}

	public Collection<PostRepresentation> getPosts() {
		return posts;
	}

}
