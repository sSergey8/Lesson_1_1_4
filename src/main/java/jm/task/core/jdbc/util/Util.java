package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/users";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "4848801";
    private static Connection connection;

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
            System.out.println("Подключение к БД успешно закрыто!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка закрытия соединения с БД!!!");
        }
    }
}
