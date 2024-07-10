package main.java.ru.clevertec.check.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CheckDto {

    public LocalTime time;
    public LocalDate date;
    public List<PurchasedItem> items = new ArrayList<>();
    public Long discountCardNumber;
    public Byte discountCardAmountPercentage;
    public BigDecimal totalPrice = new BigDecimal(0);
    public BigDecimal totalDiscount = new BigDecimal(0);
    public BigDecimal totalWithDiscount = new BigDecimal(0);
}
