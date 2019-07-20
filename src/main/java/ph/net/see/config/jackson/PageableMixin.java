package ph.net.see.config.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PageableMixin {

    @SuppressWarnings("unused")
    @JsonCreator
    static Pageable from(
            @JsonProperty("index") int number,
            @JsonProperty("size") int size,
            @JsonProperty("sort") @Nullable Sort sort) {
        return null;
    }

    @JsonIgnore
    public List<Sort.Order> getOrderBy() {
        return null;
    }
}
