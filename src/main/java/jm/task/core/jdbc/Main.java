package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao ud = new UserDaoJDBCImpl();
        ud.createUsersTable();
        User user1 = new User("Viktor", "Lipin", (byte)34);
        User user2 = new User("Nikita", "Lipin", (byte)9);
        User user3 = new User("Denis", "Lipin", (byte)7);
        ud.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        ud.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        ud.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(ud.getAllUsers());
        ud.removeUserById(2);
        ud.cleanUsersTable();
        ud.dropUsersTable();
    }
}
