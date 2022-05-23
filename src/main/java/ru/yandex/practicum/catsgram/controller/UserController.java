package ru.yandex.practicum.catsgram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{email}")
    public User findUserByEmail(@PathVariable String email) {

        return userService.findUserByEmail(email);

    }


    @GetMapping("/users")
    public Set<User> findAll() {
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) throws InvalidEmailException, UserAlreadyExistException {

        userService.create(user);

        return user;
    }

    @PutMapping(value = "/user")
    public User putUser(@RequestBody User user) throws InvalidEmailException {

        userService.put(user);

        return user;

    }


}
