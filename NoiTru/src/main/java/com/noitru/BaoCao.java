package com.noitru;

import com.noitru.model.Model_BaoCao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaoCao {

    public static List<Model_BaoCao> getAllThongTin() {
        List<Model_BaoCao> benhNhanList = new ArrayList<>();
        try (Connection conn = ConnectDB.connect(); PreparedStatement stmt = conn
                .prepareStatement("SELECT thongtinkhambenh.*, benhnhan.TenBN, benhnhan.DiaChi, benhnhan.CCCD, benhnhan.GioiTinh ,benhnhan.NgayDKKham, benhnhan.TinhTrang "
                        + "FROM thongtinkhambenh "
                        + "JOIN benhnhan ON thongtinkhambenh.MaBN = benhnhan.MaBN")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanList.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanList;
    }

    private static Model_BaoCao mapResultSetToBenhNhan(ResultSet rs) throws SQLException {
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
        String TenBN = rs.getString("TenBN");
        String DiaChi = rs.getString("DiaChi");
        String CCCD = rs.getString("CCCD");
        String GioiTinh = rs.getString("GioiTinh");
        String NgayDKKham = rs.getString("NgayDKKham");
        String TinhTrang = rs.getString("TinhTrang");
        return new Model_BaoCao(MaBS, NgayKham, PhongKham,
                MaBN, ChuyenKhoa, CanNang, NhomMau, NhietDo,
                Mach, HuyetAp, NhipTho, LyDoKham, TinhTrangHienTai,
                ChuanDoanSoBo, HuongDieuTri,
                TenBN, DiaChi, CCCD, GioiTinh, NgayDKKham, TinhTrang);
    }

    public static List<Model_BaoCao> timKiemTheoMaBN(String MaBN) {
        List<Model_BaoCao> benhNhanListTk = new ArrayList<>();
        try {
            Connection conn = ConnectDB.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT thongtinkhambenh.*, benhnhan.TenBN, benhnhan.DiaChi, benhnhan.CCCD, benhnhan.GioiTinh ,benhnhan.NgayDKKham, benhnhan.TinhTrang "
                    + "FROM thongtinkhambenh "
                    + "JOIN benhnhan ON thongtinkhambenh.MaBN = benhnhan.MaBN "
                    + "WHERE thongtinkhambenh.MaBN = ?");
            stmt.setString(1, MaBN);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                benhNhanListTk.add(mapResultSetToBenhNhan(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return benhNhanListTk;
    }
}
