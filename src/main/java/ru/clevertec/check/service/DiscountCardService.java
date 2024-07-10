package ru.clevertec.check.service;

import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.exception.EntityNotFoundException;

public interface DiscountCardService {

    DiscountCardDto findById(Long id) throws EntityNotFoundException;
    DiscountCardDto findByNumber(Long number) throws EntityNotFoundException;
}
