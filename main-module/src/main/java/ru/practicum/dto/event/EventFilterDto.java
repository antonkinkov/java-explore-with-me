package ru.practicum.dto.event;

import lombok.*;
import ru.practicum.model.event.SortType;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class EventFilterDto {

    private HttpServletRequest httpServletRequest;
    private String text;
    private List<Long> categories;
    private Boolean paid;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String rangeStart;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String rangeEnd;
    private boolean onlyAvailable;
    private SortType sort;
    @PositiveOrZero
    private final int from  = 0;
    @PositiveOrZero
    private final int size = 10;
}
