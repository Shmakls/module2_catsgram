package ru.yandex.practicum.catsgram.exceptions;

public class IdNotExistException extends RuntimeException {

    public IdNotExistException(String message) {
        super(message);
    }
}
