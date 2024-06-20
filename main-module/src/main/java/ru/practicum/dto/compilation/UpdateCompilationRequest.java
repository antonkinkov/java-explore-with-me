package ru.practicum.dto.compilation;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class UpdateCompilationRequest {

    private Long id;
    private List<Long> events;
    private Boolean pinned;
    @Size(max = 200)
    private String title;
}
