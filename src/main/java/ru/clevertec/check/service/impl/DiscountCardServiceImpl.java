package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.dto.response.DiscountCardDto;
import main.java.ru.clevertec.check.exception.EntityNotFoundException;
import main.java.ru.clevertec.check.filerepository.DiscountCardRepository;
import main.java.ru.clevertec.check.mapper.DiscountCardMapper;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.util.Constants;

public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository = new DiscountCardRepository(Constants.DISCOUNT_CARDS_PATH);
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
