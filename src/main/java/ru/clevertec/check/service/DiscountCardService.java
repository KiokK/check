package ru.clevertec.check.service;

import ru.clevertec.check.dto.request.DiscountCardModifDto;
import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.exception.EntityNotFoundException;

public interface DiscountCardService {

    DiscountCardDto findById(Long id) throws EntityNotFoundException;

    DiscountCardDto findByNumber(Long number) throws EntityNotFoundException;

    boolean deleteById(Long id);

    DiscountCardDto create(DiscountCardModifDto productDto);

    boolean update(long id, DiscountCardModifDto productDto);
}
