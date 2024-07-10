package main.java.ru.clevertec.check.exception;

public class NotEnoughMoneyException extends RuntimeException{

    public static String MESSAGE_DEFAULT = "NOT ENOUGH MONEY";

    public NotEnoughMoneyException() {
        super(MESSAGE_DEFAULT);
    }
}
