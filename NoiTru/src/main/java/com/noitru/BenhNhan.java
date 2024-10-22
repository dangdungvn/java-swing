package com.noitru;

import com.noitru.model.Model_BenhNhan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenhNhan {

    public static Map<String, Integer> getBenhNhanTheoDiaChi() {
        Map<String, Integer> statistics = new HashMap<>();

        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT DiaChi, COUNT(*) as SoLuong "
                + "FROM benhnhan "
                + "GROUP BY DiaChi "
                + "ORDER BY SoLuong")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String diaChi = rs.getString("DiaChi");
                int soLuong = rs.getInt("SoLuong");
                statistics.put(diaChi, soLuong);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return statistics;
    }

    public static double[] demSoBenhNhanTheoTinhTrang(int thang) {
        double[] soLuongTinhTrang = new double[4];
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT TinhTrang, COUNT(*) FROM benhnhan WHERE MONTH(NgayDKKham) = ? GROUP BY TinhTrang"
            );
            stmt.setInt(1, thang);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tinhTrang = rs.getString(1);
                double soBenhNhan = rs.getDouble(2); // Lấy số lượng dưới dạng double

                switch (tinhTrang) {
                    case "Chờ Xét Nghiệm" ->
                        soLuongTinhTrang[0] = soBenhNhan;
                    case "Đã Khám" ->
                        soLuongTinhTrang[1] = soBenhNhan;
                    case "Đã Nhập Viện" ->
                        soLuongTinhTrang[2] = soBenhNhan;
                    case "Đã Xuất Viện" ->
                        soLuongTinhTrang[3] = soBenhNhan;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return soLuongTinhTrang;
    }

    public static void suaBenhNhanTheoTinhTrang(String MaBN, String TinhTrang) {
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE benhnhan SET TinhTrang = ? WHERE MaBN = ?");
            stmt.setString(1, TinhTrang);
            stmt.setString(2, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static void suaBenhNhanTheoNgayRaVien(String MaBN, String NgayRaVien) {
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE benhnhan SET NgayRaVien = ? WHERE MaBN = ?");
            stmt.setString(1, NgayRaVien);
            stmt.setString(2, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static List<Model_BenhNhan> getAllBenhNhan() {
        List<Model_BenhNhan> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan`")) {
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
                String TinhTrang = rs.getString("TinhTrang");
                String NgayDKKham = rs.getString("NgayDKKham");
                String NgayRaVien = rs.getString("NgayRaVien");
                Model_BenhNhan benhNhan = new Model_BenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT,
                        DienThoai, TinhTrang, NgayDKKham, NgayRaVien);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanList;
    }

    public static void suaBenhNhan(String MaBN, String TenBN, String NgaySinh, String GioiTinh, String CCCD,
            String DiaChi, String BHYT, String DienThoai, String TinhTrang) {
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE benhnhan SET TenBN = ?, NgaySinh = ?, GioiTinh = ?, CCCD = ?, DiaChi = ?, BHYT = ?, DienThoai = ?,TinhTrang = ? WHERE MaBN = ?");
            stmt.setString(1, TenBN);
            stmt.setString(2, NgaySinh);
            stmt.setString(3, GioiTinh);
            stmt.setString(4, CCCD);
            stmt.setString(5, DiaChi);
            stmt.setString(6, BHYT);
            stmt.setString(7, DienThoai);
            stmt.setString(8, TinhTrang);
            stmt.setString(9, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    private static Model_BenhNhan mapResultSetToBenhNhan(ResultSet rs) throws SQLException {
        String MaBN = rs.getString("MaBN");
        String TenBN = rs.getString("TenBN");
        String NgaySinh = rs.getString("NgaySinh");
        String GioiTinh = rs.getString("GioiTinh");
        String CCCD = rs.getString("CCCD");
        String BHYT = rs.getString("BHYT");
        String DiaChi = rs.getString("DiaChi");
        String DienThoai = rs.getString("DienThoai");
        String TinhTrang = rs.getString("TinhTrang");
        String NgayDKKham = rs.getString("NgayDKKham");
        String NgayRaVien = rs.getString("NgayRaVien");
        return new Model_BenhNhan(MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT,
                DienThoai, TinhTrang, NgayDKKham, NgayRaVien);
    }

    public static List<Model_BenhNhan> timKiemTheoMaBN(String MaBN) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE MaBN LIKE ?");
            stmt.setString(1, "%" + MaBN + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanListTk;
    }

    public static List<Model_BenhNhan> timKiemTheoTenBN(String TenBN) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE TenBN LIKE ?");
            stmt.setString(1, "%" + TenBN + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanListTk;
    }

    public static List<Model_BenhNhan> timKiemTheoNgaySinh(String NgaySinh) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE NgaySinh LIKE ?");
            stmt.setString(1, "%" + NgaySinh + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanListTk;
    }

    public static List<Model_BenhNhan> timKiemBNChuaXuatVien() {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE TinhTrang = 'Đã Khám' OR TinhTrang = 'Đã Nhập Viện'");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    public static void addBenhNhan(String MaBN, String TenBN, String NgaySinh, String GioiTinh, String CCCD,
            String DiaChi, String BHYT, String DienThoai, String TinhTrang, String NgayDKKham, String NgayRaVien) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO benhnhan (MaBN, TenBN, NgaySinh, GioiTinh, CCCD, DiaChi, BHYT, DienThoai, TinhTrang, NgayDKKham, NgayRaVien) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, MaBN);
            stmt.setString(2, TenBN);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedNgaySinh = LocalDate.parse(NgaySinh, formatter);
            stmt.setDate(3, Date.valueOf(parsedNgaySinh));
            stmt.setString(4, GioiTinh);
            stmt.setString(5, CCCD);
            stmt.setString(6, DiaChi);
            stmt.setString(7, BHYT);
            stmt.setString(8, DienThoai);
            stmt.setString(9, TinhTrang);
            LocalDate parsedNgayDKKham = LocalDate.parse(NgayDKKham, formatter);
            stmt.setDate(10, Date.valueOf(parsedNgayDKKham));
            stmt.setString(11, NgayRaVien);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}
