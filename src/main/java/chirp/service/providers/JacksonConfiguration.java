package chirp.service.providers;

import javax.ws.rs.ext.Provider;

@Provider
public class JacksonConfiguration /* implements ContextResolver<ObjectMapper> */ {

	/*
	@Override
	public ObjectMapper getContext(Class<?> type) {
		// only serialize non-null fields in JSON representations
		return new ObjectMapper().setSerializationInclusion(NON_NULL);
	}
	*/

}
