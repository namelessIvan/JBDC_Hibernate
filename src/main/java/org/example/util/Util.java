package org.example.util;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root1";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось установить соединение с базой данных: " + e.getMessage(), e);
        }
    }

        public static SessionFactory sessionFactory = buildSessionFactory();

        public static SessionFactory buildSessionFactory() {
            try {
                Properties prop = new Properties();
                prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydbtest");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.connection.username", "root1");
                prop.setProperty("hibernate.connection.password", "root1");
                prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                return configuration.addProperties(prop).buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        public static void shutdown() {
            getSessionFactory().close();
        }

}

