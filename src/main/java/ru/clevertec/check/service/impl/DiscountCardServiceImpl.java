package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.request.DiscountCardModifDto;
import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.service.DiscountCardService;

public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;
    private final DiscountCardMapper discountCardMapper = new DiscountCardMapper();

    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

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

    @Override
    public boolean deleteById(Long id) {
        return discountCardRepository.deleteById(id);
    }

    @Override
    public DiscountCardDto create(DiscountCardModifDto productDto) {
        DiscountCard created = discountCardRepository.create(productDto);
        return discountCardMapper.toDiscountCardDto(created);
    }

    @Override
    public boolean update(long id, DiscountCardModifDto discountCardModifDto) {
        return discountCardRepository.update(discountCardMapper.toDiscountCard(id, discountCardModifDto));
    }
}
