package main.java.ru.clevertec.check.filerepository;

import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.util.reader.CsvReader;

import java.util.List;
import java.util.Optional;

import static main.java.ru.clevertec.check.util.Constants.ROOT_PATH;

public class ProductRepository {

     private static List<Product> products;

    public ProductRepository(String PRODUCT_PATH) {
        CsvReader<Product> productCsvReader = new CsvReader<>();
        products = productCsvReader.read(ROOT_PATH + PRODUCT_PATH, Product.class);
    }

    public Optional<Product> findById(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }
}
