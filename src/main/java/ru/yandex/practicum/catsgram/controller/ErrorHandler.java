package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.*;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse IncorrectParameterExceptionHandler(final IncorrectParameterException e) {

        return new ErrorResponse("Ошибка с полем " + e.getParameter());

    }

    @ExceptionHandler({PostNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse PostAndUserNotFoundExceptionHandler(final RuntimeException e) {

        return new ErrorResponse(e.getMessage());

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse UserAlreadyExistExceptionHandler(final UserAlreadyExistException e) {

        return new ErrorResponse(e.getMessage());

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse InvalidEmailExceptionHandler(final InvalidEmailException e) {

        return new ErrorResponse(e.getMessage());

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse UnforeseenExceptionHandler(final Throwable e) {

        return new ErrorResponse("Произошла непредвиденная ошибка.");

    }

}
