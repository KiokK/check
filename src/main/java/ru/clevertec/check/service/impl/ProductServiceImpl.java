package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.request.ProductModifDto;
import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = new ProductMapper();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto findById(Long id) throws EntityNotFoundException {
        return productRepository.findById(id).map(productMapper::toProductDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        return productRepository.deleteById(id);
    }

    @Override
    public ProductDto create(ProductModifDto createDto) {
        Product created = productRepository.create(createDto);
        return productMapper.toProductDto(created);
    }

    @Override
    public boolean update(long id, ProductModifDto productDto) {
        return productRepository.update(productMapper.toProduct(id, productDto));
    }

}
