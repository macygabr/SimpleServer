package org.example.database;

import java.sql.SQLException;

import org.example.models.Accounts.Account;
import org.hibernate.query.Query;
import org.example.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class DataBase {
    private static DataBase db;
    private static SessionFactory sessionFactory;
    private DataBase() {
        sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(User.class)
                            .addAnnotatedClass(Course.class)
                            .addAnnotatedClass(Account.class)
                            .buildSessionFactory();
    }

    public static DataBase getDataBase() {
        if(db == null) {
            db = new DataBase();
        }
        return db;
    }

    public void addUser(User user, Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            account.setUser(user);
            session.save(account);
            session.getTransaction().commit();
        }
    }

    public void addCourses(Account account, Course course) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Account existingAccount = session.createQuery("FROM Account WHERE login = :login", Account.class)
                    .setParameter("login", account.getLogin())
                    .uniqueResult();

            if (existingAccount == null) {
                throw new SQLException("Account not found with login: " + account.getLogin());
            }

            existingAccount.getCourses().add(course);
            session.save(existingAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error adding course to account", e);
        }
    }

     public void updateUser(User user, Account new_account) throws SQLException {
         try (Session session = sessionFactory.openSession()) {
             User existingUser = session.createQuery("FROM User WHERE name = :name", User.class)
                     .setParameter("name", user.getName())
                     .uniqueResult();

             if (existingUser == null) {
                 throw new SQLException("User not found with name: " + user.getName());
             }
             Account existingAccount = existingUser.getAccount();
             if (existingAccount == null) {
                 throw new SQLException("Account not found for user with name: " + user.getName());
             }
             session.beginTransaction();
             existingAccount.setLogin(new_account.getLogin());
             existingAccount.setPass(new_account.getPass());
             session.update(existingAccount);
             existingUser.setAccount(existingAccount);
             session.update(existingUser);
             session.getTransaction().commit();
         } catch (Exception e) {
             throw new SQLException("Error updating user and account: " + e.getMessage(), e);
         }
     }

    public void deleteUser(User user) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User new_user = session.createQuery("FROM User WHERE name = :name", User.class)
                    .setParameter("name", user.getName())
                    .uniqueResult();

            if (new_user == null) {
                throw new SQLException("User not found with name: " + user.getName());
            }

            session.delete(new_user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error deleting user: " + e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.account a LEFT JOIN FETCH a.courses", User.class);
            return query.getResultList();
        }
    }
}