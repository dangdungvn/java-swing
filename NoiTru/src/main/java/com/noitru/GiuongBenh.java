package com.noitru;

import com.noitru.model.Model_BenhNhan;
import com.noitru.model.Model_GiuongBenh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiuongBenh {

    private static Model_GiuongBenh mapResultSetToGiuongBenh(ResultSet rs) throws SQLException {
        String ChuyenKhoa = rs.getString("ChuyenKhoa");
        String LoaiPhong = rs.getString("LoaiPhong");
        String SoPhong = rs.getString("SoPhong");
        String SoGiuong = rs.getString("SoGiuong");
        String MaBN = rs.getString("MaBN");
        String MaBS = rs.getString("MaBS");
        return new Model_GiuongBenh(ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN,
                MaBS);
    }

    public static List<Model_GiuongBenh> timKiemTheoMaBS(String MaBS) {
        List<Model_GiuongBenh> giuongBenhList = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `giuongbenh` WHERE MaBS LIKE ?");
            stmt.setString(1, "%" + MaBS + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                giuongBenhList.add(mapResultSetToGiuongBenh(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return giuongBenhList;
    }

    public static List<Model_GiuongBenh> timKiemTheoMaBN(String MaBN) {
        List<Model_GiuongBenh> giuongBenhList = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `giuongbenh` WHERE MaBN LIKE ?");
            stmt.setString(1, "%" + MaBN + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                giuongBenhList.add(mapResultSetToGiuongBenh(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return giuongBenhList;
    }

    public static void xoaGiuongBenh(String MaBN) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("DELETE FROM `giuongbenh` WHERE MaBN = ?")) {
            stmt.setString(1, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static void suaGiuongBenh(String ChuyenKhoa, String LoaiPhong, String SoPhong, String SoGiuong, String MaBN,
            String MaBS) {
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE giuongbenh SET ChuyenKhoa = ?, LoaiPhong = ?, SoPhong = ?, SoGiuong = ?, MaBS = ? WHERE MaBN = ?");
            stmt.setString(1, ChuyenKhoa);
            stmt.setString(2, LoaiPhong);
            stmt.setString(3, SoPhong);
            stmt.setString(4, SoGiuong);
            stmt.setString(5, MaBS);
            stmt.setString(6, MaBN);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static void addGiuongBenh(String ChuyenKhoa, String LoaiPhong, String SoPhong, String SoGiuong, String MaBN,
            String MaBS) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO giuongbenh (ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN, MaBS)"
                + "VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, ChuyenKhoa);
            stmt.setString(2, LoaiPhong);
            stmt.setString(3, SoPhong);
            stmt.setString(4, SoGiuong);
            stmt.setString(5, MaBN);
            stmt.setString(6, MaBS);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static List<Model_GiuongBenh> getAllGiuongBenh() {
        List<Model_GiuongBenh> giuongBenhList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `giuongbenh`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ChuyenKhoa = rs.getString("ChuyenKhoa");
                String LoaiPhong = rs.getString("LoaiPhong");
                String SoPhong = rs.getString("SoPhong");
                String SoGiuong = rs.getString("SoGiuong");
                String MaBN = rs.getString("MaBN");
                String MaBS = rs.getString("MaBS");
                Model_GiuongBenh giuongBenh = new Model_GiuongBenh(ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN,
                        MaBS);
                giuongBenhList.add(giuongBenh);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return giuongBenhList;
    }

    public static List<Model_BenhNhan> getBenhNhanChuaDuocXepGiuong() {
        List<Model_BenhNhan> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE TinhTrang = 'Đã Khám'")) {
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
}
