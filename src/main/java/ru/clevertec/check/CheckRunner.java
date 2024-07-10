package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.dto.request.PurchaseDto;
import main.java.ru.clevertec.check.dto.response.CheckDto;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.EntityNotFoundException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.factory.CheckFactory;
import main.java.ru.clevertec.check.util.ArgsParser;
import main.java.ru.clevertec.check.util.printer.factory.ConsoleCheckPrinter;
import main.java.ru.clevertec.check.util.printer.factory.CsvCheckPrinter;
import main.java.ru.clevertec.check.util.printer.factory.CsvErrorPrinter;

public class CheckRunner {

    public static void main(String[] args) {
        CheckFactory checkFactory = new CheckFactory();
        CsvCheckPrinter csvCheckPrinter = new CsvCheckPrinter();
        ConsoleCheckPrinter consoleCheckPrinter = new ConsoleCheckPrinter();
        CsvErrorPrinter csvErrorPrinter = new CsvErrorPrinter();

        try {
            PurchaseDto request = ArgsParser.consoleArgsParse(args);
            CheckDto response = checkFactory.createCheck(request);
            csvCheckPrinter.print(response);
            consoleCheckPrinter.print(response);

        } catch (NotEnoughMoneyException | BadRequestException | InternalServerException e) {
            csvErrorPrinter.print(e.getMessage());
            System.out.printf("ERROR: %s", e.getMessage());

        } catch (EntityNotFoundException e) {
            csvErrorPrinter.print(BadRequestException.MESSAGE_DEFAULT);
            System.out.printf("ERROR: %s", BadRequestException.MESSAGE_DEFAULT);
        }
    }
}