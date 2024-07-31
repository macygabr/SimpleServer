package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.database.DataBase;
import org.example.models.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
     private static final DataBase db = DataBase.getDataBase();

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");

         PrintWriter out = response.getWriter();
         out.println("<html><body>");
         out.println("<h1>Deleted User</h1>");
         out.println("<form method=\"post\">");
         out.println("Name: <input type=\"text\" name=\"name\"><br>");
         out.println("<input type=\"submit\" value=\"Submit\">");
         out.println("</form>");
         out.println("</body></html>");
     }

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        User user = new User(name);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            db.deleteUser(user);
            out.println("<html><body>");
            out.println("<h1>User Deleted Successfully</h1>");
            out.println("<p>Name: " + name + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<h1>User Deleted Failed</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
            e.printStackTrace();
        }
     }
}