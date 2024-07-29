package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.example.servlets.*;

public class Main {
    private static String hostname;
    private static int port;
    private static final Tomcat tomcat = new Tomcat();

    public static void main(String[] args) {
        try {
            init();
            String appBase = ".";
            tomcat.getHost().setAppBase(appBase);
            Context context = tomcat.addWebapp("/", appBase);
            addHomeServlet(context);
            addUserServlet(context);
            deleteUserServlet(context);
            updateUserServlet(context);
            viewUsersServlet(context);
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));

        hostname = properties.getProperty("server.ip");
        port = Integer.parseInt(properties.getProperty("server.port"));

        tomcat.setPort(port);
        tomcat.setHostname(hostname);
    }

    private static void addHomeServlet(Context context) {
        Tomcat.addServlet(context, "HomeServlet", new HomeServlet());
        context.addServletMapping("/*", "HomeServlet");
    }

    private static void addUserServlet(Context context) {
        Tomcat.addServlet(context, "AddUserServlet", new AddUserServlet());
        context.addServletMapping("/add-user/*", "AddUserServlet");
    }

    private static void deleteUserServlet(Context context) {
        Tomcat.addServlet(context, "DeleteUserServlet", new DeleteUserServlet());
        context.addServletMapping("/delete-user/*", "DeleteUserServlet");
    }

    private static void updateUserServlet(Context context) {
        Tomcat.addServlet(context, "UpdateUserServlet", new UpdateUserServlet());
        context.addServletMapping("/update-user/*", "UpdateUserServlet");
    }

    private static void viewUsersServlet(Context context) {
        Tomcat.addServlet(context, "ViewUsersServlet", new ViewUsersServlet());
        context.addServletMapping("/view-users/*", "ViewUsersServlet");
    }
}