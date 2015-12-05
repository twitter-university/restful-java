package com.example.chirp.app.providers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.chirp.app.pub.ExceptionInfo;

@Provider
public class WebApplicationExceptionMapper
             implements ExceptionMapper<WebApplicationException> {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Override public Response toResponse(WebApplicationException exception) {
    int status = exception.getResponse().getStatus();
    
    ExceptionInfo info = new ExceptionInfo(status, exception);
    
    if (status < 500) log.info(info.getMessage(), exception);
    else log.error(info.getMessage(), exception);

    return Response.status(status).entity(info).build();
  }
}