package ru.practicum.stat_service.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.stat_service.dto.HitDto;
import ru.practicum.stat_service.model.EndpointHit;

@UtilityClass
public class StatsMapper {
    public static EndpointHit toEndpointHit(HitDto hitDto) {
        return EndpointHit.builder()
                .id(hitDto.getId())
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .timestamp(hitDto.getTimestamp())
                .build();
    }
}
