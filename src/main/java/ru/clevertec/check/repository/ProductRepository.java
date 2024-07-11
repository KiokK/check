package ru.clevertec.check.repository;

import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.mapper.sql.ProductSqlMapper;
import ru.clevertec.check.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductRepository {

    private final String FIND_BY_ID = "SELECT * from product WHERE id = (?);";
    private final ProductSqlMapper sqlMapper = new ProductSqlMapper();

    public Optional<Product> findById(long id) {
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
}
