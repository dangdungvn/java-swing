package com.noitru.model;

public class Model_HoaDon {

    String Thuoc;
    int SoLuongThuoc;
    float GiaTien;
    int ThanhTien;

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

    public float getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int GiaTien) {
        this.GiaTien = GiaTien;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public Model_HoaDon(String Thuoc, int SoLuongThuoc, float GiaTien, int ThanhTien) {
        this.Thuoc = Thuoc;
        this.SoLuongThuoc = SoLuongThuoc;
        this.GiaTien = GiaTien;
        this.ThanhTien = ThanhTien;
    }

    public Model_HoaDon() {
    }
}
