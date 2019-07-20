package ph.net.see.config.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.data.model.Sort;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SortMixinTest extends AbstractMixinTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortMixinTest.class);

    @Test
    void shouldSerialize() throws JsonProcessingException {
        Sort sort = Sort.of(Collections.singletonList(new Sort.Order("dummy", Sort.Order.Direction.DESC, true)));
        String json = objectMapper.writeValueAsString(sort);
        LOGGER.info("Serialized JSON = {}", json);
    }

    @Test
    void shouldBeAbleToDeserialize() throws IOException {
        String json = "{\"orderBy\":[{\"property\":\"dummy\",\"direction\":\"DESC\",\"ignoreCase\":true,\"ascending\":false}],\"sorted\":true}";
        Sort sort = objectMapper.readValue(json, Sort.class);
        assertNotNull(sort);
    }
}