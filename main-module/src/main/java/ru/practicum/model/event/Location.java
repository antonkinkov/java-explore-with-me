package ru.practicum.model.event;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    private Float lat;
    private Float lon;
}
