package org.example.dao;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static org.example.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = getConnection();
            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println("Ошибка создания табл");
                e.printStackTrace();
            }
    }

    public void dropUsersTable() {
        Connection connection = getConnection();
        try (Statement stmt = connection.createStatement()){
            String dropTable = "DROP TABLE USERS";
            stmt.executeUpdate(dropTable);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();
            String saveSql = "INSERT INTO USERS (name, lastname, age) VALUES(?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(saveSql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения пользователя");
        }

    }

    public void removeUserById(long id) {
        Connection connection = getConnection();
        try (Statement stmt = connection.createStatement()){
            String removeById = "DELETE from USERS WHERE id IN (?)";
            stmt.executeUpdate(removeById);
        }catch (SQLException e) {
            System.out.println("Ошибка удаления по id");
        }
    }

    public List<User> getAllUsers() {
        Connection connection = getConnection();
        List<User> users = new ArrayList<>();
        String selectSql = "SELECT id, name, lastname, age FROM USERS";
        try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(selectSql)){
                while (resultSet.next()){
                   Long id = resultSet.getLong("id");
                   String name = resultSet.getString("name");
                   String lastname= resultSet.getString("lastname");
                   byte age = resultSet.getByte("age");
                   User user = new User(id, name, lastname, age);
                   users.add(user);
                }
        } catch (SQLException e) {
            System.out.println("Ошибка удаления по id");
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = getConnection();
        String cleanTable = "DELETE FROM Users";
        try (PreparedStatement pstmt = connection.prepareStatement(cleanTable);){
            pstmt.executeUpdate(cleanTable);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы");
        }

    }
}


