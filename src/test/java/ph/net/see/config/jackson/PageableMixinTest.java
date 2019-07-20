package ph.net.see.config.jackson;

import io.micronaut.data.model.Pageable;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PageableMixinTest extends AbstractMixinTest {

    @Test
    void shouldBeAbleToSerialize() throws IOException {
        String json = "{\"number\":0,\"sort\":{\"sorted\":false},\"size\":100,\"offset\":0,\"sorted\":false}";
        Pageable pageable = objectMapper.readValue(json, Pageable.class);
        assertNotNull(pageable);
    }

}