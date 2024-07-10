package ru.clevertec.check.util.printer;

import static ru.clevertec.check.util.Constants.ROOT_PATH;

public interface Printer<T> {

    String RESULT_CHECK_PATH = ROOT_PATH + "\\result.csv";

    void print(T data, String filePath);

    default void print(T data) {
        print(data, RESULT_CHECK_PATH);
    }
}
