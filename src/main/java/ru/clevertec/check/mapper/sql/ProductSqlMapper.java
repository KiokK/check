package ru.clevertec.check.mapper.sql;

import ru.clevertec.check.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSqlMapper implements SqlMapper<Product> {

    private final String ID = "id";
    private final String DESCRIPTION = "description";
    private final String PRICE = "price";
    private final String QUANTITY_IN_STOCK = "quantity_in_stock";
    private final String WHOLESALE_PRODUCT = "wholesale_product";

    @Override
    public Product toEntity(ResultSet rs) {
        try {
            if (rs.isAfterLast()) {
                return null;
            }
            Product product = new Product();
            product.setId(rs.getLong(ID));
            product.setDescription(rs.getString(DESCRIPTION));
            product.setPrice(rs.getBigDecimal(PRICE));
            product.setQuantityInStock(rs.getInt(QUANTITY_IN_STOCK));
            product.setWholesaleProduct(rs.getBoolean(WHOLESALE_PRODUCT));

            return product;
        } catch (SQLException ignore) {
            return null;
        }
    }
}
