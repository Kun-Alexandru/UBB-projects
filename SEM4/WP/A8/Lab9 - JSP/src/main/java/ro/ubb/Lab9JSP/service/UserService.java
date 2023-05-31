package ro.ubb.Lab9JSP.service;

import ro.ubb.Lab9JSP.database.DatabaseManager;
import ro.ubb.Lab9JSP.model.User;

import java.sql.*;

public class UserService {

    public static User getUserByUsername(String username)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from users where username = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            Integer id = resultSet.getInt("userid");
            String password = resultSet.getString("userpassword");
            User user = new User(username, password);
            user.setId(id);

            resultSet.close();

            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static User getUserById(Integer userId)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from users where userid = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            String username = resultSet.getString("username");
            String password = resultSet.getString("userpassword");
            User user = new User(username, password);
            user.setId(userId);

            resultSet.close();

            return user;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void addUser(String username, String password)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "insert into users (username, userpassword) values (?, ?)";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, username);
            statement.setString(2, password);

            statement.execute();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Boolean isUsernameUnique(String username) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from Users where username = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return false;
            }
            return true;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean areCredentialsValid(String username, String password) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from Users where username = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) {
                return false;
            }

            String dbPassword = resultSet.getString("userpassword");
            if(password.equals(dbPassword))
                return true;

            resultSet.close();

            return false;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
