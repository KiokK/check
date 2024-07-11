package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.exception.EntityNotFoundException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static ru.clevertec.check.testutil.ProductTestData.DEF_ID;
import static ru.clevertec.check.testutil.ProductTestData.getProduct;
import static ru.clevertec.check.testutil.ProductTestData.getProductDto;

class ProductServiceImplTest {

    ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    @InjectMocks
    ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    void checkFindByIdShouldReturnFound() {
        long testId = DEF_ID;
        Product product = getProduct();
        ProductDto expected = getProductDto();

        doReturn(Optional.of(product))
                .when(productRepository).findById(testId);
        ProductDto actual = productService.findById(testId);

        assertEquals(expected, actual);
    }

    @Test
    void checkFindByIdShouldThrowNotFound() {
        long testId = 123123L;

        doReturn(Optional.empty())
                .when(productRepository).findById(testId);

        assertThrows(EntityNotFoundException.class, () -> productService.findById(testId));
    }
}
