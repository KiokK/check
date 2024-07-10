package ru.clevertec.check.dto.request;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PurchaseDto {

    public Map<Long, Integer> products = new HashMap<>();
    public Long discountCard;
    public BigDecimal balanceDebitCard;
}
