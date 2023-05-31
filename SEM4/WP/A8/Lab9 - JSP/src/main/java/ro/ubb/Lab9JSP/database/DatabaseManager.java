package ro.ubb.Lab9JSP.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    static final String DbUser = "root";
    static final String DbPassword = "";

    public static void connect() {
        if(connection == null) {
            String url = "jdbc:mysql://localhost:3306/forum2";
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, DbUser, DbPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public static Connection getConnection() {
        if(connection == null)
            connect();
        return connection;
    }
}
