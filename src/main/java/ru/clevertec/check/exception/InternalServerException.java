package main.java.ru.clevertec.check.exception;

public class InternalServerException extends RuntimeException{

    public static String MESSAGE_DEFAULT = "INTERNAL SERVER ERROR";

    public InternalServerException() {
        super(MESSAGE_DEFAULT);
    }
}
