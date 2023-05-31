package ro.ubb.Lab9JSP.controller;

import ro.ubb.Lab9JSP.model.Topic;
import ro.ubb.Lab9JSP.service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "TopicServlet", value = "/topic-servlet")
public class TopicServlet extends HttpServlet {

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
        if(action != null)
        {
            if(action.equals("add"))
            {
                String topicName = request.getParameter("name");
                Integer userId = Integer.parseInt(request.getParameter("userId"));
                if(TopicService.isTopicNameUnique(topicName)) {
                    if(!topicName.equals("")) {
                        TopicService.addTopic(topicName, userId);
                        out.println("Added topic: name = " + topicName + " userId = " + userId);
                    }
                    else
                        out.println("Topic invalid");
                }
                else
                    out.println("Topic already exists");
            }
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

                List<Topic> topics = TopicService.getAllTopics();
                JSONArray jsonArray = new JSONArray();
                for(int i = 0; i < topics.size(); i++)
                {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", topics.get(i).getId());
                    jsonObject.put("name", topics.get(i).getName());
                    jsonObject.put("userid", topics.get(i).getUserId());

                    jsonArray.add(jsonObject);
                }
                out.println(jsonArray.toJSONString());
                out.flush();
            }
        }
    }
}
