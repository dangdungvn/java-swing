package com.noitru;

import com.noitru.model.Model_BacSi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BacSi {

    public static List<Model_BacSi> getBacSiByChuyenKhoa(String chuyenKhoaCb) {
        List<Model_BacSi> bacSiList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `bacsi` WHERE ChuyenKhoa = ?")) {
            stmt.setString(1, chuyenKhoaCb);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String MaBS = rs.getString("MaBS");
                String TenBS = rs.getString("TenBS");
                String KinhNghiem = rs.getString("KinhNghiem");
                String ChuyenKhoa = rs.getString("ChuyenKhoa");
                Model_BacSi bacSi = new Model_BacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                bacSiList.add(bacSi);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return bacSiList;
    }

    private static Model_BacSi mapResultSetToBacSi(ResultSet rs) throws SQLException {
        String Ma = rs.getString("MaBS");
        String Ten = rs.getString("TenBS");
        String KinhNghiem = rs.getString("KinhNghiem");
        String ChuyenKhoa = rs.getString("ChuyenKhoa");
        return new Model_BacSi(Ma, Ten, KinhNghiem, ChuyenKhoa);
    }

    public static List<Model_BacSi> timKiemTheoMaBS(String MaBS) {
        List<Model_BacSi> bacSiListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `bacsi` WHERE MaBS LIKE ?");
            stmt.setString(1, "%" + MaBS + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bacSiListTk.add(mapResultSetToBacSi(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return bacSiListTk;
    }

    public static List<Model_BacSi> timKiemTheoTenBS(String TenBS) {
        List<Model_BacSi> bacSiListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `bacsi` WHERE TenBS LIKE ?");
            stmt.setString(1, "%" + TenBS + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bacSiListTk.add(mapResultSetToBacSi(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return bacSiListTk;
    }

    public static void xoaBacSi(String MaBS) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM `bacsi` WHERE MaBS = ?")) {
            stmt.setString(1, MaBS);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static List<String> getAllChuyenKhoa() {
        List<String> chuyenKhoaList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT ChuyenKhoa FROM `chuyenkhoa` GROUP BY ChuyenKhoa")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ChuyenKhoa = rs.getString("ChuyenKhoa");
                chuyenKhoaList.add(ChuyenKhoa);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return chuyenKhoaList;
    }

    public static List<Model_BacSi> getAllBacSi() {
        List<Model_BacSi> bacSiList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `bacsi`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String MaBS = rs.getString("MaBS");
                String TenBS = rs.getString("TenBS");
                String KinhNghiem = rs.getString("KinhNghiem");
                String ChuyenKhoa = rs.getString("ChuyenKhoa");
                Model_BacSi bacSi = new Model_BacSi(MaBS, TenBS, KinhNghiem, ChuyenKhoa);
                bacSiList.add(bacSi);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bacSiList;
    }

    public static void addBacSi(String MaBS, String TenBS, String KinhNghiem, String ChuyenKhoa) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO bacsi (MaBS, TenBS, KinhNghiem, ChuyenKhoa) "
                + "VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, MaBS);
            stmt.setString(2, TenBS);
            stmt.setString(3, KinhNghiem);
            stmt.setString(4, ChuyenKhoa);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void suaBacSi(String MaBS, String TenBS, String KinhNghiem, String ChuyenKhoa) {
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE bacsi SET TenBS = ?, KinhNghiem = ?, ChuyenKhoa = ? WHERE MaBS = ?");
            stmt.setString(1, TenBS);
            stmt.setString(2, KinhNghiem);
            stmt.setString(3, ChuyenKhoa);
            stmt.setString(4, MaBS);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
