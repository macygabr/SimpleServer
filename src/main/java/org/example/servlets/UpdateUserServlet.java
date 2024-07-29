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
    // private static final DataBase db = DataBase.getDataBase();

    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     response.setContentType("text/html");

    //     PrintWriter out = response.getWriter();
    //     out.println("<html><body>");
    //     out.println("<h1>Update User</h1>");
    //     out.println("<form method=\"post\">");
    //     out.println("Name: <input type=\"text\" name=\"name\"><br>");
    //     out.println("Phone Number: <input type=\"text\" name=\"phoneNumber\"><br>");
    //     out.println("Operator: <input type=\"text\" name=\"operator\"><br>");
    //     out.println("<input type=\"submit\" value=\"Submit\">");
    //     out.println("</form>");
    //     out.println("</body></html>");
    // }

    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    String name = request.getParameter("name");
    //    String phoneNumber = request.getParameter("phoneNumber");
    //    String operator = request.getParameter("operator");
    //    Users user = new Users();
    //    Phone phone = new Phone();
    //    user.setName(name);
    //    user.setOperator(operator);
    //    phone.setPhoneNumber(phoneNumber);

    //    response.setContentType("text/html");
    //    PrintWriter out = response.getWriter();

    //    try {
    //        db.updateUser(user, phone);
    //        out.println("<html><body>");
    //        out.println("<h1>User Updated Successfully</h1>");
    //        out.println("<p>Name: " + name + "</p>");
    //        out.println("<p>Phone Number: " + phoneNumber + "</p>");
    //        out.println("<p>Operator: " + operator + "</p>");
    //        out.println("</body></html>");
    //    } catch (Exception e) {
    //        out.println("<h1>User Updated Failed</h1>");
    //        out.println("<p>" + e.getMessage() + "</p>");
    //        out.println("</body></html>");
    //        e.printStackTrace();
    //    }
    // }
}