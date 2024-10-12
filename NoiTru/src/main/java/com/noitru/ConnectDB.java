package com.noitru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/benhvien?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
