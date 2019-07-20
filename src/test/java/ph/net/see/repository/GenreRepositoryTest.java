package ph.net.see.repository;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import ph.net.see.model.Genre;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(transactional = false)
@Property(name = "datasources.default.schema-generate", value = "CREATE_DROP")
class GenreRepositoryTest {

    @Inject
    private GenreRepository genreRepository;

    @Test
    void save() {
        Genre savedGenre = genreRepository.save("dummy");

        assertTrue(genreRepository.existsById(savedGenre.getId()), "Genre should exist");
    }
}