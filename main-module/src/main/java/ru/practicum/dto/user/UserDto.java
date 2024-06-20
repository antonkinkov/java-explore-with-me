package ru.practicum.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Имя не может быть пустым.")
    @Size(min = 2, max = 30, message = "Имя пользователя должено быть от 2 до 30 символов.")
    private String name;

    @Email(message = "email должен быть адресом эл. почты.")
    @NotEmpty(message = "email не должен быть пустым")
    private String email;
}
