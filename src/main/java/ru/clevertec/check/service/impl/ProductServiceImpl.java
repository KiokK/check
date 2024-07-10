package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.filerepository.ProductRepository;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.util.Constants;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepository(Constants.PRODUCT_PATH);
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public ProductDto findById(Long id) throws EntityNotFoundException {
        return productRepository.findById(id).map(productMapper::toProductDto)
                .orElseThrow(EntityNotFoundException::new);
    }

}
