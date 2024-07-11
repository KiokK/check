package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository = new DiscountCardRepository();
    private final DiscountCardMapper discountCardMapper = new DiscountCardMapper();

    @Override
    public DiscountCardDto findById(Long id) throws EntityNotFoundException {
        return discountCardRepository.findById(id).map(discountCardMapper::toDiscountCardDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public DiscountCardDto findByNumber(Long number) throws EntityNotFoundException {
        return discountCardRepository.findByNumber(number).map(discountCardMapper::toDiscountCardDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
