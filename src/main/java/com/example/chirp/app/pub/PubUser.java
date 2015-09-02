package com.example.chirp.app.pub;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PubUser {
  
  private final Map<String,URI> _links = new LinkedHashMap<>();
  private final String username;
  private final String realName;
 
  public PubUser(@JsonProperty("_links") Map<String,URI> _links, 
                 @JsonProperty("username") String username, 
                 @JsonProperty("realName") String realName) {

    this.username = username;
    this.realName = realName;
    this._links.putAll(_links);
  }
  
  public String getUsername() { return username; }
  public String getRealName() { return realName; }
  public Map<String,URI> get_links() { return _links; }
}