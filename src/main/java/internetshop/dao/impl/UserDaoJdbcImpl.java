package internetshop.dao.impl;

import internetshop.dao.UserDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.User;
import internetshop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT FROM users WHERE login = ?";
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            user = getUserFromResultSet(resultSet);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return null;
    }

    @Override
    public User create(User user) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "INSERT INTO users ( login, password) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM products WHERE product_id = ?";
        Optional<User> user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        String query = "SELECT * FROM users";
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "UPDATE users SET login = ?, password = ? WHERE user_id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Can't update this product", e);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        String query = "DELETE FROM users WHERE user_id = ?";
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

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(rs.getLong("user_id"),
                rs.getString("login"), rs.getString("password"));
    }
}
