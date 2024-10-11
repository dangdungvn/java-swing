package com.noitru.component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.noitru.BenhNhan;
import com.noitru.ConnectDB;

public class CheckLoi {

    static Connection conn;

    public static boolean checkKey(String MaBN, String tenDB) {
        boolean kq = false;
        String ma = null;
        if (tenDB == "benhnhan") {
            ma = "MaBN";
        } else if (tenDB == "bacsi") {
            ma = "MaBS";
        }
        try {
            conn = ConnectDB.connect();
            String sql = "SELECT * FROM `" + tenDB + "` Where " + ma + " = ('" + MaBN + "') ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                kq = true;
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
        return kq;
    }

    public static String checkDT(String dt) {
        String regex = "^(\\+84|0)[0-9]{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dt);
        if (!m.matches()) {
            return null;
        }
        return dt;
    }
}
