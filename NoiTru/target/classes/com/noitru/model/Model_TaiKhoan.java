package com.noitru.model;

public class Model_TaiKhoan {

    private String TaiKhoan, MatKhau, Email;

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Model_TaiKhoan(String TaiKhoan, String MatKhau, String Email) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
    }

    public Model_TaiKhoan() {
    }
}
