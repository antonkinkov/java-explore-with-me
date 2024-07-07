package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;
import ru.practicum.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private final StatsService statsService;

    @PostMapping("/hit")
    public HitDto create(@Valid @RequestBody HitDto hitDto) {
        return statsService.create(hitDto);
    }

    //    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @GetMapping("/stats")
    public List<StatsDto> getStats(@RequestParam LocalDateTime start,
                                   @RequestParam LocalDateTime end,
                                   @RequestParam(required = false) List<String> uris,
                                   @RequestParam(defaultValue = "false") Boolean unique) {

        return statsService.getStats(start, end, uris, unique);
    }

//    private LocalDateTime parseToLocalDateTime(String date) {
//        try {
//            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        } catch (Exception e) {
//            throw new RuntimeException("Неверный формат даты: " + date);
//        }
//    }
}
