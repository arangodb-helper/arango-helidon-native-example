package com.example.myproject;

import io.helidon.microprofile.testing.junit5.AddConfig;
import jakarta.ws.rs.core.Response;
import jakarta.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;

import io.helidon.microprofile.testing.junit5.HelidonTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

abstract class MainTest {

    @Inject
    private WebTarget target;

    @Test
    void testVersionEndpoint() {
        Response response = target.path("/version")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);

        String server = response
                .readEntity(JsonObject.class)
                .getString("server");

        assertThat(server).isEqualTo("arango");
    }
}

@HelidonTest
@AddConfig(key = "adb.protocol", value = "HTTP_JSON")
class HttpJsonGreetingResourceTest extends MainTest {
}

@HelidonTest
@AddConfig(key = "adb.protocol", value = "HTTP2_JSON")
class Http2JsonGreetingResourceTest extends MainTest {
}
