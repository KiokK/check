package ru.clevertec.check.testutil;

import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.model.Product;

import java.math.BigDecimal;

public class ProductTestData {

    public static final long DEF_ID = 1L;
    public static final String DEF_DESCRIPTION = "Milk";
    public static final BigDecimal DEF_PRICE = new BigDecimal("1.07");
    public static final int DEF_QUANTITY_IN_STOCK = 10;
    public static final boolean DEF_WHOLESALE = true;

    public static ProductDto getProductDto() {
        return new ProductDto(DEF_ID, DEF_DESCRIPTION, DEF_PRICE, DEF_QUANTITY_IN_STOCK, DEF_WHOLESALE);
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setId(DEF_ID);
        product.setWholesaleProduct(DEF_WHOLESALE);
        product.setDescription(DEF_DESCRIPTION);
        product.setPrice(DEF_PRICE);
        product.setQuantityInStock(DEF_QUANTITY_IN_STOCK);
        return product;
    }

    public static Product getProductCream() {
        Product product = new Product();
        product.setId(2L);
        product.setWholesaleProduct(false);
        product.setDescription("Cream 400g");
        product.setPrice(BigDecimal.valueOf(2.71));
        product.setQuantityInStock(20);
        return product;
    }
}
