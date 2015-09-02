package com.example.chirp.app.providers;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.chirp.app.kernel.exceptions.NoSuchEntityException;
import com.example.chirp.app.pub.ExceptionInfo;

@Provider
public class NoSuchEntityExceptionMapper implements ExceptionMapper<NoSuchEntityException> {
  
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Context UriInfo uriInfo;
	
	public NoSuchEntityExceptionMapper(Class type, String id) {
	  super("The entity " + type.getSimpleName() + " (" +id +") does not exsit");
	}
	
  @Override
  public Response toResponse(NoSuchEntityException exception) {
    ExceptionInfo info = new ExceptionInfo(404, exception);
 

    log.info(info.getMessage(), exception);
    return Response.status(404).entity(info).build();
  }
}








