package ru.clevertec.check.service;

import ru.clevertec.check.dto.request.ProductModifDto;
import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.exception.EntityNotFoundException;

public interface ProductService {

    ProductDto findById(Long id) throws EntityNotFoundException;

    boolean deleteById(Long id);

    ProductDto create(ProductModifDto productDto);

    boolean update(long id, ProductModifDto productDto);
}
