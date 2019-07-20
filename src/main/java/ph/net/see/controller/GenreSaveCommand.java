package ph.net.see.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

public class GenreSaveCommand {

    private String name;

    public GenreSaveCommand() {
    }

    public GenreSaveCommand(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
