package uz.raximov.codingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProgLangDto {
    @NotNull
    private String name;

    private String description;
}
