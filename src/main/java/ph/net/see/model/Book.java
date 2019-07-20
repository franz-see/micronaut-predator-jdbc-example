package ph.net.see.model;

import io.micronaut.data.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@MappedEntity
@EqualsAndHashCode
@ToString
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String isbn;

    @Relation(Relation.Kind.MANY_TO_ONE)
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
