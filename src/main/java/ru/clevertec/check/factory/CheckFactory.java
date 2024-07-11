package ru.clevertec.check.factory;

import ru.clevertec.check.dto.request.ProductModifDto;
import ru.clevertec.check.dto.request.PurchaseDto;
import ru.clevertec.check.dto.response.CheckDto;
import ru.clevertec.check.dto.response.DiscountCardDto;
import ru.clevertec.check.dto.response.ProductDto;
import ru.clevertec.check.dto.response.PurchasedItem;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.repository.DiscountCardRepository;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.ProductService;
import ru.clevertec.check.service.impl.DiscountCardServiceImpl;
import ru.clevertec.check.service.impl.ProductServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class CheckFactory {

    private final BigDecimal DISCOUNT_OPT_PERCENTAGE = new BigDecimal("0.1");

    private final ProductService productService = new ProductServiceImpl(new ProductRepository());
    private final DiscountCardService discountCardService = new DiscountCardServiceImpl(new DiscountCardRepository());

    public CheckDto createCheck(PurchaseDto purchaseDto) throws NotEnoughMoneyException, BadRequestException {
        PurchasedItem item;

        CheckDto resultCheck = new CheckDto();
        BigDecimal discountPercentageByCard = null;
        resultCheck.discountCardNumber = null;

        if (purchaseDto.discountCard != null) {
            DiscountCardDto findDiscountCard = discountCardService.findByNumber(purchaseDto.discountCard);
            resultCheck.discountCardNumber = findDiscountCard.number;
            resultCheck.discountCardAmountPercentage = findDiscountCard.amount;
            discountPercentageByCard = new BigDecimal(findDiscountCard.amount / 100.0);
        }

        if (purchaseDto.products == null || purchaseDto.products.isEmpty()) {
            throw new BadRequestException();
        }

        Map<Long, ProductModifDto> updated = new HashMap<>();
        for (Map.Entry<Long, Integer> productInCart : purchaseDto.products.entrySet()) {
            ProductDto findProduct = productService.findById(productInCart.getKey());
            if (findProduct.quantityInStock() < productInCart.getValue()) {
                throw new BadRequestException();
            }

            item = new PurchasedItem();
            item.qty = productInCart.getValue();
            item.description = findProduct.description();
            item.price = findProduct.price();
            item.discount = new BigDecimal(0);
            item.total = item.price.multiply(new BigDecimal(item.qty));

            if (item.qty >= 5 && findProduct.wholesaleProduct()) {
                item.discount = item.total.multiply(DISCOUNT_OPT_PERCENTAGE);
            } else {
                if (discountPercentageByCard != null) {
                    item.discount = item.total.multiply(discountPercentageByCard);
                }
            }
            resultCheck.items.add(item);
            resultCheck.totalPrice = resultCheck.totalPrice.add(item.total);
            resultCheck.totalDiscount = resultCheck.totalDiscount.add(item.discount);
            updated.put(productInCart.getKey(), new ProductModifDto(findProduct.description(), findProduct.price(),
                    findProduct.quantityInStock() - productInCart.getValue(), findProduct.wholesaleProduct()));
        }
        resultCheck.totalWithDiscount = resultCheck.totalPrice.subtract(resultCheck.totalDiscount);
        resultCheck.date = LocalDate.now();
        resultCheck.time = LocalTime.now();

        if (resultCheck.totalPrice.compareTo(purchaseDto.balanceDebitCard) > 0) {
            throw new NotEnoughMoneyException();
        }
        updateData(updated);

        return resultCheck;
    }
    private void updateData(Map<Long, ProductModifDto> productModifDtos) {
        productModifDtos.forEach((key, value) -> productService.update(key, value));
    }
}
