package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.dto.response.ProductDto;
import main.java.ru.clevertec.check.model.Product;

public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice(),
                product.getQuantityInStock(), product.getWholesaleProduct());
    }
}
