package ru.practicum.stat_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat_service.dto.HitDto;
import ru.practicum.stat_service.dto.StatsDto;
import ru.practicum.stat_service.exception.InvalidTimeParamException;
import ru.practicum.stat_service.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.stat_service.mapper.StatsMapper.toEndpointHit;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;

    @Override
    public HitDto create(HitDto hitDto) {
        repository.save(toEndpointHit(hitDto));
        return hitDto;
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (end.isBefore(start)) {
            throw new InvalidTimeParamException("Время конца раньше начала");
        }
//        if(uris.isEmpty()){
//            return new ArrayList<>();
//        }

        return unique.equals(true) ?
                getStatsWithUnique(start, end, uris) : getAllStats(start, end, uris);
    }

    private List<StatsDto> getAllStats(LocalDateTime start, LocalDateTime end, List<String> uris) {
        return uris.isEmpty() ? repository.getStats(start, end): repository.getStatsWithUrls(start, end, uris);
    }

    private List<StatsDto> getStatsWithUnique(LocalDateTime start, LocalDateTime end, List<String> uris) {
        return uris.isEmpty() ? repository.getStatsWithUniqueIp(start, end) : repository.getStatsWithUniqueIpAndUrls(start, end, uris);

    }
}
