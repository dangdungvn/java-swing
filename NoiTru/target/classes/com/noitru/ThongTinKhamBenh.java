package com.noitru;

import com.noitru.model.Model_BenhNhan;
import com.noitru.model.Model_ThongTinKhamBenh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongTinKhamBenh {

    public static List<String> getAllPhongKham() {
        List<String> chuyenKhoaList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement("SELECT PhongKham FROM `phongkham` GROUP BY PhongKham")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ChuyenKhoa = rs.getString("PhongKham");
                chuyenKhoaList.add(ChuyenKhoa);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return chuyenKhoaList;
    }

    public static List<Model_BenhNhan> getAllBenhNhanChuaKham() {
        List<Model_BenhNhan> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement("SELECT * FROM `benhnhan` WHERE TinhTrang = 'Chờ Xét Nghiệm'")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_BenhNhan benhNhan = mapResultSetToBenhNhan(rs);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanList;
    }

    public static List<Model_ThongTinKhamBenh> getAllThongTin() {
        List<Model_ThongTinKhamBenh> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement("SELECT * FROM `thongtinkhambenh`")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_ThongTinKhamBenh benhNhan = mapResultSetToThongTinKhamBenh(rs);
                benhNhanList.add(benhNhan);
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanList;
    }

    public static boolean addThongTin(String MaBS, String NgayKham, String PhongKham, String MaBN,
            String ChuyenKhoa, String CanNang, String NhomMau, String NhietDo,
            String Mach, String HuyetAp, String NhipTho, String LyDoKham, String TinhTrangHienTai,
            String ChuanDoanSoBo, String HuongDieuTri) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement(
                        "INSERT INTO `thongtinkhambenh` (MaBS, NgayKham, PhongKham, MaBN, ChuyenKhoa, CanNang, NhomMau, NhietDo, Mach, HuyetAp, NhipTho, LyDoKham, TinhTrangHienTai, ChuanDoanSoBo, HuongDieuTri) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            stmt.setString(1, MaBS);
            stmt.setString(2, NgayKham);
            stmt.setString(3, PhongKham);
            stmt.setString(4, MaBN);
            stmt.setString(5, ChuyenKhoa);
            stmt.setString(6, CanNang);
            stmt.setString(7, NhomMau);
            stmt.setString(8, NhietDo);
            stmt.setString(9, Mach);
            stmt.setString(10, HuyetAp);
            stmt.setString(11, NhipTho);
            stmt.setString(12, LyDoKham);
            stmt.setString(13, TinhTrangHienTai);
            stmt.setString(14, ChuanDoanSoBo);
            stmt.setString(15, HuongDieuTri);
            stmt.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean suaThongTin(String MaBS, String NgayKham, String PhongKham, String MaBN, String ChuyenKhoa,
            String CanNang, String NhomMau, String NhietDo,
            String Mach, String HuyetAp, String NhipTho, String LyDoKham, String TinhTrangHienTai,
            String ChuanDoanSoBo, String HuongDieuTri) {
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn.prepareStatement(
                "UPDATE thongtinkhambenh SET MaBS = ?, NgayKham = ?, PhongKham = ?, ChuyenKhoa = ?, CanNang = ?, NhomMau = ?, NhietDo = ?, Mach = ?, HuyetAp = ?, NhipTho = ?, LyDoKham = ?, TinhTrangHienTai = ?, ChuanDoanSoBo = ?, HuongDieuTri = ? WHERE MaBN = ?")) {
            stmt.setString(1, MaBS);
            stmt.setString(2, NgayKham);
            stmt.setString(3, PhongKham);
            stmt.setString(4, ChuyenKhoa);
            stmt.setString(5, CanNang);
            stmt.setString(6, NhomMau);
            stmt.setString(7, NhietDo);
            stmt.setString(8, Mach);
            stmt.setString(9, HuyetAp);
            stmt.setString(10, NhipTho);
            stmt.setString(11, LyDoKham);
            stmt.setString(12, TinhTrangHienTai);
            stmt.setString(13, ChuanDoanSoBo);
            stmt.setString(14, HuongDieuTri);
            stmt.setString(15, MaBN);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Model_ThongTinKhamBenh> timKiemTheoMaBN(String MaBN) {
        List<Model_ThongTinKhamBenh> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM thongtinkhambenh WHERE MaBN = ?");
            stmt.setString(1, MaBN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Model_ThongTinKhamBenh benhNhan = mapResultSetToThongTinKhamBenh(rs);
                benhNhanListTk.add(benhNhan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }

    private static Model_ThongTinKhamBenh mapResultSetToThongTinKhamBenh(ResultSet rs) throws SQLException {
        String MaBS = rs.getString("MaBS");
        String NgayKham = rs.getString("NgayKham");
        String PhongKham = rs.getString("PhongKham");
        String MaBN = rs.getString("MaBN");
        String ChuyenKhoa = rs.getString("ChuyenKhoa");
        String CanNang = rs.getString("CanNang");
        String NhomMau = rs.getString("NhomMau");
        String NhietDo = rs.getString("NhietDo");
        String Mach = rs.getString("Mach");
        String HuyetAp = rs.getString("HuyetAp");
        String NhipTho = rs.getString("NhipTho");
        String LyDoKham = rs.getString("LyDoKham");
        String TinhTrangHienTai = rs.getString("TinhTrangHienTai");
        String ChuanDoanSoBo = rs.getString("ChuanDoanSoBo");
        String HuongDieuTri = rs.getString("HuongDieuTri");
        return new Model_ThongTinKhamBenh(MaBS, NgayKham, PhongKham, MaBN, ChuyenKhoa, CanNang, NhomMau, NhietDo,
                Mach, HuyetAp, NhipTho, LyDoKham, TinhTrangHienTai, ChuanDoanSoBo, HuongDieuTri);
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

    public static List<Model_BenhNhan> timKiemTheoTenBN(String TenBN) {
        List<Model_BenhNhan> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `benhnhan` WHERE TenBN = ?");
            stmt.setString(1, TenBN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return benhNhanListTk;
    }
}
