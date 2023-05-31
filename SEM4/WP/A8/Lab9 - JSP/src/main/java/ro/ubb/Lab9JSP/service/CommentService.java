package ro.ubb.Lab9JSP.service;

import ro.ubb.Lab9JSP.database.DatabaseManager;
import ro.ubb.Lab9JSP.model.Comment;
import ro.ubb.Lab9JSP.model.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

    public static List<Comment> getCommentsByTopicId(Integer topicId)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select * from comments where topicid = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, topicId);

            ResultSet resultSet = statement.executeQuery();
            List<Comment> comments = new ArrayList<>();

            while(resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setDescription(resultSet.getString("description"));
                comment.setUserId(resultSet.getInt("userid"));
                comment.setTopicId(resultSet.getInt("topicid"));

                comments.add(comment);
            }
            return comments;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void addComment(Comment comment)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "insert into comments (description, topicid, userid) values (?, ?, ?)";
            statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, comment.getDescription());
            statement.setInt(2, comment.getTopicId());
            statement.setInt(3, comment.getUserId());

            statement.execute();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteComment(Integer commentId)
    {
        System.out.println("heheStart");
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();
            System.out.println("hehe");
            String sqlStatement = "delete from comments where id = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, commentId);

            statement.execute();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Boolean areValidCommentUser(Integer commentId, Integer userId) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseManager.getConnection();

            String sqlStatement = "select userid from comments where id = ?";
            statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, commentId);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Integer dbUserId = resultSet.getInt("userid");
                if(dbUserId.equals(userId))
                    return true;
            }
            return false;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
