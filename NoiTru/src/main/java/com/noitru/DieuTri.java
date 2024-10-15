package com.noitru;

import com.noitru.model.Model_DieuTri;
import com.noitru.model.Model_ThongTinKhamBenh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DieuTri {

    private static Model_DieuTri mapResultSetToThongTinKhamBenh(ResultSet rs) throws SQLException {
        String MaBN = rs.getString("MaBN");
        int SoNgayNhapVien = rs.getInt("SoNgayNhapVien");
        String Ngay = rs.getString("Ngay");
        String Thuoc = rs.getString("Thuoc");
        int SoLuongThuoc = rs.getInt("SoLuongThuoc");
        int ThanhTien = rs.getInt("ThanhTien");
        return new Model_DieuTri(MaBN, SoNgayNhapVien, Ngay, Thuoc, SoLuongThuoc, ThanhTien);
    }

    public static List<Model_DieuTri> getAllThongTin() {
        List<Model_DieuTri> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement("SELECT * FROM `dieutri`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_DieuTri benhNhan = mapResultSetToThongTinKhamBenh(rs);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanList;
    }

    public static List<Model_DieuTri> timKiemTheoMaBN(String MaBN) {
        List<Model_DieuTri> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dieutri WHERE MaBN = ?");
            stmt.setString(1, MaBN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_DieuTri benhNhan = mapResultSetToThongTinKhamBenh(rs);
                benhNhanListTk.add(benhNhan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static void addThongTin(String MaBN, int SoNgayNhapVien, String Ngay, String Thuoc, int SoLuongThuoc, int ThanhTien) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement(
                        "INSERT INTO `dieutri` (MaBN, SoNgayNhapVien, Ngay, Thuoc, SoLuongThuoc, ThanhTien) "
                        + "VALUES (?,?,?,?,?,?)")) {
            stmt.setString(1, MaBN);
            stmt.setInt(2, SoNgayNhapVien);
            stmt.setString(3, Ngay);
            stmt.setString(4, Thuoc);
            stmt.setInt(5, SoLuongThuoc);
            stmt.setInt(6, ThanhTien);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
