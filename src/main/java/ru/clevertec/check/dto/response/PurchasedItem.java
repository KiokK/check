package main.java.ru.clevertec.check.dto.response;

import java.math.BigDecimal;

public class PurchasedItem {

    public int qty;
    public String description;
    public BigDecimal price;
    public BigDecimal total;
    public BigDecimal discount = new BigDecimal(0);
}
