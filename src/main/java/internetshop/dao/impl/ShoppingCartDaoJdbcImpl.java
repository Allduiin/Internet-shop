package internetshop.dao.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUE (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            shoppingCart.setId(generatedKeys.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException("Can't create shoppingCart", e);
        }
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart shoppingCart = null;
        String query = "SELECT cart_id FROM shopping_carts WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSetCart = statement.executeQuery();
            if (resultSetCart.next()) {
                Long cartId = resultSetCart.getLong(1);
                List<Product> products = getProductsByCartId(cartId);
                shoppingCart = new ShoppingCart(cartId, userId, products);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get shoppingCart from db by userId", e);
        }
        return Optional.ofNullable(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getById(Long id) {
        return Optional.of(new ShoppingCart(id,
                getUserIdFromCartId(id), getProductsByCartId(id)));
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT * FROM shopping_carts;";
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(new ShoppingCart(resultSet.getLong(1),
                        resultSet.getLong(2)));
            }
            for (ShoppingCart shoppingCart : shoppingCarts) {
                shoppingCart.setProducts(getProductsByCartId(shoppingCart.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get all shoppingCart from db", e);
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
            addProducts(shoppingCart, connection);
            return shoppingCart;
        } catch (SQLException e) {
            throw new RuntimeException("Can't update shoppingCart", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            query = "DELETE FROM shopping_carts WHERE cart_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete shopping cart", e);
        }
        return true;
    }

    public List<Product> getProductsByCartId(Long id) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT pr.product_id ,pr.name, pr.price FROM shopping_carts sc"
                + " INNER JOIN shopping_carts_products scp"
                + " ON sc.cart_id = scp.cart_id"
                + " INNER JOIN products pr"
                + " ON scp.product_id = pr.product_id"
                + " WHERE sc.cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get products from shoppingCart Id", e);
        }
    }

    private Product getProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("product_id"),
                rs.getString("name"), rs.getDouble("price"));
    }

    private Long getUserIdFromCartId(Long cartId) {
        String query = "SELECT user_id FROM shopping_carts WHERE cart_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user_id from shoppingCart_id", e);
        }
    }

    private boolean addProducts(ShoppingCart cart, Connection connection)
            throws SQLException {
        String query = "INSERT INTO shopping_carts_products VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (Product product : cart.getProducts()) {
            statement.setLong(1, cart.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
        return true;
    }
}
