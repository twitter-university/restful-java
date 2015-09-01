package com.example.chirp.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.example.chirp.app.resources.GreetingsResource;
import com.example.chirp.app.resources.RootResource;

//@ApplicationPath("/")
public class ChirpApplication extends Application {
	private Set<Class<?>> classes = new HashSet<>();
	
	public ChirpApplication() {
		 classes.add(RootResource.class);
		 classes.add(GreetingsResource.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
	 return classes;
	}
}