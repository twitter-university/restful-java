package com.example.chirp.app.resources;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public class CreateUserRequest {

	public @PathParam("username") String username;
	public @FormParam("realName") String realName;
	public @Context UriInfo uriInfo;

	public void validate() {
		if (this.username.contains(" ")) {
			throw new BadRequestException("The user name cannot contain spaces.");
		}
  }
}
