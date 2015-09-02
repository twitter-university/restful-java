package com.example.chirp.app.pub;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PubUser {
  
  private final Map<String,URI> _links = new LinkedHashMap<>();
  private final String username;
  private final String realName;
 
  @JsonInclude(Include.NON_NULL)
  private final PubChirps chirps;
  
  public PubUser(@JsonProperty("_links") Map<String,URI> _links, 
                 @JsonProperty("username") String username, 
                 @JsonProperty("realName") String realName,
                 @JsonProperty("chirps") PubChirps chirps) {

    this.username = username;
    this.realName = realName;
    this._links.putAll(_links);
    this.chirps = chirps;
  }

  public PubChirps getChirps() {
    return chirps;
  }
  
  public String getUsername() { return username; }
  public String getRealName() { return realName; }
  public Map<String,URI> get_links() { return _links; }
}