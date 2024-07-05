package ru.practicum.controller.admin;

import org.springframework.dao.DataIntegrityViolationException;
import ru.practicum.dto.category.CategoryDto;
import ru.practicum.dto.category.NewCategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exception.ViolationException;
import ru.practicum.service.category.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto addCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Создание новой категории с name = {})", newCategoryDto.getName());
        return categoryService.create(newCategoryDto);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PositiveOrZero @PathVariable Long catId) {
        log.info("Удаление категории с id = {}", catId);
        try {
            categoryService.delete(catId);
        } catch (DataIntegrityViolationException e) {
        throw new ViolationException(e.getMessage());
        }
    }

    @PatchMapping("/{catId}")
    public CategoryDto patchCategory(@PathVariable Long catId,
                                     @Valid @RequestBody CategoryDto categoryDto) {
        log.info("Обновление категории с name = {}", categoryDto);
        return categoryService.update(catId, categoryDto);
    }
}
