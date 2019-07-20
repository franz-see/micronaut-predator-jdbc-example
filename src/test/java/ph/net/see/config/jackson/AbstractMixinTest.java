package ph.net.see.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import ph.net.see.config.JacksonConfig;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractMixinTest {

    protected ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws ClassNotFoundException {
        objectMapper = new ObjectMapper();
        new JacksonConfig().configureObjectMapper(objectMapper);
    }
}
