package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.dto.request.PurchaseServletDto;

public class PurchaseDtoMapper {

    public PurchaseDto toPurchaseDto(PurchaseServletDto servletDto) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.balanceDebitCard = servletDto.balanceDebitCard;
        purchaseDto.discountCard = servletDto.discountCard;
        servletDto.products.forEach(pair -> {
            if (purchaseDto.products.containsKey(pair.id)) {
                purchaseDto.products.put(pair.id, purchaseDto.products.get(pair.id) + pair.quantity);
                } else {
                purchaseDto.products.put(pair.id, pair.quantity);
                }
            }
        );

        return purchaseDto;
    }
}
