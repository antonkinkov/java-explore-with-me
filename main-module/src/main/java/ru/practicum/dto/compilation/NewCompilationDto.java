package ru.practicum.dto.compilation;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class NewCompilationDto {

    private Long id;
    private List<Long> events;
    private final Boolean pinned = false;

    @NotBlank
    @Size(max = 50)
    private String title;
}
