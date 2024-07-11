package ru.clevertec.check.util.printer.factory;

import ru.clevertec.check.dto.response.CheckDto;
import ru.clevertec.check.util.printer.Printer;

import static ru.clevertec.check.util.FormatUtil.currencyFormat;
import static ru.clevertec.check.util.FormatUtil.dateFormat;
import static ru.clevertec.check.util.FormatUtil.timeFormat;

public class ConsoleCheckPrinter implements Printer<CheckDto> {

    @Override
    public void print(CheckDto data, String filePath) {
        System.out.println(printString(data));
    }

    @Override
    public String printString(CheckDto data) {
        StringBuilder sb = new StringBuilder();
        sb.append("Result check:\n" +
                "Date: " + dateFormat(data.date) +
                ", Time: " + timeFormat(data.time) +
                ",\n");
        sb.append("QTY | Description || PRICE | Discount | TOTAL\n");

        data.items.forEach(
                item -> sb.append(item.qty + "\t|" + item.description + "\t|| " + currencyFormat(item.price) + "\t" +
                        currencyFormat(item.discount) + "\t" + currencyFormat(item.total) + "\n"));

        if (data.discountCardNumber != null) {
            sb.append("\nDISCOUNT CARD=" + data.discountCardNumber +
                    ", DISCOUNT=" + data.discountCardAmountPercentage + "%\n");
        }

        sb.append(
                "\nTotal price=" + currencyFormat(data.totalPrice) +
                        ", Total discount=" + currencyFormat(data.totalDiscount) +
                        ", Total with discount=" + currencyFormat(data.totalWithDiscount) + "\n");

        return sb.toString();
    }
}
