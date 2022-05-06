package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    private final Set<User> users = new HashSet<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public Set<User> findAll() {

        log.info("Количество пользователей - {}", users.size());

        return users;
    }

    @PostMapping(value = "/users")
    public void createUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {

        if (user.getEmail().isEmpty() || user.getEmail() == null) {
            throw new InvalidEmailException("Некорректный e-mail!");
        }

        if (!(users.add(user))) {
            throw new UserAlreadyExistException("Пользователь уже существует!");
        }

        log.info("Добавлен пользователь - {}", user);

    }

    @PutMapping(value = "/users")
    public void putUser(@RequestBody User user) throws InvalidEmailException {

        if (user.getEmail().isEmpty() || user.getEmail() == null) {
            throw new InvalidEmailException("Некорректный e-mail!");
        }

        if (!users.contains(user)) {
            users.add(user);
            log.info("Добавлен пользователь - {}", user);
        } else {
            users.remove(user);
            users.add(user);
            log.info("Обновлен пользователь - {}", user);
        }

    }


}
