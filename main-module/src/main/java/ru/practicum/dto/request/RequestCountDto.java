package ru.practicum.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RequestCountDto {

    private Long eventId;

    private Long requestCount;

}
