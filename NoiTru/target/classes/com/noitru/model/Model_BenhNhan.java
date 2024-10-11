package com.noitru.model;

public class Model_BenhNhan {

    private String MaBN;
    private String TenBN;
    private String NgaySinh;
    private String GioiTinh;
    private String CCCD;
    private String DiaChi;
    private String BHYT;
    private String DienThoai;

    public Model_BenhNhan(String MaBN, String TenBN, String NgaySinh, String GioiTinh, String CCCD, String DiaChi,
            String BHYT, String DienThoai) {
        this.MaBN = MaBN;
        this.TenBN = TenBN;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.CCCD = CCCD;
        this.DiaChi = DiaChi;
        this.BHYT = BHYT;
        this.DienThoai = DienThoai;
    }

    public Model_BenhNhan() {
    }

    public String getBHYT() {
        return BHYT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getMaBN() {
        return MaBN;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getTenBN() {
        return TenBN;
    }

    public void setBHYT(String BHYT) {
        this.BHYT = BHYT;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public void setTenBN(String TenBN) {
        this.TenBN = TenBN;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setMaBN(String MaBN) {
        this.MaBN = MaBN;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

}
