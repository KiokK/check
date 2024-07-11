package ru.clevertec.check.repository;

import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.mapper.sql.DiscountCardSqlMapper;
import ru.clevertec.check.model.DiscountCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DiscountCardRepository {

    private final String FIND_BY_ID = "SELECT * from discount_card WHERE id = (?);";
    private final String FIND_BY_NUMBER = "SELECT * from discount_card WHERE number = (?);";
    private final DiscountCardSqlMapper sqlMapper = new DiscountCardSqlMapper();

    public Optional<DiscountCard> findById(long id) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return Optional.ofNullable(sqlMapper.toEntity(rs));
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    public Optional<DiscountCard> findByNumber(long number) {
        try (Connection connection = DataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NUMBER);
            preparedStatement.setLong(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return Optional.ofNullable(sqlMapper.toEntity(rs));
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
