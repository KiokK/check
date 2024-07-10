package main.java.ru.clevertec.check.filerepository;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.util.reader.CsvReader;

import java.util.List;
import java.util.Optional;

import static main.java.ru.clevertec.check.util.Constants.ROOT_PATH;

public class DiscountCardRepository {

    private static List<DiscountCard> discountCards;

    public DiscountCardRepository(String DISCOUNT_CARDS_PATH) {
        CsvReader<DiscountCard> discountCardCsvReader = new CsvReader<>();
        discountCards = discountCardCsvReader.read(ROOT_PATH + DISCOUNT_CARDS_PATH, DiscountCard.class);
    }

    public Optional<DiscountCard> findById(long id) {
        return discountCards.stream().filter(discountCard -> discountCard.getId() == id).findFirst();
    }

    public Optional<DiscountCard> findByNumber(long number) {
        return discountCards.stream().filter(discountCard -> discountCard.getNumber() == number).findFirst();
    }
}
