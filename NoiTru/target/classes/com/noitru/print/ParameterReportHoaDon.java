package com.noitru.print;

import java.util.List;

public class ParameterReportHoaDon {

    String tenBenhNhan;
    String tongTien;
    List<FieldReportHoaDon> fields;

    public ParameterReportHoaDon(String tenBenhNhan, String tongTien, List<FieldReportHoaDon> fields) {
        this.tenBenhNhan = tenBenhNhan;
        this.tongTien = tongTien;
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
