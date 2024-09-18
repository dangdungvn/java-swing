/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Database {

    static Connection connect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/tnj?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static void main(String[] args) {
        String text1 = "Hello Java regex 2-12-2018";
        Pattern pattern = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
        Matcher matcher = pattern.matcher(text1);
        System.out.println(text1);
        while (matcher.find()) {
            System.out.println(text1.substring(matcher.start(), matcher.end()));
        }
    }
}
