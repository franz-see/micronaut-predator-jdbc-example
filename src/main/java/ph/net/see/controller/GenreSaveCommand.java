package ph.net.see.controller;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class GenreSaveCommand {

    @NotBlank
    private String name;

}
