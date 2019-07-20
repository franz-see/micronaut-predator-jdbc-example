package ph.net.see.config.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.model.Pageable;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DefaultPageMixin<T> {

    @JsonCreator
    DefaultPageMixin(
            @JsonProperty("content") List<T> content,
            @JsonProperty("pageable") Pageable pageable,
            @JsonProperty("totalSize") long totalSize) {
    }
}
