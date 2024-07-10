package ru.clevertec.check.exception;

public class BadRequestException extends RuntimeException{

    public static String MESSAGE_DEFAULT = "BAD REQUEST";

    public BadRequestException() {
        super(MESSAGE_DEFAULT);
    }
}
