package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query qr = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age TINYINT)").addEntity(User.class);
            qr.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query qr = session.createSQLQuery("DROP TABLE IF EXISTS user").addEntity(User.class);
            qr.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, (byte)age);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            System.out.println("Юзер создан ...");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        User us = Util.getSessionFactory().openSession().get(User.class, id);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(us);
            System.out.printf("Юзер с id : %s удален ...", id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
        Query qr = session.createQuery("FROM User" );
            List<User> myList = (List<User>) qr.list();
            return myList;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            System.out.println("Таблица отчищена... ");
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
