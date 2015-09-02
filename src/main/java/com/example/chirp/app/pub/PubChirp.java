package com.example.chirp.app.pub;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PubChirp {

  private final String id;
  private final String content;
  private final Map<String,URI> _links = new LinkedHashMap<>();
  
  public PubChirp(@JsonProperty("_links") Map<String,URI> _links,
                  @JsonProperty("id") String id,
                  @JsonProperty("content") String content) {
  
    this._links.putAll(_links);
    this.id = id;
    this.content = content;
  }
 
  public String getId() { return id; }
  public String getContent() { return content; }
  public Map<String, URI> get_links() { return _links; }
}







