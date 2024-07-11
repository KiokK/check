package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.request.DiscountCardModifDto;
import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.model.DiscountCard;

public class DiscountCardMapper {

    public DiscountCardDto toDiscountCardDto(DiscountCard discountCard) {
        DiscountCardDto discountCardDto = new DiscountCardDto();
        discountCardDto.id = discountCard.getId();
        discountCardDto.amount = discountCard.getAmount();
        discountCardDto.number = discountCard.getNumber();

        return discountCardDto;
    }

    public DiscountCard toDiscountCard(long id, DiscountCardModifDto dto) {
        DiscountCard discountCard = new DiscountCard();
        discountCard.setId(id);
        discountCard.setAmount(dto.discountAmount());
        discountCard.setNumber(dto.discountCard());

        return discountCard;
    }
}
