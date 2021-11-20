package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public Util() {
    }

    public static Connection getMyConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/vito_base", "root", "2175");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return con;
        }

    }
}

