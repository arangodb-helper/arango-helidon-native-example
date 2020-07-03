package io.helidon.examples.quickstart.mp.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author Michele Rastelli
 */
@Provider
public class JsonConfig implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> aClass) {
        return JsonbBuilder
                .newBuilder()
                .withConfig(new JsonbConfig().withAdapters(new VersionAdapter()))
                .build();
    }

}
