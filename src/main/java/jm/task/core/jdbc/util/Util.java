package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД

    public static final String URL = "jdbc:mysql://localhost:3306/mukashema";
    public static final String USER = "root";
    public static final String PASS = "mukagali";

    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            Logger.getLogger("Method getConnection() false: ").log(Level.WARNING, "Во время " +
                    "выполнения коннекта с БД произошла ошибка.");
        }
        return connection;
    }

}
