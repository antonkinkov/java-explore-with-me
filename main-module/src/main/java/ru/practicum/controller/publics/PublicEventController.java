package ru.practicum.controller.publics;

import ru.practicum.dto.event.EventFilterDto;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatsClient;
import ru.practicum.dto.HitDto;
import ru.practicum.service.event.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PublicEventController {

    private final EventService eventService;
    private final StatsClient statsClient;

    @GetMapping
    public List<EventShortDto> findSearchByFilter(@ModelAttribute EventFilterDto eventFilterDto) {
        return eventService.getEventsByFilter(eventFilterDto);
    }

    @GetMapping("/{id}")
    public EventFullDto findById(HttpServletRequest httpServletRequest,
                                 @PathVariable("id") @Positive Long eventId) {

        log.info("Public: Получить событие по id = {}", eventId);
        statsClient.addStatistic(
                new HitDto("main-module",
                        "/events/" + eventId,
                        httpServletRequest.getRemoteAddr(),
                        LocalDateTime.now()
                ));

        return eventService.getEventById(eventId, httpServletRequest);
    }
}
