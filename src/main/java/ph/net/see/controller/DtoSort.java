package ph.net.see.controller;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.util.ArgumentUtils;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Sort;
import lombok.*;
import lombok.experimental.Tolerate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class DtoSort implements Sort {

    @Builder.Default
    private List<Sort.Order> orderBy = new LinkedList<>();

    @Tolerate
    public DtoSort() {
        orderBy = new LinkedList<>();
    }

    @Override
    public boolean isSorted() {
        return CollectionUtils.isNotEmpty(orderBy);
    }

    @NonNull
    @Override
    public Sort order(@NonNull String propertyName) {
        return order(new Order(propertyName));
    }

    @NonNull
    @Override
    public Sort order(@NonNull Order order) {
        ArgumentUtils.requireNonNull("order", order);
        List<Order> newOrderBy = new ArrayList<>(orderBy);
        newOrderBy.add(order);
        return new DtoSort(newOrderBy);
    }

    @NonNull
    @Override
    public Sort order(@NonNull String propertyName, @NonNull Order.Direction direction) {
        return order(new Order(propertyName, direction, false));
    }

}
