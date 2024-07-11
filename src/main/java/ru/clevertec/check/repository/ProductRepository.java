package ru.clevertec.check.repository;

import ru.clevertec.check.config.DataSource;
import ru.clevertec.check.dto.request.ProductModifDto;
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
    private final String DELETE_BY_ID = "DELETE from product WHERE id = (?);";
    private final String INSERT = "INSERT INTO product(description, price, quantity_in_stock, wholesale_product)  VALUES(?,?,?,?) RETURNING *;";
    private final ProductSqlMapper sqlMapper = new ProductSqlMapper();

    public Optional<Product> findById(long id) {
        try (Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
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

    public Product create(ProductModifDto dto) {
        try (Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, dto.description());
            preparedStatement.setBigDecimal(2, dto.price());
            preparedStatement.setInt(3, dto.quantity());
            preparedStatement.setBoolean(4, dto.isWholesale());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            return sqlMapper.toEntity(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(Product entity) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection
                .prepareStatement(FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            preparedStatement.setLong(1, entity.getId());
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            rs.updateString("description", entity.getDescription());
            rs.updateBigDecimal("price", entity.getPrice());
            rs.updateInt("quantity_in_stock", entity.getQuantityInStock());
            rs.updateBoolean("wholesale_product", entity.getWholesaleProduct());
            rs.updateRow();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
