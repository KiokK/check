package main.java.ru.clevertec.check.util;

import main.java.ru.clevertec.check.dto.request.PurchaseDto;
import main.java.ru.clevertec.check.exception.BadRequestException;

import java.math.BigDecimal;
import java.util.HashMap;

public class ArgsParser {

    private static final String ID_AMOUNT_SEPARATOR = "-";
    private static final String ARG_SEPARATOR = "=";

    private static final String DISCOUNT_CARD = "discountCard";
    private static final String BALANCE_DEBIT_CARD = "balanceDebitCard";

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
                }
            } else {
                addProduct(request, part);
            }
        }

        if (request.balanceDebitCard == null) {
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
