package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.response.ProductDto;
import main.java.ru.clevertec.check.exception.EntityNotFoundException;

public interface ProductService {

    ProductDto findById(Long id) throws EntityNotFoundException;
}
