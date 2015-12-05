package com.example.chirp.app.pub;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionInfo {

  private final int status;
  private final String message;

  @JsonCreator
  public ExceptionInfo(@JsonProperty("status") int status, 
                       @JsonProperty("message") String message) {
    this.status = status;
    this.message = message;
  }
  
  public ExceptionInfo(int status, Exception ex) {
    this.status = status;
    this.message = (ex.getMessage() == null) ?
        ex.getClass().getName() : 
        ex.getMessage();
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }


}
