/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.nhanvien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class NhanVien {

    static Connection connect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/csdl?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
