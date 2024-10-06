package com.noitru.model;

public class Model_BacSi {

    private String MaBS;
    private String TenBS;
    private String KinhNghiem;
    private String ChuyenKhoa;

    public Model_BacSi(String MaBS, String TenBS, String KinhNghiem, String ChuyenKhoa) {
        this.MaBS = MaBS;
        this.TenBS = TenBS;
        this.KinhNghiem = KinhNghiem;
        this.ChuyenKhoa = ChuyenKhoa;
    }

    public Model_BacSi() {

    }

    public String getMaBS() {
        return MaBS;
    }

    public void setMaBS(String maBS) {
        MaBS = maBS;
    }

    public String getTenBS() {
        return TenBS;
    }

    public void setTenBS(String tenBS) {
        TenBS = tenBS;
    }

    public String getKinhNghiem() {
        return KinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        KinhNghiem = kinhNghiem;
    }

    public String getChuyenKhoa() {
        return ChuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        ChuyenKhoa = chuyenKhoa;
    }
}
