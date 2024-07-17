package org.example.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.example.models.User;


public class DataBase {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static String DB_DRIVER_NAME;
    private static Connection connection;
    private static final String SQL_CREATE_USERS_DB = "CREATE TABLE IF NOT EXISTS users (" + "id SERIAL PRIMARY KEY, " + "name VARCHAR(100) NOT NULL, " + "operator VARCHAR(50) NOT NULL);";

    private static final String SQL_CREATE_NUMBERS_PHONE_DB = "CREATE TABLE IF NOT EXISTS numbers_phone (" + "phone_number VARCHAR(18) UNIQUE, " + "user_id INTEGER);";

    public DataBase() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            DB_DRIVER_NAME = properties.getProperty("db.driver.name");

            Class.forName(DB_DRIVER_NAME);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            try (PreparedStatement pstmt1 = connection.prepareStatement(SQL_CREATE_USERS_DB); PreparedStatement pstmt2 = connection.prepareStatement(SQL_CREATE_NUMBERS_PHONE_DB)) {
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpId(User user) throws SQLException {
        String sql = "SELECT id FROM users WHERE operator = ? AND name = ?";
        try (PreparedStatement pstmt1 = connection.prepareStatement(sql)) {
            pstmt1.setString(1, user.getOperator());
            pstmt1.setString(2, user.getName());
            ResultSet rs = pstmt1.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, operator) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getOperator());
            pstmt.executeUpdate();
            setUpId(user);
        }

        sql = "INSERT INTO numbers_phone (phone_number, user_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getPhoneNumber());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        setUpId(user);
        String sql = "UPDATE numbers_phone SET phone_number = ? WHERE user_id = ?";
        try (PreparedStatement pstmt1 = connection.prepareStatement(sql)) {
            pstmt1.setString(1, user.getPhoneNumber());
            pstmt1.setInt(2, user.getId());
            pstmt1.executeUpdate();
        }
    }

    public void deleteUser(User user) throws SQLException {
        String sql = "SELECT id FROM users JOIN numbers_phone ON users.id = numbers_phone.user_id WHERE operator = ? AND name = ? AND phone_number = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getOperator());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPhoneNumber());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM users WHERE id = ?";
                try (PreparedStatement pstmt1 = connection.prepareStatement(sql)) {
                    pstmt1.setInt(1, rs.getInt(1));
                    pstmt1.executeUpdate();
                }
                sql = "DELETE FROM numbers_phone WHERE user_id = ?";
                try (PreparedStatement pstmt1 = connection.prepareStatement(sql)) {
                    pstmt1.setInt(1, rs.getInt(1));
                    pstmt1.executeUpdate();
                }
            }
        }
    }

    public ArrayList<User> viewUsers() throws SQLException {
        String sql = "SELECT * FROM users JOIN numbers_phone ON users.id = numbers_phone.user_id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ArrayList<User> users = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    users.add(User.builder().name(rs.getString("name")).phoneNumber(rs.getString("phone_number")).operator(rs.getString("operator")).build());
                }
            }
            return users;
        }
    }
}