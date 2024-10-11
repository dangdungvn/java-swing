package com.noitru.model;

public class Model_GiuongBenh {

    String ChuyenKhoa, LoaiPhong, SoPhong, SoGiuong, MaBN, MaBS;

    public Model_GiuongBenh(String ChuyenKhoa, String LoaiPhong, String SoPhong, String SoGiuong, String MaBN, String MaBS) {
        this.ChuyenKhoa = ChuyenKhoa;
        this.LoaiPhong = LoaiPhong;
        this.SoPhong = SoPhong;
        this.SoGiuong = SoGiuong;
        this.MaBN = MaBN;
        this.MaBS = MaBS;
    }

    public Model_GiuongBenh() {
    }

    public String getChuyenKhoa() {
        return ChuyenKhoa;
    }

    public void setChuyenKhoa(String ChuyenKhoa) {
        this.ChuyenKhoa = ChuyenKhoa;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String SoPhong) {
        this.SoPhong = SoPhong;
    }

    public String getSoGiuong() {
        return SoGiuong;
    }

    public void setSoGiuong(String SoGiuong) {
        this.SoGiuong = SoGiuong;
    }

    public String getMaBN() {
        return MaBN;
    }

    public void setMaBN(String MaBN) {
        this.MaBN = MaBN;
    }

    public String getMaBS() {
        return MaBS;
    }

    public void setMaBS(String MaBS) {
        this.MaBS = MaBS;
    }
}
