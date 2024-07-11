package ru.clevertec.check.dto.request;

import java.math.BigDecimal;

public record ProductModifDto(

        String description,
        BigDecimal price,
        Integer quantity,
        Boolean isWholesale
) {
}
