package internetshop.dao.impl;

import internetshop.dao.ProductDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.storage.Storage;
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
        Connection connection = ConnectionUtil.getConnection();
        String query = "INSERT INTO products ( name, price) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM products WHERE product_id = ?";
        Product product;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product = getProductFromResultSet(resultSet);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return Optional.of(product);
    }

    @Override
    public List<Product> getAll() {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM products";
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return products;
    }

    @Override
    public Product update(Product product) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "UPDATE products SET name = ?, price = ? WHERE product_id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
            statement.close();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Can't update this product", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "DELETE FROM products WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            boolean execute = statement.execute();
            statement.close();
            return execute;
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete by this Id", e);
        }
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("product_id"),
                rs.getString("name"), rs.getDouble("price"));
    }
}
