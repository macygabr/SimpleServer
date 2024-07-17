package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.database.DataBase;
import org.example.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {
    private static final DataBase db = new DataBase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Add User</h1>");
        out.println("<form method=\"post\">");
        out.println("Name: <input type=\"text\" name=\"name\"><br>");
        out.println("Phone Number: <input type=\"text\" name=\"phoneNumber\"><br>");
        out.println("Operator: <input type=\"text\" name=\"operator\"><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String operator = request.getParameter("operator");
        User user = User.builder().name(name).phoneNumber(phoneNumber).operator(operator).build();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            db.addUser(user);
            out.println("<html><body>");
            out.println("<h1>User Added Successfully</h1>");
            out.println("<p>Name: " + name + "</p>");
            out.println("<p>Phone Number: " + phoneNumber + "</p>");
            out.println("<p>Operator: " + operator + "</p>");
            out.println("</body></html>");
        } catch (SQLException e) {
            out.println("<h1>User Added Failed</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
    }
}