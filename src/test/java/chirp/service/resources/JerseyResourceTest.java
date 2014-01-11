package chirp.service.resources;

import java.lang.reflect.ParameterizedType;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

public abstract class JerseyResourceTest<R> extends JerseyTest {
	

	@Override
	protected Application configure() {
		// enable server logging of HTTP traffic and dumping of HTTP Entities
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		final Class<R> resourceClass = (Class<R>) ((ParameterizedType) getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
	
		// can be a resource config or an application object
		return new ResourceConfig(resourceClass);
	}

}
