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
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ProductDaoImpl implements ProductDao {
    Connection connection = ConnectionUtil.getConnection();

    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products ( name, price) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        Product product = new Product(null, 0);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("product_id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                product.setId(productId);
                product.setName(name);
                product.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return Optional.of(product);
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(x -> product.getId().equals(Storage.products.get(x).getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.removeIf(product -> product.getId().equals(id));
    }
}
