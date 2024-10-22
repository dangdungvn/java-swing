package com.noitru;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoan {

    public static boolean addTaiKhoan(String TaiKhoan, String MatKhau, String Email) {
        boolean exists = true;
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO taikhoan (TaiKhoan, MatKhau, Email) "
                + "VALUES (?, ?, ?)")) {
            stmt.setString(1, TaiKhoan);
            stmt.setString(2, MatKhau);
            stmt.setString(3, Email);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            exists = false;
        }
        return exists;
    }

    public static boolean checkTaiKhoan(String Email, String MatKhau) {
        boolean exists = false; // Biến để lưu trạng thái tồn tại

        // Kết nối đến cơ sở dữ liệu
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM taikhoan WHERE Email = ? AND MatKhau = ?")) {
            stmt.setString(1, Email);
            stmt.setString(2, MatKhau);

            // Thực thi truy vấn
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        return exists; // Trả về kết quả
    }
}
