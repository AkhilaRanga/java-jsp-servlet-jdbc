package com.learn.javaweb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/java_web";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
