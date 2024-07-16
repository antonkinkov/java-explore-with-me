package ru.practicum.dto.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class NewCommentDto {

    @NotNull
    private Long autorId;
    @NotNull
    private Long eventId;

    @NotBlank
    @Size(max = 1000)
    private String text;

}
