package ru.clevertec.check.util.printer.factory;

import ru.clevertec.check.dto.response.CheckDto;
import ru.clevertec.check.dto.response.PurchasedItem;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.util.printer.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static ru.clevertec.check.util.FormatUtil.currencyFormat;
import static ru.clevertec.check.util.FormatUtil.dateFormat;
import static ru.clevertec.check.util.FormatUtil.timeFormat;

public class CsvCheckPrinter implements Printer<CheckDto> {

    @Override
    public void print(CheckDto check, String filePath) {
        File csvOutputFile = new File(filePath);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(printString(check));
        } catch (FileNotFoundException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public String printString(CheckDto check) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Date;Time\n");
        stringBuilder.append(dateFormat(check.date) + ";" + timeFormat(check.time));

        stringBuilder.append("\n\nQTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
        check.items.stream()
                .map(this::convertToCsvString)
                .forEach(s -> stringBuilder.append(s).append("\n"));
        stringBuilder.append("\n");

        if (check.discountCardNumber != null) {
            stringBuilder.append("DISCOUNT CARD;DISCOUNT PERCENTAGE\n");
            stringBuilder.append(check.discountCardNumber + ";" + check.discountCardAmountPercentage + "%\n\n");
        }

        stringBuilder.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        stringBuilder.append(currencyFormat(check.totalPrice) + ";" + currencyFormat(check.totalDiscount) + ";" +
                currencyFormat(check.totalWithDiscount));

        return stringBuilder.toString();
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
