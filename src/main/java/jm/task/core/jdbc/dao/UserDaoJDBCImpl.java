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
    private Statement stat;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try {
            stat = Util.getMyConnect().createStatement();
            stat.executeUpdate("create table if not exists user_table (id INT PRIMARY KEY NOT NULL Auto_Increment, names VARCHAR(30), lastNames VARCHAR(30), ages TINYINT)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            stat = Util.getMyConnect().createStatement();
            ResultSet r = stat.executeQuery("SHOW TABLES FROM vito_base like 'user_table'");
            if (r.next()) {
                stat.executeUpdate("DROP TABLE user_table  ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            ps = Util.getMyConnect().prepareStatement("INSERT INTO user_table (names, lastNames, ages) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try {
            stat = Util.getMyConnect().createStatement();
            rs = stat.executeQuery("SHOW TABLES FROM vito_base like 'user_table'");
            if (rs.next()) {
                ps = Util.getMyConnect().prepareStatement("DELETE FROM user_table WHERE id = ?");
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> myList = new ArrayList<>();
        try {
            stat = Util.getMyConnect().createStatement();
            rs = stat.executeQuery("SELECT * FROM user_table");
            while (rs.next()) {
                myList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return myList;
        }
    }

    public void cleanUsersTable() {
        try {
            stat = Util.getMyConnect().createStatement();
            rs = stat.executeQuery("SHOW TABLES FROM vito_base like 'user_table'");
            if (rs.next()) {
                stat.executeUpdate("DELETE FROM user_table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
