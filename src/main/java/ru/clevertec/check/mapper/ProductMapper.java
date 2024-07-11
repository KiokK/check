package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.request.ProductModifDto;
import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.model.Product;

public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice(),
                product.getQuantityInStock(), product.getWholesaleProduct());
    }

    public Product toProduct(long id, ProductModifDto productDto) {
        Product product = new Product();
        product.setId(id);
        product.setWholesaleProduct(productDto.isWholesale());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setQuantityInStock(productDto.quantity());

        return product;
    }
}
