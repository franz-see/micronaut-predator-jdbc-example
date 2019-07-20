package ph.net.see.config.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.model.Sort;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SortMixin {

    @SuppressWarnings("unused")
    @JsonCreator
    static Sort of(@JsonProperty("orderBy") List<Sort.Order> ignore) {
        return null;
    }
}
