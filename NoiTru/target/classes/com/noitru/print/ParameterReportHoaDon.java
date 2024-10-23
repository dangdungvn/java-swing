package com.noitru.print;

import java.util.List;

public class ParameterReportHoaDon {

    String tenBenhNhan;
    String tongTien;
    String loaiPhong;
    String soNgayO;
    String tienPhong;
    List<FieldReportHoaDon> fields;

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getSoNgayO() {
        return soNgayO;
    }

    public void setSoNgayO(String soNgayO) {
        this.soNgayO = soNgayO;
    }

    public String getTienPhong() {
        return tienPhong;
    }

    public void setTienPhong(String tienPhong) {
        this.tienPhong = tienPhong;
    }

    public ParameterReportHoaDon(String tenBenhNhan, String tongTien, String loaiPhong, String soNgayO, String tienPhong, List<FieldReportHoaDon> fields) {
        this.tenBenhNhan = tenBenhNhan;
        this.tongTien = tongTien;
        this.loaiPhong = loaiPhong;
        this.soNgayO = soNgayO;
        this.tienPhong = tienPhong;
        this.fields = fields;
    }

    public ParameterReportHoaDon() {
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public List<FieldReportHoaDon> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportHoaDon> fields) {
        this.fields = fields;
    }
}
