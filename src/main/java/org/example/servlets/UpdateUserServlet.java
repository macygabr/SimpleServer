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

@WebServlet("/update-user")
public class UpdateUserServlet extends HttpServlet {
     private static final DataBase db = DataBase.getDataBase();

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");

         PrintWriter out = response.getWriter();
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Update User</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Update User</h1>");
         out.println("<form method=\"post\" action=\"/update-user\">");

         // Form for User
         out.println("Name: <input type=\"text\" name=\"name\" required><br>");
         out.println("<fieldset>");
         out.println("<legend>New User Information</legend>");
         out.println("Login: <input type=\"text\" name=\"login\" required><br>");
         out.println("Password: <input type=\"text\" name=\"pass\" required><br>");
         out.println("<input type=\"submit\" name=\"action\" value=\"update\">");
         out.println("</fieldset>");

         out.println("</form>");
         out.println("</body>");
         out.println("</html>");
     }

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String name = request.getParameter("name");
         String login = request.getParameter("login");
         String pass = request.getParameter("pass");
         User user = User.builder().name(name).build();
         Account account = Account.builder().login(login).pass(pass).build();

         PrintWriter out = response.getWriter();
         response.setContentType("text/html");
         try {
             db.updateUser(user, account);
             out.println("<html><body>");
             out.println("<h1>User Update Successfully</h1>");
             out.println("<p>Name: " + name + "</p>");
             out.println("</body></html>");
         } catch (Exception e) {
             out.println("<h1>User Update Failed</h1>");
             out.println("<p>" + e.getMessage() + "</p>");
             out.println("</body></html>");
             e.printStackTrace();
         }
     }
}