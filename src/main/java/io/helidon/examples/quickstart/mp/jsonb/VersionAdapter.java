package io.helidon.examples.quickstart.mp.jsonb;


import com.arangodb.entity.ArangoDBVersion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

/**
 * @author Michele Rastelli
 */
public class VersionAdapter implements JsonbAdapter<ArangoDBVersion, JsonObject> {

    @Override
    public JsonObject adaptToJson(ArangoDBVersion obj) throws Exception {
        return Json.createObjectBuilder()
                .add("version", obj.getVersion())
                .add("server", obj.getServer())
                .add("license", obj.getLicense().toString())
                .build();
    }

    @Override
    public ArangoDBVersion adaptFromJson(JsonObject obj) throws Exception {
        throw new UnsupportedOperationException("not implemented");
    }

}
