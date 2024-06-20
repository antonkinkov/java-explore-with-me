package ru.practicum.exception;

public class InvalidException extends RuntimeException {
    public InvalidException(String msg) {
        super(msg);
    }
}
