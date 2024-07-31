package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.database.DataBase;

import org.example.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
    private static final DataBase db = DataBase.getDataBase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add User and Course</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Add User and Course</h1>");
        out.println("<form method=\"post\" action=\"/add-user\">");

        // Form for User
        out.println("<fieldset>");
        out.println("<legend>User Information</legend>");
        out.println("Name: <input type=\"text\" name=\"name\" required><br>");
        out.println("Login: <input type=\"text\" name=\"login\" required><br>");
        out.println("Password: <input type=\"text\" name=\"pass\" required><br>");
        out.println("<input type=\"submit\" name=\"action\" value=\"addUser\">");
        out.println("</fieldset>");

        // Form for Course
        out.println("<fieldset>");
        out.println("<legend>Course Information</legend>");
        out.println("Course: <input type=\"text\" name=\"course\"><br>");
        out.println("<input type=\"submit\" name=\"action\" value=\"addCourse\">");
        out.println("</fieldset>");

        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("addUser".equals(action)) {
            addUser(request, response);
        } else if ("addCourse".equals(action)) {
            addCourse(request, response);
        } else {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h1>Unknown Action</h1>");
            out.println("<p>The action parameter was not recognized.</p>");
            out.println("</body></html>");
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        User user = new User(name);
//        PrimeAccount account = new PrimeAccount(login, pass);
//        account.setBalance(199L);
        Account account = new Account(login, pass);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            db.addUser(user, account);
            out.println("<html><body>");
            out.println("<h1>User Added Successfully</h1>");
            out.println("<p>Name: " + name + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<h1>User Added Failed</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String course_name = request.getParameter("course");

        Account account = new Account(login, pass);
        Course course = new Course(course_name);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            db.addCourses(account, course);
            out.println("<html><body>");
            out.println("<h1>User Added Successfully</h1>");
            out.println("<p>Name: " + name + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<h1>User Added Failed</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }
}