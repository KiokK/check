package ru.clevertec.check.service;

import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.exception.EntityNotFoundException;

public interface ProductService {

    ProductDto findById(Long id) throws EntityNotFoundException;
}
