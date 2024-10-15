package com.noitru.model;

public class Model_DieuTri {

    private String MaBN;
    private int SoNgayNhapVien;
    private String Ngay;
    private String Thuoc;
    private int SoLuongThuoc;
    private int ThanhTien;

    public Model_DieuTri() {
    }

    public Model_DieuTri(String MaBN, int SoNgayNhapVien, String Ngay, String Thuoc, int SoLuongThuoc, int ThanhTien) {
        this.MaBN = MaBN;
        this.SoNgayNhapVien = SoNgayNhapVien;
        this.Ngay = Ngay;
        this.Thuoc = Thuoc;
        this.SoLuongThuoc = SoLuongThuoc;
        this.ThanhTien = ThanhTien;
    }

    public String getMaBN() {
        return MaBN;
    }

    public void setMaBN(String MaBN) {
        this.MaBN = MaBN;
    }

    public int getSoNgayNhapVien() {
        return SoNgayNhapVien;
    }

    public void setSoNgayNhapVien(int SoNgayNhapVien) {
        this.SoNgayNhapVien = SoNgayNhapVien;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getThuoc() {
        return Thuoc;
    }

    public void setThuoc(String Thuoc) {
        this.Thuoc = Thuoc;
    }

    public int getSoLuongThuoc() {
        return SoLuongThuoc;
    }

    public void setSoLuongThuoc(int SoLuongThuoc) {
        this.SoLuongThuoc = SoLuongThuoc;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
}
