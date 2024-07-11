package ru.clevertec.check.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseServletDto {

    public List<PurchasedItemReq> products;
    public Long discountCard;
    public BigDecimal balanceDebitCard;
}
