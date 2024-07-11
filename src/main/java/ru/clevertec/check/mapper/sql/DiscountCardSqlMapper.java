package ru.clevertec.check.mapper.sql;

import ru.clevertec.check.model.DiscountCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCardSqlMapper implements SqlMapper<DiscountCard> {

    private final String ID = "id";
    private final String AMOUNT = "amount";
    private final String NUMBER = "number";

    @Override
    public DiscountCard toEntity(ResultSet rs) {
        try {
            if (rs.isAfterLast()) {
                return null;
            }
            DiscountCard discountCard = new DiscountCard();
            discountCard.setId(rs.getLong(ID));
            discountCard.setAmount(rs.getByte(AMOUNT));
            discountCard.setNumber(rs.getLong(NUMBER));

            return discountCard;
        } catch (SQLException ignore) {
            return null;
        }
    }
}
