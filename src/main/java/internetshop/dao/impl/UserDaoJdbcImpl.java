package internetshop.dao.impl;

import internetshop.dao.UserDao;
import internetshop.lib.Dao;
import internetshop.model.Role;
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
        String query = "SELECT * FROM users WHERE login = ?";
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users ( login, password, salt) VALUES (?,?,?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setBytes(3, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
            setRole(user.getId(), 2L);
            user.setRoles(getRoles(user.getId()));
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't read result of statment", e);
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET login = ?, password = ? WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
            deleteAllRoles(user.getId());
            for (Role role : user.getRoles()) {
                setRole(user.getId(), role.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't update this product", e);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            deleteAllRoles(id);
            String query = "DELETE FROM users WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't delete by this Id", e);
        }
        return true;
    }

    private List<Role> getRoles(Long userId) {
        String query = "SELECT r.name FROM users u "
                + "INNER JOIN users_roles ur "
                + "ON u.user_id = ur.user_id "
                + "INNER JOIN roles r "
                + "ON ur.role_id = r.role_id "
                + "WHERE u.user_id = ?;";
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get role by user Id", e);
        }
        return roles;
    }

    private void setRole(Long userId, Long roleId) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES (?,?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't set role to user", e);
        }
    }

    private void deleteAllRoles(Long userId) {
        String query = "DELETE FROM users_roles WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't set role to user", e);
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User(rs.getLong("user_id"),
                rs.getString("login"), rs.getString("password"),
                rs.getBytes("salt"));
        user.setRoles(getRoles(user.getId()));
        return user;
    }
}
