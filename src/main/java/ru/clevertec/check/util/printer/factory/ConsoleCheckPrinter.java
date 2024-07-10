package main.java.ru.clevertec.check.util.printer.factory;

import main.java.ru.clevertec.check.dto.response.CheckDto;
import main.java.ru.clevertec.check.util.printer.Printer;

import static main.java.ru.clevertec.check.util.FormatUtil.currencyFormat;
import static main.java.ru.clevertec.check.util.FormatUtil.dateFormat;
import static main.java.ru.clevertec.check.util.FormatUtil.timeFormat;

public class ConsoleCheckPrinter implements Printer<CheckDto> {

    @Override
    public void print(CheckDto data, String filePath) {
        System.out.println("Result check:\n" +
                "Date: " + dateFormat(data.date) +
                ", Time: " + timeFormat(data.time) +
                ",\n");
        System.out.println("QTY | Description || PRICE | Discount | TOTAL");

        data.items.forEach(
                item -> System.out.println(item.qty + "\t|" + item.description + "\t|| " + currencyFormat(item.price) + "\t" +
                        currencyFormat(item.discount) + "\t" + currencyFormat(item.total)));

        if (data.discountCardNumber != null) {
            System.out.println("\nDISCOUNT CARD=" + data.discountCardNumber +
                    ", DISCOUNT=" + data.discountCardAmountPercentage + "%");
        }

        System.out.println(
                "\nTotal price=" + currencyFormat(data.totalPrice) +
                        ", Total discount=" + currencyFormat(data.totalDiscount) +
                        ", Total with discount=" + currencyFormat(data.totalWithDiscount));
    }
}
