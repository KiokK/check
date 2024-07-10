package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.response.DiscountCardDto;
import main.java.ru.clevertec.check.exception.EntityNotFoundException;

public interface DiscountCardService {

    DiscountCardDto findById(Long id) throws EntityNotFoundException;
    DiscountCardDto findByNumber(Long number) throws EntityNotFoundException;
}
