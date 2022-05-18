package ru.yandex.practicum.catsgram.exceptions;

public class EmailNotExistException extends RuntimeException {

    public EmailNotExistException(String message) {
        super(message);
    }
}
