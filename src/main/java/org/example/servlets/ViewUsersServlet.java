package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.models.*;
import org.example.database.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/view-users")
public class ViewUsersServlet extends HttpServlet {
     private static final DataBase db = DataBase.getDataBase();

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         try {
            List<User> users = db.getAllUsers();
            out.println("<html><body>");
            out.println("<h1>View Users</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>User</th><th>Login</th><th>Courses</th></tr>");
            for (User user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getName() + "</td>");
                out.println("<td>" +user.getAccount().getLogin() + "</td>");
                out.println("<td>" + user.getAccount().getCourses() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
             out.println("<h1>View Users Failed</h1>");
             out.println("<p>" + e.getMessage() + "</p>");
             out.println("</body></html>");
             e.printStackTrace();
        }
     }
}