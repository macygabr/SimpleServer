package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.models.User;
import org.example.database.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/view-users")
public class ViewUsersServlet extends HttpServlet {
    private static final DataBase db = new DataBase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        ArrayList<User> users = new ArrayList<>();
        try {
            users = db.viewUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("<html><body>");
        out.println("<h1>View Users</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Name</th><th>Phone Number</th><th>Operator</th></tr>");
        for (User user : users) {
            out.println("<tr>");
            out.println("<td>" + user.getName() + "</td>");
            out.println("<td>" + user.getPhoneNumber() + "</td>");
            out.println("<td>" + user.getOperator() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}