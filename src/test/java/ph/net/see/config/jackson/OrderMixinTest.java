package ph.net.see.config.jackson;

import io.micronaut.data.model.Sort;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderMixinTest extends AbstractMixinTest {

    @Test
    void shouldBeAbleToDeserialize() throws IOException {
        String json = "{\"property\":\"dummy\",\"direction\":\"DESC\",\"ignoreCase\":true,\"ascending\":false}";
        Sort.Order order = objectMapper.readValue(json, Sort.Order.class);
        assertNotNull(order);
    }

}