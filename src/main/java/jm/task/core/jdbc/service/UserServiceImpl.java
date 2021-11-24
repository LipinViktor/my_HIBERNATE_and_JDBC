package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {}

    UserDao myDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        myDao.createUsersTable();
    }

    public void dropUsersTable() {
        myDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        myDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        myDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return myDao.getAllUsers();
    }

    public void cleanUsersTable() {
        myDao.cleanUsersTable();
    }
}
