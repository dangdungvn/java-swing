/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.testdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TestDatabase {

    static Connection connect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/tnj?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static List<NhaXuatBan> getAllNhaXuatBan() {
        List<NhaXuatBan> nxbList = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM nxb")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNXB = rs.getString("mxb");
                String tenNXB = rs.getString("tnxb");
                String ngayXB = rs.getString("nxb");
                String dienThoai = rs.getString("dt");
                String email = rs.getString("em");
                String diaChi = rs.getString("dc");
                String ghiChu = rs.getString("gc");
                NhaXuatBan nxb = new NhaXuatBan(maNXB, tenNXB, ngayXB, dienThoai, email, diaChi, ghiChu);
                nxbList.add(nxb);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return nxbList;
    }

    public static void addNhaXuatBan(String mxb, String tnxb, String nxb, String dt, String em, String dc, String gc) {
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO nxb (mxb, tnxb, nxb, dt, em, dc, gc) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, mxb);
            stmt.setString(2, tnxb);
            stmt.setString(3, nxb);
            stmt.setString(4, dt);
            stmt.setString(5, em);
            stmt.setString(6, dc);
            stmt.setString(7, gc);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void suaNhaXuatBan(String mxb, String tnxb, String nxb, String dt, String em, String dc, String gc) {
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE nxb SET tnxb = ?, nxb = ?, dt = ?, em = ?, dc = ?, gc = ? WHERE mxb = ?");
            stmt.setString(1, tnxb);
            stmt.setString(2, nxb);
            stmt.setString(3, dt);
            stmt.setString(4, em);
            stmt.setString(5, dc);
            stmt.setString(6, gc);
            stmt.setString(7, mxb);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void xoaNhaXuatBan(String mxb) {
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM nxb WHERE mxb = ?");
            stmt.setString(1, mxb);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<NhaXuatBan> timKiemNhaXuatBan(String mxb, String tnxb, String nxb) {
        List<NhaXuatBan> nxbListTk = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `nxb` WHERE mxb LIKE ? AND tnxb LIKE ? AND nxb LIKE ?");
            stmt.setString(1, "%" + mxb + "%");
            stmt.setString(2, "%" + tnxb + "%");
            stmt.setString(3, "%" + nxb + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNXB = rs.getString("mxb");
                String tenNXB = rs.getString("tnxb");
                String ngayXB = rs.getString("nxb");
                String dienThoai = rs.getString("dt");
                String email = rs.getString("em");
                String diaChi = rs.getString("dc");
                String ghiChu = rs.getString("gc");
                NhaXuatBan nxbtk = new NhaXuatBan(maNXB, tenNXB, ngayXB, dienThoai, email, diaChi, ghiChu);
                nxbListTk.add(nxbtk);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return nxbListTk;
    }

    public static void main(String[] args) {
        List<NhaXuatBan> nxbList = getAllNhaXuatBan();
        for (NhaXuatBan nxb : nxbList) {
            System.out.println(nxb.getTenNXB());
        }
        new Main().setVisible(true);
    }
}
