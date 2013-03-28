package chirp.service.providers;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.core.ResourceConfigurator;
import com.sun.jersey.server.linking.LinkFilter;

@Provider
public class JerseyConfiguration implements ResourceConfigurator {

	@Override
	@SuppressWarnings("unchecked")
	public void configure(ResourceConfig config) {
		// enable filter to process @Links, @Link and @Ref annotations
		config.getContainerResponseFilters().add(LinkFilter.class);
	}

}