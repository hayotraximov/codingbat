package uz.raximov.codingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Integer starBadge;
}
