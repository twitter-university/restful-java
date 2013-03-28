package chirp.service.representations;

import chirp.model.User;

public class UserRepresentation {

	private final String username;
	private final String realname;

	public UserRepresentation(User user) {
		username = user.getUsername();
		realname = user.getRealname();
	}

	public String getUsername() {
		return username;
	}

	public String getRealname() {
		return realname;
	}

}
