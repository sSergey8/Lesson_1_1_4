package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.cleanUsersTable();
        userService.saveUser("Сергей", "Литвиненко", (byte) 33);
        userService.saveUser("Настя", "Яковлева", (byte) 23);
        userService.saveUser("Артём", "Петров", (byte) 28);
        userService.saveUser("Никита", "Барановский", (byte) 45);
        userService.getAllUsers();
        userService.createUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();


        //Util.getConnection(); // тест соединение
    }
}
