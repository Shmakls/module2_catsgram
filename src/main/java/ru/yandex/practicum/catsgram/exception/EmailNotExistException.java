package ru.yandex.practicum.catsgram.exception;

public class EmailNotExistException extends RuntimeException {

    public EmailNotExistException(String message) {
        super(message);
    }
}
