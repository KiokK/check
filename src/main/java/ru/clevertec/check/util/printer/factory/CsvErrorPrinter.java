package ru.clevertec.check.util.printer.factory;

import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.util.printer.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvErrorPrinter implements Printer<String> {

    private final String DEFAULT_COLUMN = "ERROR";

    @Override
    public void print(String errorMessage, String filePath) {
        File csvOutputFile = new File(filePath);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(DEFAULT_COLUMN);
            pw.print(errorMessage);
        } catch (FileNotFoundException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public String printString(String errorMessage) {
        return DEFAULT_COLUMN + "\n" + errorMessage;
    }
}
