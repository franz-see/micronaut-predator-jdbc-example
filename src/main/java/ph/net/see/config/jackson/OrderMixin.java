package ph.net.see.config.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.model.Sort;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderMixin {

    @SuppressWarnings("unused")
    @JsonCreator
    public OrderMixin(
            @JsonProperty("property") String property,
            @JsonProperty("direction") Sort.Order.Direction direction,
            @JsonProperty("ignoreCase") boolean ignoreCase) {
    }

}
