package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.UserDto;
import ru.practicum.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                                  @PositiveOrZero @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                  @Positive @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Admin: Получение информации о пользователях (ids = {}, from = {}, size = {})", ids, from, size);
        return userService.findById(ids, from, size);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto  userDto) {
        log.info("Admin: Добавление нового пользователя: {}", userDto);
        return userService.create(userDto);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@Positive @PathVariable Long userId) {
        log.info("Admin: Удаление пользователя по id: {}", userId);
        userService.delete(userId);
    }
}
