package ru.yandex.practicum.catsgram.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;


import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    private final Set<User> users = new HashSet<>();

    @GetMapping("/users")
    public Set<User> findAll() {
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
    }

    @PutMapping(value = "/users")
    public void putUser(@RequestBody User user) throws InvalidEmailException {

        if (user.getEmail().isEmpty() || user.getEmail() == null) {
            throw new InvalidEmailException("Некорректный e-mail!");
        }

        if (!users.contains(user)) {
            users.add(user);
        } else {
            users.remove(user);
            users.add(user);
        }

    }


}
