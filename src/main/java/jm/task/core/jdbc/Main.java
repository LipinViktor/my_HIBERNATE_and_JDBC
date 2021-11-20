package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
         User us1 = new User("Viktor", "Lipin", (byte)34);
         User us2 = new User("Nikita", "Lipin", (byte)9);
         User us3 = new User("Denis", "Lipin", (byte)7);
         User us4 = new User("Anna", "Lipina", (byte)33);
         UserDao ud = new UserDaoJDBCImpl();
         ud.createUsersTable();
         ud.saveUser(us1.getName(), us1.getLastName(), us1.getAge());
         ud.saveUser(us2.getName(), us2.getLastName(), us2.getAge());
         ud.saveUser(us3.getName(), us3.getLastName(), us3.getAge());
         ud.saveUser(us4.getName(), us4.getLastName(), us4.getAge());
         ud.getAllUsers();
         ud.cleanUsersTable();
         ud.dropUsersTable();

    }
}
