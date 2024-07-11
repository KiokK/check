package ru.clevertec.check.repository;

import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.dto.request.DiscountCardModifDto;
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
    private final String DELETE_BY_ID = "DELETE from discount_card WHERE id = (?);";
    private final String INSERT = "INSERT INTO discount_card(number, amount)  VALUES(?,?) RETURNING *;";

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
        try (Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NUMBER)) {
            preparedStatement.setLong(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return Optional.ofNullable(sqlMapper.toEntity(rs));
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }


    public boolean deleteById(long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public DiscountCard create(DiscountCardModifDto dto) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, dto.discountCard());
            preparedStatement.setByte(2, dto.discountAmount());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(DiscountCard entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setLong(1, entity.getId());
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            rs.updateInt("amount", entity.getAmount());
            rs.updateLong("number", entity.getNumber());
            rs.updateRow();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
