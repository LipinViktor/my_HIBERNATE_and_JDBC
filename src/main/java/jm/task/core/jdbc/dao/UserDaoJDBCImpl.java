package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Statement stat = Util.getMyConnect().createStatement()) {
            stat.executeUpdate("create table if not exists user_table (id BIGINT PRIMARY KEY NOT NULL Auto_Increment, names VARCHAR(30), lastNames VARCHAR(30), ages TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stat = Util.getMyConnect().createStatement()) {
            stat.executeUpdate("DROP TABLE IF EXISTS user_table  ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = Util.getMyConnect().prepareStatement("INSERT INTO user_table (names, lastNames, ages) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement stat = Util.getMyConnect().createStatement()) {
            try (PreparedStatement ps = Util.getMyConnect().prepareStatement("DELETE FROM user_table WHERE id = ?")) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> myList = new ArrayList<>();
        try (Statement stat = Util.getMyConnect().createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM user_table");
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getLong(1));
                us.setName(rs.getString(2));
                us.setLastName(rs.getString(3));
                us.setAge(rs.getByte(4));
                myList.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    public void cleanUsersTable() {
        try (Statement stat = Util.getMyConnect().createStatement()) {
            stat.executeUpdate("DELETE FROM user_table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
