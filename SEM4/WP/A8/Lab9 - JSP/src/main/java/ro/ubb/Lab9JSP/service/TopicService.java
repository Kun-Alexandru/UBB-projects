package ro.ubb.Lab9JSP.service;

import ro.ubb.Lab9JSP.database.DatabaseManager;
import ro.ubb.Lab9JSP.model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicService{

    public static List<Topic> getAllTopics()
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from topics";
            statement = connection.prepareStatement(sqlStatement);

            ResultSet resultSet = statement.executeQuery();
            List<Topic> topics = new ArrayList<>();

            while(resultSet.next()) {
                Topic topic = new Topic();
                topic.setId(resultSet.getInt("id"));
                topic.setName(resultSet.getString("name"));
                topic.setUserId(resultSet.getInt("userid"));

                topics.add(topic);
            }
            return topics;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void addTopic(String name, Integer userId)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "insert into topics (name, userid) values (?, ?)";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, name);
            statement.setInt(2, userId);

            statement.execute();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Boolean isTopicNameUnique(String name) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from topics where name = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, name);

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
}
