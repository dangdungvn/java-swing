package com.noitru;

import com.noitru.model.Model_Thuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaThuoc {

    public static List<Model_Thuoc> timKiemTheoMaBN(String Thuoc) {
        List<Model_Thuoc> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM giathuoc WHERE Thuoc = ?");
            stmt.setString(1, Thuoc);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_Thuoc benhNhan = mapResultSetToGiaThuoc(rs);
                benhNhanListTk.add(benhNhan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static List<Model_Thuoc> getAllThongTin() {
        List<Model_Thuoc> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement("SELECT * FROM `giathuoc`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_Thuoc benhNhan = mapResultSetToGiaThuoc(rs);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanList;
    }

    private static Model_Thuoc mapResultSetToGiaThuoc(ResultSet rs) throws SQLException {
        String MaBN = rs.getString("Thuoc");
        float GiaTien = rs.getFloat("GiaTien");
        return new Model_Thuoc(MaBN, GiaTien);
    }
}
