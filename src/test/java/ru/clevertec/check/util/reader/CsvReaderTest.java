package ru.clevertec.check.util.reader;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.testutil.ProductTestData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.clevertec.check.util.Constants.ROOT_PATH;

class CsvReaderTest {

    @Test
    void read() {
        CsvReader<Product> reader = new CsvReader<>();
        List<Product> productList = reader.read(ROOT_PATH + "\\src\\test\\resources\\products-test.csv", Product.class);

        assertAll(
                () -> assertEquals(ProductTestData.getProduct(), productList.getFirst()),
                () -> assertEquals(ProductTestData.getProductCream(), productList.get(1))
        );
    }
}
