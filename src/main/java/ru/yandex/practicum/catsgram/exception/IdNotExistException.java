package ru.yandex.practicum.catsgram.exception;

public class IdNotExistException extends RuntimeException {

    public IdNotExistException(String message) {
        super(message);
    }
}
