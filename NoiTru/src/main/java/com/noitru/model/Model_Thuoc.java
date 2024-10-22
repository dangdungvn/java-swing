package com.noitru.model;

public class Model_Thuoc {

    String Thuoc;
    float GiaTien;

    public Model_Thuoc() {
    }

    public Model_Thuoc(String Thuoc, float GiaTien) {
        this.Thuoc = Thuoc;
        this.GiaTien = GiaTien;
    }

    public String getThuoc() {
        return Thuoc;
    }

    public float getGiaTien() {
        return GiaTien;
    }

    public void setThuoc(String Thuoc) {
        this.Thuoc = Thuoc;
    }

    public void setGiaTien(float GiaTien) {
        this.GiaTien = GiaTien;
    }
}
