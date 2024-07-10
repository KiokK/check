package ru.clevertec.check.dto.response;

import java.math.BigDecimal;

public record ProductDto(

        Long id,
        String description,
        BigDecimal price,
        Integer quantityInStock,
        Boolean wholesaleProduct
) {
}
