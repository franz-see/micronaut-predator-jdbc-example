package ph.net.see.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.http.uri.UriTemplate;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import ph.net.see.model.Genre;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
@Property(name = "datasources.default.schema-generate", value = "CREATE_DROP")
class GenreControllerTest {

    @Inject
    @Client("/")
    private RxHttpClient rxHttpClient;

    BlockingHttpClient getClient() {
        return rxHttpClient.toBlocking();
    }

    @Test
    void supplyAnInvalidOrderTriggersValidationFailure() {
        assertThrows(HttpClientResponseException.class, () ->
                getClient().retrieve(HttpRequest.GET("/genres/list?sort=foo"), Argument.of(List.class, Genre.class)));
    }

    @Test
    void testFindNonExistingGenreReturns404() {
        assertThrows(HttpClientResponseException.class, () ->
                getClient().retrieve(HttpRequest.GET("/genres/99"), Argument.of(Genre.class)));
    }

    private HttpResponse<Genre> saveGenre(String genre) {
        HttpRequest<GenreSaveCommand> request = HttpRequest.POST("/genres", new GenreSaveCommand(genre));
        return getClient().exchange(request);
    }

    @Test
    void testGenreCrudOperations() {
        List<Long> genreIds = new ArrayList<>();
        HttpResponse response = saveGenre("DevOps");
        genreIds.add(entityId(response));
        assertEquals(HttpStatus.CREATED, response.getStatus());

        response = saveGenre("Microservices");
        assertEquals(HttpStatus.CREATED, response.getStatus());

        Long id = entityId(response);
        genreIds.add(id);
        Genre genre = show(id);
        assertEquals("Microservices", genre.getName());

        response = update(id, "Micro-services");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

        genre = show(id);
        assertEquals("Micro-services", genre.getName());

        List<Genre> genres = listGenres(null);
        assertEquals(2, genres.size());

        genres = listGenres(Pageable.from(0, 1));
        assertEquals(1, genres.size());
        assertEquals("DevOps", genres.get(0).getName());

        genres = listGenres(Pageable.from(0, 1,
                Sort.of(singletonList(new Sort.Order("name", Sort.Order.Direction.DESC, false)))));
        assertEquals(1, genres.size());
        assertEquals("Micro-services", genres.get(0).getName());

        genres = listGenres(Pageable.from(10, 1));
        assertEquals(0, genres.size());

        // cleanup:
        for (Long genreId : genreIds) {
            response = delete(genreId);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        }
    }

    private List<Genre> listGenres(Pageable pageable) {
        String uri = "/genres/list";
        if (pageable != null) {
            UriBuilder uriBuilder = UriBuilder.of(uri)
                    .queryParam("size", pageable.getSize())
                    .queryParam("page", pageable.getNumber());

            pageable.getSort().getOrderBy().forEach(order ->
                    uriBuilder.queryParam("sort", format("%s,%s", order.getProperty(), order.getDirection())));

            uri = uriBuilder.build().toString();
        }
        HttpRequest request = HttpRequest.GET(uri);
        @SuppressWarnings("unchecked") List<Genre> result = getClient()
                .retrieve(request, Argument.of(List.class, Genre.class));
        return result;
    }

    private Genre show(Long id) {
        String uri = UriTemplate.of("/genres/{id}").expand(Collections.singletonMap("id", id));
        HttpRequest request = HttpRequest.GET(uri);
        @SuppressWarnings("unchecked") Genre result = getClient().retrieve(request, Genre.class);
        return result;
    }

    private HttpResponse update(Long id, String name) {
        HttpRequest request = HttpRequest.PUT("/genres", new GenreUpdateCommand(id, name));
        @SuppressWarnings("unchecked") HttpResponse result = getClient().exchange(request);
        return result;
    }

    private HttpResponse delete(Long id) {
        HttpRequest request = HttpRequest.DELETE("/genres/" + id);
        @SuppressWarnings("unchecked") HttpResponse result = getClient().exchange(request);
        return result;
    }

    Long entityId(HttpResponse response) {
        String path = "/genres/";
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }
}