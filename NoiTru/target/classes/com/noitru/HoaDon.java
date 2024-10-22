package com.noitru;

import com.noitru.model.Model_HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {

    private static Model_HoaDon mapResultSetToHoaDon(ResultSet rs) throws SQLException {
        String Thuoc = rs.getString("TenThuoc");
        int SoLuongThuoc = rs.getInt("SoLuong");
        float GiaTien = rs.getFloat("GiaTien");
        int ThanhTien = rs.getInt("TongTien");
        return new Model_HoaDon(Thuoc, SoLuongThuoc, GiaTien, ThanhTien);
    }

    public static List<Model_HoaDon> timKiemTheoMaBN(String MaBN) {
        List<Model_HoaDon> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            String sql = "SELECT dieutri.Thuoc AS TenThuoc, dieutri.SoLuongThuoc AS SoLuong, "
                    + "giathuoc.GiaTien, (dieutri.SoLuongThuoc * giathuoc.GiaTien) AS TongTien "
                    + "FROM dieutri "
                    + "JOIN giathuoc ON dieutri.Thuoc = giathuoc.Thuoc "
                    + "WHERE dieutri.MaBN = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, MaBN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToHoaDon(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }
}
