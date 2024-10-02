package com.testdatabase;

/**
 *
 * @author Admin
 */
public class NhaXuatBan {

    private String maNXB;
    private String tenNXB;
    private String ngayXB;
    private String dienThoai;
    private String email;
    private String diaChi;
    private String ghiChu;

    public NhaXuatBan() {
    }

    public NhaXuatBan(String maNXB, String tenNXB, String ngayXB, String dienThoai, String email, String diaChi, String ghiChu) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.ngayXB = ngayXB;
        this.dienThoai = dienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.ghiChu = ghiChu;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getNgayXB() {
        return ngayXB;
    }

    public void setNgayXB(String ngayXB) {
        this.ngayXB = ngayXB;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
