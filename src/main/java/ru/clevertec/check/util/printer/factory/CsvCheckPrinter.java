package main.java.ru.clevertec.check.util.printer.factory;

import main.java.ru.clevertec.check.dto.response.CheckDto;
import main.java.ru.clevertec.check.dto.response.PurchasedItem;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.util.printer.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static main.java.ru.clevertec.check.util.FormatUtil.currencyFormat;
import static main.java.ru.clevertec.check.util.FormatUtil.dateFormat;
import static main.java.ru.clevertec.check.util.FormatUtil.timeFormat;

public class CsvCheckPrinter implements Printer<CheckDto> {

    @Override
    public void print(CheckDto check, String filePath) {
        File csvOutputFile = new File(filePath);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("Date;Time");
            pw.println(dateFormat(check.date) + ";" + timeFormat(check.time));
            pw.println();

            pw.println("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL");
            check.items.stream()
                    .map(this::convertToCsvString)
                    .forEach(pw::println);
            pw.println();

            if (check.discountCardNumber != null) {
                pw.println("DISCOUNT CARD;DISCOUNT PERCENTAGE");
                pw.println(check.discountCardNumber + ";" + check.discountCardAmountPercentage + '%');
                pw.println();
            }

            pw.println("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT");
            pw.println(currencyFormat(check.totalPrice) + ";" + currencyFormat(check.totalDiscount) + ";" +
                    currencyFormat(check.totalWithDiscount));
        } catch (FileNotFoundException e) {
            throw new InternalServerException();
        }
    }

    private String convertToCsvString(PurchasedItem purchasedItem) {
        return new StringBuilder()
                .append(purchasedItem.qty).append(";")
                .append(purchasedItem.description).append(";")
                .append(currencyFormat(purchasedItem.price)).append(";")
                .append(currencyFormat(purchasedItem.discount)).append(";")
                .append(currencyFormat(purchasedItem.total))
                .toString();
    }
}
