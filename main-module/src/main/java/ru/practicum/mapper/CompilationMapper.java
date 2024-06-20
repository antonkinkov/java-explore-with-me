package ru.practicum.mapper;

import ru.practicum.dto.compilation.CompilationDto;
import lombok.experimental.UtilityClass;
import ru.practicum.model.compilation.Compilation;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CompilationMapper {

    public static CompilationDto toDto(Compilation compilation) {
        return CompilationDto.builder()
                .id(compilation.getId())
                .pinned(compilation.getPinned())
//                .events(EventMapper.toDtoList(compilation.getEvents()))
                .title(compilation.getTitle())
                .build();
    }

    public static List<CompilationDto> toDtoList(List<Compilation> compilationList) {
        return compilationList.stream()
                .map(CompilationMapper::toDto)
                .collect(Collectors.toList());
    }
}
