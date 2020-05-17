package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.lib.Dao;
import internetshop.model.Order;
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
public class OrderDaoJdbsImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUE (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            order.setId(generatedKeys.getLong(1));
            addProducts(order, connection);
        } catch (SQLException e) {
            throw new RuntimeException("Can't create order", e);
        }
        return order;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.of(new Order(id,
                getUserIdFromOrderId(id), getProductsByOrderId(id)));
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT order_id FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getById(resultSet.getLong(1)).get());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user orders", e);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong(1),
                        resultSet.getLong(2)));
            }
            for (Order order : orders) {
                order.setProducts(getProductsByOrderId(order.getId()));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all orders from db", e);
        }
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET user_id = ? WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
            query = "DELETE FROM orders_products WHERE order_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            addProducts(order, connection);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException("Can't update order", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM orders_products WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            query = "DELETE FROM orders WHERE order_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete order", e);
        }
        return true;
    }

    private boolean addProducts(Order order, Connection connection)
            throws SQLException {
        String query = "INSERT INTO orders_products VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (Product product : order.getProducts()) {
            statement.setLong(1, order.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
        return true;
    }

    private Long getUserIdFromOrderId(Long orderId) {
        String query = "SELECT user_id FROM orders WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user_id from order_id", e);
        }
    }

    public List<Product> getProductsByOrderId(Long id) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT pr.product_id ,pr.name, pr.price FROM orders o"
                + " INNER JOIN orders_products o_p"
                + " ON o.order_id = o_p.order_id"
                + " INNER JOIN products pr"
                + " ON o_p.product_id = pr.product_id"
                + " WHERE o.order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get products from order Id", e);
        }
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("product_id"),
                rs.getString("name"), rs.getDouble("price"));
    }
}
