package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.dto.response.DiscountCardDto;
import main.java.ru.clevertec.check.model.DiscountCard;

public class DiscountCardMapper {

    public DiscountCardDto toDiscountCardDto(DiscountCard discountCard) {
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.id = discountCard.getId();
        discountCardDto.amount = discountCard.getAmount();
        discountCardDto.number = discountCard.getNumber();

        return discountCardDto;
    }
}
