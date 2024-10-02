package com.noitru;

import com.noitru.model.Model_BenhNhan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoiTru {

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
        System.out.println("Hello World!");
    }

    public static List<Model_BenhNhan> getAllBenhNhan() {
        List<Model_BenhNhan> benhNhanList = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String MaBN = rs.getString("MaBN");
                String TenBN = rs.getString("TenBN");
                String NgaySinh = rs.getString("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String CCCD = rs.getString("CCCD");
                String DiaChi = rs.getString("DiaChi");
                String BHYT = rs.getString("BHYT");
                String DienThoai = rs.getString("DienThoai");
                Model_BenhNhan benhNhan = new Model_BenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT, DienThoai);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanList;
    }

    public static void suaBenhNhan(String MaBN, String TenBN, String NgaySinh, String GioiTinh, String CCCD, String DiaChi, String BHYT, String DienThoai) {
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE benhnhan SET TenBN = ?, NgaySinh = ?, GioiTinh = ?, CCCD = ?, DiaChi = ?, BHYT = ?, DienThoai = ? WHERE MaBN = ?");
            stmt.setString(1, TenBN);
            stmt.setString(2, NgaySinh);
            stmt.setString(3, GioiTinh);
            stmt.setString(4, CCCD);
            stmt.setString(5, DiaChi);
            stmt.setString(6, BHYT);
            stmt.setString(7, DienThoai);
            stmt.setString(8, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Model_BenhNhan mapResultSetToBenhNhan(ResultSet rs) throws SQLException {
        String Ma = rs.getString("MaBN");
        String Ten = rs.getString("TenBN");
        String Ngay = rs.getString("NgaySinh");
        String GioiTinh = rs.getString("GioiTinh");
        String CCCD = rs.getString("CCCD");
        String BHYT = rs.getString("BHYT");
        String DiaChi = rs.getString("DiaChi");
        String DienThoai = rs.getString("DienThoai");
        return new Model_BenhNhan(Ma, Ten, Ngay, GioiTinh, CCCD, BHYT, DiaChi, DienThoai);
    }

    public static List<Model_BenhNhan> timKiemTheoMaBN(String MaBN) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE MaBN LIKE ?");
            stmt.setString(1, "%" + MaBN + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static List<Model_BenhNhan> timKiemTheoTenBN(String TenBN) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE TenBN LIKE ?");
            stmt.setString(1, "%" + TenBN + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static List<Model_BenhNhan> timKiemTheoNgaySinh(String NgaySinh) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE NgaySinh LIKE ?");
            stmt.setString(1, "%" + NgaySinh + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static void addBenhNhan(String MaBN, String TenBN, String NgaySinh, String GioiTinh, String CCCD, String DiaChi, String BHYT, String DienThoai) {
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("INSERT INTO benhnhan (MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT, DienThoai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, MaBN);
            stmt.setString(2, TenBN);
            stmt.setString(3, NgaySinh);
            stmt.setString(4, GioiTinh);
            stmt.setString(5, CCCD);
            stmt.setString(6, DiaChi);
            stmt.setString(7, BHYT);
            stmt.setString(8, DienThoai);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
