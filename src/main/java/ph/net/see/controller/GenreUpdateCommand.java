package ph.net.see.controller;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GenreUpdateCommand {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public GenreUpdateCommand() {
    }

    public GenreUpdateCommand(@NotNull Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

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
}
