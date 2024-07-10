package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.model.Product;

public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice(),
                product.getQuantityInStock(), product.getWholesaleProduct());
    }
}
