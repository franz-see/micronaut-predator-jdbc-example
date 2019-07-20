package ph.net.see.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import lombok.*;
import lombok.experimental.Tolerate;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class DtoPageable implements Pageable {

    private int number;

    @Builder.Default
    private int size = 100;

    @Builder.Default
    @JsonDeserialize(as = DtoSort.class)
    private Sort sort = new DtoSort();


    @Tolerate
    public DtoPageable() {
        sort = new DtoSort();
        size = 100;
    }
}
