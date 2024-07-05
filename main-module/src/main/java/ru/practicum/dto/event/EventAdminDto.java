package ru.practicum.dto.event;

import lombok.*;
import ru.practicum.model.event.EventState;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class EventAdminDto {

    private List<Long> users;
    private List<EventState> states;
    private List<Long> categories;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String rangeStart;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String rangeEnd;

    @PositiveOrZero
    @Builder.Default
    private int from = 0;

    @Positive
    @Builder.Default
    private int size = 10;

}