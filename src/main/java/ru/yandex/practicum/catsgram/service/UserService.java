package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserService {

    private final Set<User> users = new HashSet<>();

    public Set<User> findAll() {
        log.info("Количество пользователей - {}", users.size());
        return users;
    }

    public User create(User user) throws InvalidEmailException, UserAlreadyExistException {

        if (!StringUtils.hasText(user.getEmail())) {
            throw new InvalidEmailException("Некорректный e-mail!");
        }

        if (!(users.add(user))) {
            throw new UserAlreadyExistException("Пользователь уже существует!");
        }

        log.info("Добавлен пользователь - {}", user.getNickname());

        return user;

    }

    public void put(User user) throws InvalidEmailException {

        if (!StringUtils.hasText(user.getEmail())) {
            throw new InvalidEmailException("Некорректный e-mail!");
        }

        if (!users.contains(user)) {
            users.add(user);
            log.info("Добавлен пользователь - {}", user.getNickname());
        } else {
            users.remove(user);
            users.add(user);
            log.info("Обновлен пользователь - {}", user.getNickname());
        }

    }

    public User findUserByEmail(String email) {

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;

    }

}
