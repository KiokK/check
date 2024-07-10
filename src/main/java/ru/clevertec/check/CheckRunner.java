package ru.clevertec.check;

import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.dto.response.CheckDto;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.util.ArgsParser;
import ru.clevertec.check.util.Constants;
import ru.clevertec.check.util.printer.factory.ConsoleCheckPrinter;
import ru.clevertec.check.util.printer.factory.CsvCheckPrinter;
import ru.clevertec.check.util.printer.factory.CsvErrorPrinter;

public class CheckRunner {

    public static void main(String[] args) {
        CheckFactory checkFactory = new CheckFactory();
        CsvCheckPrinter csvCheckPrinter = new CsvCheckPrinter();
        ConsoleCheckPrinter consoleCheckPrinter = new ConsoleCheckPrinter();
        CsvErrorPrinter csvErrorPrinter = new CsvErrorPrinter();

        try {
            PurchaseDto request = ArgsParser.consoleArgsParse(args);
            CheckDto response = checkFactory.createCheck(request);
            csvCheckPrinter.print(response, Constants.SAVE_TO_FILE);
            consoleCheckPrinter.print(response);

        } catch (NotEnoughMoneyException | BadRequestException | InternalServerException e) {
            if (Constants.SAVE_TO_FILE != null) {
                csvErrorPrinter.print(e.getMessage(), Constants.SAVE_TO_FILE);
            } else {
                csvErrorPrinter.print(e.getMessage());
            }
            System.out.printf("ERROR: %s", e.getMessage());

        } catch (EntityNotFoundException e) {
            if (Constants.SAVE_TO_FILE != null) {
                csvErrorPrinter.print(BadRequestException.MESSAGE_DEFAULT, Constants.SAVE_TO_FILE);
            } else {
                csvErrorPrinter.print(BadRequestException.MESSAGE_DEFAULT);
            }
            System.out.printf("ERROR: %s", BadRequestException.MESSAGE_DEFAULT);
        }
    }
}