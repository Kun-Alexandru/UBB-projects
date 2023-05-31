package ro.ubb.Lab9JSP.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ro.ubb.Lab9JSP.model.Comment;
import ro.ubb.Lab9JSP.model.Topic;
import ro.ubb.Lab9JSP.service.CommentService;
import ro.ubb.Lab9JSP.service.TopicService;
import ro.ubb.Lab9JSP.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CommentServlet", value = "/comment-servlet")
public class CommentServlet extends HttpServlet {
    static private Integer currentTopicId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // check if the user is logged
        HttpSession session = request.getSession();
        Integer userIdSession = (Integer) session.getAttribute("userId");
        if(userIdSession == null) {
            out.println("You are not logged in!");
            return;
        }

        String action = request.getParameter("action");
        if(action != null) {
            if(action.equals("setCurrentTopicId")) {
                response.setContentType("text/html");

                Integer topicId = Integer.parseInt(request.getParameter("topicId"));
                currentTopicId = topicId;

                out.println("Topic id = " + currentTopicId);
//            response.sendRedirect("oneTopic.jsp");
            } else if(action.equals("delete")) {
                response.setContentType("text/html");

                Integer commentId = Integer.parseInt(request.getParameter("commentId"));

                // check if the current user (on the session) has the permission to delete the comment
                if(!CommentService.areValidCommentUser(commentId, (Integer) request.getSession().getAttribute("userId"))) {
                    out.println("You cannot delete this comment!");
                    return;
                }

                CommentService.deleteComment(commentId);
            } else if(action.equals("add")) {
                response.setContentType("text/html");

                String description = request.getParameter("description");
                Integer userId = Integer.parseInt(request.getParameter("userId"));
                if(!description.equals("")){
                Comment comment = new Comment();
                comment.setDescription(description);
                comment.setUserId(userId);
                comment.setTopicId(currentTopicId);

                CommentService.addComment(comment);

                out.println("Added comment: description = " + description +
                        " userId = " + userId +
                        " topicId = " + currentTopicId);
                }
                else
                    out.println("Comment invalid");
            }
        } else if(action != null && action.equals("delete")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            out.println("DELETE method not allowed");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        // check if the user is logged
        HttpSession session = request.getSession();
        Integer userIdSession = (Integer) session.getAttribute("userId");
        if(userIdSession == null) {
            out.println("You are not logged in!");
            return;
        }

        String action = request.getParameter("action");
        if(action != null)
        {
            if(action.equals("getAll"))
            {
                response.setContentType("application/json");
                JSONArray jsonArray = new JSONArray();

                if(currentTopicId != null)
                {
                    List<Comment> comments = CommentService.getCommentsByTopicId(currentTopicId);
                    for (int i = 0; i < comments.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", comments.get(i).getId());
                        jsonObject.put("description", comments.get(i).getDescription());
                        jsonObject.put("userid", comments.get(i).getUserId());
                        jsonObject.put("topicid", comments.get(i).getTopicId());

                        String username = UserService.getUserById(comments.get(i).getUserId()).getUsername();
                        jsonObject.put("username", username);

                        jsonArray.add(jsonObject);
                    }
                }

                out.println(jsonArray.toJSONString());
                out.flush();
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // check if the user is logged
        HttpSession session = request.getSession();
        Integer userIdSession = (Integer) session.getAttribute("userId");
        if (userIdSession == null) {
            out.println("You are not logged in!");
            return;
        }


        String action = request.getParameter("action");
        System.out.println(action);
        System.out.println("doDelete method called4");
        response.setContentType("text/html");
        System.out.println("doDelete method called2");
        CommentService.deleteComment(Integer.parseInt(request.getParameter("commentId")));
        System.out.println("doDelete method called3");
        }


}
