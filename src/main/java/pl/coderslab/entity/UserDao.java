package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;


public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";
    private static final String READ_USER_QUERY = "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hashPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
//        if (!=hashed)
//        System.out.println("niepoprawne hasło");
    }

    public User read(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(READ_USER_QUERY);
            stmt.setInt(1,userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user2 = new User();
                user2.setId(rs.getInt("id"));
                user2.setUserName(rs.getString("username"));
                user2.setEmail(rs.getString("email"));
                user2.setPassword(rs.getString("password"));
//                System.out.println("id: " + id + "\n" + "username: " + username + "\n" + "email: " + email + "\n" + "password: " + password);
                return user2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




