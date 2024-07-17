package org.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Home</title></head>");
        out.println("<body>");

        out.println("<div>");
        out.println("<ul>");
        out.println("<li><a href=\"/add-user\">Add user</a></li>");
        out.println("<li><a href=\"/update-user\">Update user</a></li>");
        out.println("<li><a href=\"/delete-user\">Delete user</a></li>");
        out.println("<li><a href=\"/view-users\">View users</a></li>");
        out.println("</ul>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}