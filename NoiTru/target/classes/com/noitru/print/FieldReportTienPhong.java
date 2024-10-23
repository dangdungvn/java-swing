package com.noitru.print;

import java.util.List;

public class FieldReportTienPhong {

    String loaiphong;
    long songayo;
    long thanhtien;
    List<FieldReportHoaDon> fields;

    public List<FieldReportHoaDon> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportHoaDon> fields) {
        this.fields = fields;
    }

    public FieldReportTienPhong(String loaiphong, long songayo, long thanhtien, List<FieldReportHoaDon> fields) {
        this.loaiphong = loaiphong;
        this.songayo = songayo;
        this.thanhtien = thanhtien;
        this.fields = fields;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public long getSongayo() {
        return songayo;
    }

    public void setSongayo(long songayo) {
        this.songayo = songayo;
    }

    public long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(long thanhtien) {
        this.thanhtien = thanhtien;
    }

    public FieldReportTienPhong() {
    }
}
