package ru.practicum.stat_service.exception;

public class InvalidTimeParamException extends RuntimeException{
    public InvalidTimeParamException(String msg){
        super(msg);
    }
}
