package chirp.service;

import static com.google.inject.Guice.createInjector;
import static com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory.createHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import chirp.model.UserRepository;


/**
 * Lightweight, embedded HTTP server. Knows how to load and save the user
 * repository, and provide it for injection into resource classes.
 */
public class Server {
	
    public static final String BASE_URI = "http://localhost:8080/";


	private static final File userRepositoryFile = new File(
			"user_repository.bin");

	public static void main(String[] args) throws IOException {
		
        final ResourceConfig rc = new ResourceConfig().packages("chirp");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

		// instantiate singleton user repository for injection
		final UserRepository userRepository = thaw();

		Injector injector = createInjector(new AbstractModule() {
			protected void configure() {
				bind(UserRepository.class).toInstance(userRepository);
			}
		});

		// wait for shutdown ...
		System.out.println("Hit <return> to stop server...");
		System.in.read();
		httpServer.shutdownNow();

		// save state
		freeze(userRepository);
	}

	private static UserRepository thaw() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					userRepositoryFile));
			try {
				return (UserRepository) in.readObject();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			return new UserRepository();
		}
	}

	private static void freeze(UserRepository userRepository) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(userRepositoryFile));
			try {
				out.writeObject(userRepository);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
