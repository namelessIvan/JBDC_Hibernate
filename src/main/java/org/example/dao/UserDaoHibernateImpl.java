package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static org.example.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getSessionFactory().openSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";
            session.beginTransaction();
            session.createNativeQuery(sql);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getSessionFactory().openSession()){
            String sql = "DROP TABLE USERS";
            session.beginTransaction();
            session.createNativeQuery(sql);
            session.getTransaction().commit();
        } catch (Exception e){
            System.out.println("Ошибка удаления таблицы");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){
            System.out.println("Ошибка сохранения пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete User where id = :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Session session = sessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            userList = session.createQuery("from User order by name").list();
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getSessionFactory().openSession()){
            String sql = "delete User";
            session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }

    }
}
