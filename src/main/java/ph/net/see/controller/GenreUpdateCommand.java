package ph.net.see.controller;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class GenreUpdateCommand {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}
