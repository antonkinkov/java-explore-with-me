package ru.practicum.stat_service.service;

import ru.practicum.stat_service.dto.HitDto;
import ru.practicum.stat_service.dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    HitDto create(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
