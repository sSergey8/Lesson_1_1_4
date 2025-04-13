package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/users";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "4848801";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Подключение к БД прошло успешно!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("При подключении к БД произошла ОШИБКА!!!");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Подключение к БД закрыто!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка закрытия соединения с БД!!!");
        }
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", DB_URL);
                settings.put("hibernate.connection.username", DB_USERNAME);
                settings.put("hibernate.connection.password", DB_PASSWORD);
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.format_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");

                Configuration configuration = new Configuration();
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                System.out.println("Hibernate SessionFactory создана!");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Ошибка при создании SessionFactory!");
            }
        }
        return sessionFactory;
    }
}
