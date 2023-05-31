package ro.ubb.Lab9JSP.controller;

import ro.ubb.Lab9JSP.model.User;
import ro.ubb.Lab9JSP.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {

    public void init() {}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // get the parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        // check if passwords are equal
        if(!password.equals(repeatPassword)) {
            out.println("Error: incorrect repeated password");
            return;
        }

        // check if username is unique
        if(!UserService.isUsernameUnique(username)) {
            out.println("Error: username already exists");
            return;
        }

        // Save the password to database
        UserService.addUser(username, password);
        User addedUser = UserService.getUserByUsername(username);

        // create a new session and save the user for the session
        HttpSession session = request.getSession();
        session.setAttribute("userId", addedUser.getId());
        session.setAttribute("username", username);
        response.sendRedirect("homepage.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {}
}
