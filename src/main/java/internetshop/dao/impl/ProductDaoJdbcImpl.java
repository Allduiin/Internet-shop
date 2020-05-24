package internetshop.dao.impl;

import internetshop.dao.ProductDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products ( name, price) VALUES (?,?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Product product = getProductFromResultSet(resultSet);
            return Optional.of(product);
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Can't update this product", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM shopping_carts_products WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            doQueryWithId(query, id, connection);
            query = "DELETE FROM orders_products WHERE product_id = ?;";
            doQueryWithId(query, id, connection);
            query = "DELETE FROM products WHERE product_id = ?;";
            doQueryWithId(query, id, connection);
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete by this Id", e);
        }
        return true;
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("product_id"),
                rs.getString("name"), rs.getDouble("price"));
    }

    private void doQueryWithId(String query, Long id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
    }
}
