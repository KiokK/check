package ru.clevertec.check.util;

import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.HashMap;

public class ArgsParser {

    private static final String ID_AMOUNT_SEPARATOR = "-";
    private static final String ARG_SEPARATOR = "=";

    private static final String DISCOUNT_CARD = "discountCard";
    private static final String BALANCE_DEBIT_CARD = "balanceDebitCard";
    private static final String SAVE_TO_FILE = "saveToFile";
    private static final String DATASOURCE_URL = "datasource.url";
    private static final String DATASOURCE_USERNAME = "datasource.username";
    private static final String DATASOURCE_PASSWORD = "datasource.password";

    public static PurchaseDto consoleArgsParse(String[] args) throws BadRequestException {
        PurchaseDto request = new PurchaseDto();
        request.products = new HashMap<>();
        String[] part;
        for (String arg : args) {
            part = arg.split(ID_AMOUNT_SEPARATOR);
            if (part.length != 2) {
                part = arg.split(ARG_SEPARATOR);
                switch (part[0]) {
                    case DISCOUNT_CARD -> request.discountCard = Long.valueOf(part[1]);
                    case BALANCE_DEBIT_CARD -> request.balanceDebitCard = new BigDecimal(part[1]);
                    case SAVE_TO_FILE -> Constants.SAVE_TO_FILE = part[1];
                    case DATASOURCE_URL -> DataSource.DATASOURCE_URL = part[1];
                    case DATASOURCE_USERNAME -> DataSource.DATASOURCE_USERNAME = part[1];
                    case DATASOURCE_PASSWORD -> DataSource.DATASOURCE_PASSWORD = part[1];
                }
            } else {
                addProduct(request, part);
            }
        }

        if (request.balanceDebitCard == null || Constants.SAVE_TO_FILE == null || DataSource.DATASOURCE_USERNAME == null ||
                DataSource.DATASOURCE_PASSWORD == null || DataSource.DATASOURCE_URL == null) {
            throw new BadRequestException();
        }

        return request;
    }

    private static void addProduct(PurchaseDto request, String[] part) {
        try {
            Long id = Long.valueOf(part[0]);
            Integer amount = Integer.valueOf(part[1]);
            if (request.products.containsKey(id)) {
                request.products.put(id, request.products.get(id) + amount);
            } else {
                request.products.put(id, amount);
            }
        } catch (NumberFormatException ex) {
            throw new BadRequestException();
        }
    }
}
