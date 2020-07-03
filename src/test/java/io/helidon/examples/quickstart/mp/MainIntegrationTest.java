
package io.helidon.examples.quickstart.mp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

class MainIntegrationTest {

    @Test
    void testVersionEndpoint() {
        Client client = ClientBuilder.newClient();

        JsonObject jsonVersion = client
                .target(getConnectionString("/version"))
                .request()
                .get(JsonObject.class);
        Assertions.assertEquals("arango", jsonVersion.getString("server"));
    }

    private String getConnectionString(String path) {
        return "http://localhost:8081" + path;
    }
}
