package ro.ubb.Lab9JSP.controller;

import ro.ubb.Lab9JSP.model.User;
import ro.ubb.Lab9JSP.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // get the parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check if username and password are valid
        if(!UserService.areCredentialsValid(username, password)) {
            out.println("Error: incorrect credentials");
            return;
        }

        User currentUser = UserService.getUserByUsername(username);

        // create a new session and save the user for the session
        HttpSession session = request.getSession();
        session.setAttribute("userId", currentUser.getId());
        session.setAttribute("username", username);
        response.sendRedirect("homepage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
