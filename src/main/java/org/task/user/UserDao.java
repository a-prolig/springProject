package org.task.user;

import org.task.config.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getAll() {
        String SQL_QUERY = "select * from users";
        List<User> users = new ArrayList<>();
        try (Connection con = getConfigConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY );
             ResultSet rs = pst.executeQuery()) {
            while (rs.next() ) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("userName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(Long id) {
        String SQL_QUERY = "select * from users where users.id=?";
        List<User> users = getAll();
        User user = new User();
        try (Connection con = getConfigConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next() ) {
                    int userId = (int) rs.getLong("id");
                    user = users.stream().filter(u -> u.id == userId).findFirst().orElse(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void save(Long id, String userName) {
        String SQL_QUERY = "insert into users (id, username) values (?, ?)";
        try (Connection con = getConfigConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            pst.setLong(1, id);
            pst.setString(2, userName);
            pst.executeUpdate();
            System.out.println("Database has been update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String SQL_QUERY = "delete from users where users.id=?";
        try (Connection con = getConfigConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY)) {
            pst.setLong(1, id);
            pst.executeUpdate();
            System.out.println("Database has been update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection getConfigConnection() throws SQLException {
        AppConfig appConfig = new AppConfig();
        return appConfig.getDataSource().getConnection();
    }
}
