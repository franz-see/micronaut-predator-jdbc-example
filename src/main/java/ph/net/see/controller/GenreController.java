package ph.net.see.controller;

import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.spring.tx.annotation.Transactional;
import io.micronaut.validation.Validated;
import ph.net.see.model.Genre;
import ph.net.see.repository.GenreRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@Controller("/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Get("/{id}")
    @Transactional
    public Genre show(Long id) {
        return genreRepository
                .findById(id)
                .orElse(null);
    }

    @Put()
    public HttpResponse<Void> update(@Body @Valid GenreUpdateCommand command) {
        genreRepository.update(command.getId(), command.getName());

        return HttpResponse
                .<Void>noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Get(value = "/list{?pageable*}")
    public List<Genre> list(@Valid Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Post()
    public HttpResponse<Genre> save(@Body @Valid GenreSaveCommand cmd) {
        Genre genre = genreRepository.save(cmd.getName());

        return HttpResponse
                .created(genre)
                .headers(headers -> headers.location(location(genre.getId())));
    }

    @Delete("/{id}")
    public HttpResponse<Void> delete(Long id) {
        genreRepository.deleteById(id);
        return HttpResponse.<Void>noContent();
    }

    private URI location(Long id) {
        return URI.create("/genres/" + id);
    }

}
