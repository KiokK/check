package ru.clevertec.check.model;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private Boolean wholesaleProduct;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }
}
