package main.java.ru.clevertec.check.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###0.00$");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String currencyFormat(BigDecimal n) {
        return decimalFormat.format(n);
    }

    public static String dateFormat(LocalDate date) {
        return dateFormat.format(date);
    }

    public static String timeFormat(LocalTime time) {
        return timeFormat.format(time);
    }
}
