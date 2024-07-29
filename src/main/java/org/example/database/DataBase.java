package org.example.database;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public void addUser(User user, Account account) throws SQLException {
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

    // public void updateUser(User new_user, Phone phone) throws SQLException {
    //     try (Session session = sessionFactory.openSession()) {
    //         session.beginTransaction();
    //         User user = getUserByName(new_user.getName());
    //         user.setOperator(new_user.getOperator());
    //         Phone ph = getPhoneByUserId(user.getId());
    //         ph.setPhoneNumber(phone.getPhoneNumber());
    //         session.update(user);
    //         session.update(ph);
    //         session.getTransaction().commit();
    //     } catch (Exception e) {
    //         throw new SQLException("Error getting user: " + e.getMessage(), e);
    //     }
    // }

    public void deleteUser(User user) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User new_user = session.createQuery("FROM User WHERE account.login = :login", User.class)
                    .setParameter("login", user.getAccount().getLogin())
                    .uniqueResult();

            if (new_user == null) {
                throw new SQLException("User not found with login: " + user.getAccount().getLogin());
            }

            session.delete(new_user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error deleting user: " + e.getMessage(), e);
        }
    }

     public List<User> getAllUsers() throws SQLException {
         try (Session session = sessionFactory.openSession()) {
             Query<User> query = session.createQuery("FROM User", User.class);
             return query.getResultList();
         }
     }

    // public User getUserByName(String name) throws SQLException {
    //     try (Session session = sessionFactory.openSession()) {
    //         Query<User> query = session.createQuery("FROM User WHERE name = :name", User.class);
    //         query.setParameter("name", name);
    //         return query.uniqueResult();
    //     } catch (Exception e) {
    //         throw new SQLException("Error getting user by name: " + e.getMessage());
    //     }
    // }
    
    
    // public Phone getPhoneByUserId(Long user_id) throws SQLException {
    //     try (Session session = sessionFactory.openSession()) {
    //         Query<Phone> query = session.createQuery("FROM Phone WHERE user_id = :user_id", Phone.class);
    //         query.setParameter("user_id", user_id);
    //         return query.uniqueResult();
    //     } catch (Exception e) {
    //         throw new SQLException("Error getting phone by name: " + e.getMessage());
    //     }
    // }
}