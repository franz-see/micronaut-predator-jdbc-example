package ph.net.see.repository;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;
import ph.net.see.model.Book;
import ph.net.see.model.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptySet;

@JdbcRepository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    @NonNull
    Optional<Genre> findById(@Id @NotNull @NonNull Long id);

    default Genre save(@NotBlank String name) {
        return save(name, emptySet());
    }

    Genre save(@NotBlank String name, @NotNull Set<Book> books);

    void deleteById(@Id @NonNull @NotNull Long id);

    Page<Genre> findAll(@NotNull Pageable pageable);

    void update(@Id Long id, @NotBlank String name);

}
