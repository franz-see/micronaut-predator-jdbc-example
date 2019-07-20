package ph.net.see.repository;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import ph.net.see.model.Genre;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(transactional = false)
@Property(name = "datasources.default.schema-generate", value = "CREATE_DROP")
class GenreRepositoryTest {

    @Inject
    private GenreRepository genreRepository;

    @Test
    void save() {
        String genreName = "dummy";
        Genre savedGenre = genreRepository.save(genreName);

        Optional<Genre> foundGenre = genreRepository.findById(savedGenre.getId());
        assertTrue(foundGenre.isPresent(), "Genre should exist");
        assertEquals(genreName, foundGenre.get().getName(), "Genre should have correct name");
    }
}